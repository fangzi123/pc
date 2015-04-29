package com.micro.rent.biz.weixin.message.receive.normal;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("xml")
public class TextMessage extends BaseOrdinaryMessage {
    @XStreamAlias("Content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
