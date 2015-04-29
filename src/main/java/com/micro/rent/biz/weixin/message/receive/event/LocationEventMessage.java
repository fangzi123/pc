package com.micro.rent.biz.weixin.message.receive.event;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("xml")
public class LocationEventMessage extends BaseMessage {
    @XStreamAlias("Event")
    private String event;
    @XStreamAlias("Latitude")
    private String latitude;
    @XStreamAlias("Longitude")
    private String longitude;
    @XStreamAlias("Precision")
    private String precision;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
