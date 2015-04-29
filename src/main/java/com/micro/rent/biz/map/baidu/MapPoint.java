package com.micro.rent.biz.map.baidu;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

public class MapPoint {

    private BigDecimal lng;
    private BigDecimal lat;

    public MapPoint() {

    }

    public MapPoint(BigDecimal lng, BigDecimal lat) {
        this.lat = lat;
        this.lng = lng;
    }

    public MapPoint(String lng, String lat) {
        this.lat = new BigDecimal(lat);
        this.lng = new BigDecimal(lng);
    }


    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
