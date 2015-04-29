package com.micro.rent.biz.weixin.message.receive.normal;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BaseOrdinaryMessage extends BaseMessage {

    @XStreamAlias("MsgId")
    private long msgId;

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

}
