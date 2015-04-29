package com.micro.rent.biz.map.baidu.direction;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Origin {

    @XStreamAlias("originPt")
    private MapPoint origin;

    public MapPoint getOrigin() {
        return origin;
    }

    public void setOrigin(MapPoint origin) {
        this.origin = origin;
    }


}
