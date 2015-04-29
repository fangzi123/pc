package com.micro.rent.common.comm.mybatis;

public abstract class Dialect {

    public abstract String getLimitString(String sql, int offset, int limit);

    public static enum Type {
        MYSQL,
        ORACLE,
        POSTGRESQL
    }
}
