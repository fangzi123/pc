package com.micro.rent.biz.weixin.service;

public interface MessageHandler {

    /**
     * 消息处理接口
     *
     * @param source  微信发送的消息内容
     * @param strings (服务器根URL、.....)
     * @return
     */
    String handleMessage(String source, Object... strings);
}
