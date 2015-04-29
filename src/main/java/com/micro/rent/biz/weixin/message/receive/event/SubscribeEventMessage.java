package com.micro.rent.biz.weixin.message.receive.event;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("xml")
public class SubscribeEventMessage extends BaseMessage {
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("EventKey")
    private String eventKey;
    @XStreamAlias("Ticket")
    private String ticket;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
