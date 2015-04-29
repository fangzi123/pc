package com.micro.rent.common.comm.mybatis;

import com.micro.rent.common.web.controller.pagination.Pageable;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.Properties;


@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class PaginationInterceptor implements org.apache.ibatis.plugin.Interceptor {
    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final String defaultSqlPageId = ".*Paged$";
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin
     * .Invocation)
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaStatementHandler = MetaObject
                .forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

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

        Dialect dialect = null;

        switch (databaseType) {
            case MYSQL:
                dialect = new MysqlDialect();
                break;
            case ORACLE:
                dialect = new OracleDialect();
                break;
        }

        //获取分页sql的规则
        String pageSqlId = configuration.getVariables().getProperty("pageSqlId");
        if (null == pageSqlId || "".equals(pageSqlId)) {
            pageSqlId = defaultSqlPageId;
        }

        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
        // 只重写需要分页的sql语句。通过MappedStatement的ID匹配，默认重写以Page结尾的
        //  MappedStatement的sql
        if (mappedStatement.getId().matches(pageSqlId)) {
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
            if (parameterObject == null) {
                throw new NullPointerException("parameterObject is null!");
            }

            Pageable pageable = (Pageable) metaStatementHandler.getValue("delegate.boundSql.parameterObject.page");

            String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

            metaStatementHandler.setValue(
                    "delegate.boundSql.sql",
                    dialect.getLimitString(
                            originalSql,
                            (pageable.getPageStart() - 1)
                                    * pageable.getPageLimit(),
                            pageable.getPageLimit()));

            metaStatementHandler.setValue("delegate.rowBounds.offset",
                    RowBounds.NO_ROW_OFFSET);

            metaStatementHandler.setValue("delegate.rowBounds.limit",
                    RowBounds.NO_ROW_LIMIT);

            if (log.isDebugEnabled()) {
                log.debug("生成分页SQL : " + boundSql.getSql());
            }
        }

        return invocation.proceed();

    }


    /*
     * (non-Javadoc)
     *
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override
    public void setProperties(Properties arg0) {
        // TODO Auto-generated method stub

    }
}
