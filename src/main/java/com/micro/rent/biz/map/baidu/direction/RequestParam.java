package com.micro.rent.biz.map.baidu.direction;

import com.micro.rent.biz.map.service.DirectionReqParam;

public class RequestParam implements DirectionReqParam {

    private String origin;
    private String destination;
    private String mode;
    private String region;
    private String origin_region;
    private String destination_region;

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getOrigin_region() {
        return origin_region;
    }

    public void setOrigin_region(String origin_region) {
        this.origin_region = origin_region;
    }

    public String getDestination_region() {
        return destination_region;
    }

    public void setDestination_region(String destination_region) {
        this.destination_region = destination_region;
    }


}
