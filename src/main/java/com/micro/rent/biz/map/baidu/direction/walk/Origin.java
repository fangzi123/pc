package com.micro.rent.biz.map.baidu.direction.walk;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author dell
 * @Description:
 * @date 2014-8-5
 */
@XStreamAlias("origin")
public class Origin {
    @XStreamAlias("area_id")
    private String areaId;
    @XStreamAlias("cname")
    private String cname;
    @XStreamAlias("uid")
    private String uid;
    @XStreamAlias("wd")
    private String wd;
    @XStreamAlias("bus_stop")
    private String busStop;
    @XStreamAlias("originPt")
    private MapPoint originPt;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getWd() {
        return wd;
    }

    public void setWd(String wd) {
        this.wd = wd;
    }

    public String getBusStop() {
        return busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public MapPoint getOriginPt() {
        return originPt;
    }

    public void setOriginPt(MapPoint originPt) {
        this.originPt = originPt;
    }


}
