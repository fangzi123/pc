package com.micro.rent.common.comm.mybatis;

public class MysqlDialect extends Dialect {

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
        pagingSelect.append(sql);
        pagingSelect.append("  limit  ").append(offset)
                .append(" , ").append(limit);
        return pagingSelect.toString();
    }
}
