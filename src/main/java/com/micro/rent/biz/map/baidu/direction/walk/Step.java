package com.micro.rent.biz.map.baidu.direction.walk;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author dell
 * @Description:
 * @date 2014-8-5
 */
@XStreamAlias("content")
public class Step {
    private String area;
    private String direction;
    private String distance;
    private String duration;
    private String instructions;
    private String path;
    private String turn;
    private String type;
    private MapPoint stepOriginLocation;
    private MapPoint stepDestinationLocation;
    private String stepOriginInstruction;
    private String stepDestinationInstruction;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
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

    public String getStepOriginInstruction() {
        return stepOriginInstruction;
    }

    public void setStepOriginInstruction(String stepOriginInstruction) {
        this.stepOriginInstruction = stepOriginInstruction;
    }

    public String getStepDestinationInstruction() {
        return stepDestinationInstruction;
    }

    public void setStepDestinationInstruction(String stepDestinationInstruction) {
        this.stepDestinationInstruction = stepDestinationInstruction;
    }

}
