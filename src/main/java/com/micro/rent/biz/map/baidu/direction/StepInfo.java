package com.micro.rent.biz.map.baidu.direction;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@XStreamAlias("info")
public class StepInfo {

    private Integer distance;
    private Integer duration;
    private String path;
    private String type;
    private MapPoint stepOriginLocation;
    private MapPoint stepDestinationLocation;
    private String stepInstruction;
    private Vehicle vehicle;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MapPoint getStepOriginLocation() {
        return stepOriginLocation;
    }

    public void setStepOriginLocation(MapPoint stepOriginLocation) {
        this.stepOriginLocation = stepOriginLocation;
    }

    public MapPoint getStepDestinationLocation() {
        return stepDestinationLocation;
    }

    public void setStepDestinationLocation(MapPoint stepDestinationLocation) {
        this.stepDestinationLocation = stepDestinationLocation;
    }

    public String getStepInstruction() {
        return stepInstruction;
    }

    public void setStepInstruction(String stepInstruction) {
        this.stepInstruction = stepInstruction;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
