package com.micro.rent.biz.map.baidu.direction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;


public class Vehicle {

    @XStreamAlias("end_name")
    private String endName;
    @XStreamAlias("end_time")
    private String endTime;
    @XStreamAlias("end_uid")
    private String endUid;
    @XStreamAlias("name")
    private String name;
    @XStreamAlias("start_name")
    private String startName;
    @XStreamAlias("start_time")
    private String startTime;
    @XStreamAlias("start_uid")
    private String startUid;
    @XStreamAlias("stop_num")
    private String stopNum;
    @XStreamAlias("total_price")
    private String totalPrice;
    @XStreamAlias("type")
    private String type;
    @XStreamAlias("uid")
    private String uid;
    @XStreamAlias("zone_price")
    private String zonePrice;

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndUid() {
        return endUid;
    }

    public void setEndUid(String endUid) {
        this.endUid = endUid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartUid() {
        return startUid;
    }

    public void setStartUid(String startUid) {
        this.startUid = startUid;
    }

    public String getStopNum() {
        return stopNum;
    }

    public void setStopNum(String stopNum) {
        this.stopNum = stopNum;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getZonePrice() {
        return zonePrice;
    }

    public void setZonePrice(String zonePrice) {
        this.zonePrice = zonePrice;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
