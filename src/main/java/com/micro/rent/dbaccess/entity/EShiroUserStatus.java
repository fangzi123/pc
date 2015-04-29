package com.micro.rent.dbaccess.entity;

/**
 * @author
 * @version 1.0
 * @Description 用户状态
 * @date
 */
public enum EShiroUserStatus {
    /**
     * 正常
     */
    NORMAL("1"),
    /**
     * 禁用
     */
    FORBIDDEN("0");

    private final String code;

    EShiroUserStatus(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
