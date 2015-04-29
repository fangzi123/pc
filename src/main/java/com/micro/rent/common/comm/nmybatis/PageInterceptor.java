package com.micro.rent.common.comm.nmybatis;

import com.micro.rent.common.comm.mybatis.Dialect;
import com.micro.rent.common.comm.mybatis.MysqlDialect;
import com.micro.rent.common.comm.mybatis.OracleDialect;
import com.micro.rent.common.comm.mybatis.PostgresqlDialect;
import com.micro.rent.common.support.ReflectUtil;
import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})
})
public class PageInterceptor implements Interceptor {

    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final String defaultSqlPageId = ".*Paged$";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation.getTarget();

        MetaObject metaStatementHandler = MetaObject
                .forObject(handler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

        // 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环
        // 可以分离出最原始的的目标类)
        while (metaStatementHandler.hasGetter("h")) {
            Object object = metaStatementHandler.getValue("h");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
        }
        // 分离最后一个代理对象的目标类
        while (metaStatementHandler.hasGetter("target")) {
            Object object = metaStatementHandler.getValue("target");
            metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY,
                    DEFAULT_OBJECT_WRAPPER_FACTORY);
        }

        Configuration configuration = (Configuration) metaStatementHandler
                .getValue("delegate.configuration");

        Dialect.Type databaseType = null;

        try {
            databaseType = Dialect.Type.valueOf(configuration.getVariables()
                    .getProperty("dialect").toUpperCase());
        } catch (Exception e) {
            log.error("dialect error", e.getMessage());
        }

        if (databaseType == null) {
            log.error("data base type is null.");
            throw new RuntimeException(
                    "the value of the dialect property in configuration.xml is not defined : "
                            + configuration.getVariables().getProperty(
                            "dialect"));
        }

        Dialect dialect = new OracleDialect();

        switch (databaseType) {
            case MYSQL:
                dialect = new MysqlDialect();
                break;
            case ORACLE:
                dialect = new OracleDialect();
                break;
            case POSTGRESQL:
                dialect = new PostgresqlDialect();
                break;
        }

        //获取分页sql的规则
        String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
        if (null == pageSqlId || "".equals(pageSqlId)) {
            pageSqlId = defaultSqlPageId;
        }
        //MappedStatement mappedStatement = (MappedStatement)metaStatementHandler.getValue("delegate.mappedStatement");

        //通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler) ReflectUtil.getFieldValue(handler, "delegate");

        //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
        MappedStatement mappedStatement = (MappedStatement) ReflectUtil.getFieldValue(delegate, "mappedStatement");

        //获取到当前StatementHandler的 boundSql，这里不管是调用handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的，因为之前已经说过了
        //RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql boundSql = delegate.getBoundSql();

        //拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
        Object obj = boundSql.getParameterObject();

        if (obj instanceof Page<?>) {
            Page<?> page = (Page<?>) obj;

            //拦截到的prepare方法参数是一个Connection对象
            Connection connection = (Connection) invocation.getArgs()[0];

            //获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
            String sql = boundSql.getSql();

            //给当前的page参数对象设置总记录数
            this.setTotalRecord(page, mappedStatement, connection);
            //获取分页Sql语句
            String pageSql = this.getPageSql(page, sql, dialect);
            //log.debug(pageSql);
            //利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
            ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties arg0) {

    }

    /**
     * 根据page对象获取对应的分页查询Sql语句，这里只做了三种数据库类型，Mysql, Oracle, postgresql
     * 其它的数据库都 没有进行分页
     *
     * @param page 分页对象
     * @param sql  原sql语句
     * @return
     */
    private String getPageSql(Page<?> page, String sql, Dialect dialect) {
        return dialect.getLimitString(sql, (page.getPageStart() - 1) * page.getPageLimit(), page.getPageLimit());
    }

    /**
     * 给当前的参数对象page设置总记录数
     *
     * @param page            Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection      当前的数据库连接
     */
    private void setTotalRecord(Page<?> page,
                                MappedStatement mappedStatement, Connection connection) {

        BoundSql boundSql = mappedStatement.getBoundSql(page);

        String sql = boundSql.getSql();
        // 通过查询Sql语句获取到对应的计算总记录数的sql语句
        String countSql = this.getCountSql(sql);
        // 通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql
                .getParameterMappings();
        // 利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(
                mappedStatement.getConfiguration(), countSql,
                parameterMappings, page);
        // 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
        ParameterHandler parameterHandler = new DefaultParameterHandler(
                mappedStatement, page, countBoundSql);
        // 通过connection建立一个countSql对应的PreparedStatement对象。
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            // 通过parameterHandler给PreparedStatement对象设置参数
            parameterHandler.setParameters(pstmt);
            // 之后就是执行获取总记录数的Sql语句和获取结果了。
            rs = pstmt.executeQuery();
            if (rs.next()) {
                int totalRecord = rs.getInt(1);
                // 给当前的参数page对象设置总记录数
                page.setTotalRecord(totalRecord);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     *
     * @param sql
     * @return
     */
    private String getCountSql(String sql) {
        return "select count(*) from (" + sql + ") sub_search";
    }

}
