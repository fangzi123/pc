package com.micro.rent.biz.map.baidu.geocoder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.math.BigDecimal;

@XStreamAlias("location")
public class Location {
    private BigDecimal lat;
    private BigDecimal lng;

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
