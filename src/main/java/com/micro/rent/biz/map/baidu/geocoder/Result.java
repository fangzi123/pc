package com.micro.rent.biz.map.baidu.geocoder;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("result")
public class Result {

    private Location location;
    private int precise;
    private int confidence;
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getConfidence() {
        return confidence;
    }

    public void setConfidence(int confidence) {
        this.confidence = confidence;
    }

    public int getPrecise() {
        return precise;
    }

    public void setPrecise(int precise) {
        this.precise = precise;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
