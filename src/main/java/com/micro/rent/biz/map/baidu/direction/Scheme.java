package com.micro.rent.biz.map.baidu.direction;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.util.ArrayList;
import java.util.List;

@XStreamAlias("scheme")
public class Scheme {
    @XStreamAlias("duration")
    private String distance;
    @XStreamAlias("duration")
    private String duration;
    @XStreamAlias("steps")
    private List<StepInfo> steps = new ArrayList<StepInfo>();
    @XStreamAlias("originLocation")
    private Integer originLocation;
    @XStreamAlias("destinationLocation")
    private Integer destinationLocation;

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<StepInfo> getSteps() {
        return steps;
    }

    public void setSteps(List<StepInfo> steps) {
        this.steps = steps;
    }

    public Integer getOriginLocation() {
        return originLocation;
    }

    public void setOriginLocation(Integer originLocation) {
        this.originLocation = originLocation;
    }

    public Integer getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(Integer destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
