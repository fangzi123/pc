package com.micro.rent.biz.weixin.message;

/**
 * 消息类型
 *
 * @author liqianxi
 * @date 2014-12-14
 */
public enum EMsgType {

    /**
     * 事件类型
     */
    EVENT("event"),

    /**
     * 图文类型
     */
    NEWS("news"),

    /**
     * 文本类型
     */
    TEXT("text"),

    /**
     * 图片类型
     */
    IMAGE("image"),

    /**
     * 语音类型
     */
    VOICE("voice"),

    /**
     * 视频类型
     */
    VIDEO("video"),

    /**
     * 音乐类型
     */
    MUSIC("music");

    private String code;

    private EMsgType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
