package com.micro.rent.biz.weixin.message;

/**
 * 事件类型
 *
 * @author liqianxi
 * @date 2014-12-14
 */
public enum EEventType {

    /**
     * 关注事件
     */
    SUBSCRIBE("subscribe"),

    /**
     * 取消关注事件
     */
    UNSUBSCRIBE("unsubscribe"),

    /**
     * 用户已关注时的事件推送
     */
    SCAN("SCAN"),

    /**
     * 上报地理位置事件
     */
    LOCATION("LOCATION"),

    /**
     * 点击菜单拉取消息时的事件
     */
    CLICK("CLICK"),

    /**
     * 点击菜单跳转链接时的事件
     */
    VIEW("VIEW");

    private String code;

    private EEventType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
