package com.micro.rent.common.support.task._enum;

/**
 * @author
 * @version 1.0
 * @Description 消息资源类型
 * @date 2012-11-26
 */
public enum MessageType {

    /**
     * 异常资源类型：errors_message.properties
     */
    ERRORS_TYPE("errors"),

    /**
     * 字典资源类型
     */
    DICTS_TYPE("dicts");

    private final String code;

    MessageType(String code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }
}
