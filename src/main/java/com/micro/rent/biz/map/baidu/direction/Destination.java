package com.micro.rent.biz.map.baidu.direction;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Destination {

    @XStreamAlias("destinationPt")
    private MapPoint destination;

    public MapPoint getDestination() {
        return destination;
    }

    public void setDestination(MapPoint destination) {
        this.destination = destination;
    }

}
