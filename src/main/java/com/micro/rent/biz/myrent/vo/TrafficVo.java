package com.micro.rent.biz.myrent.vo;

import java.math.BigDecimal;

public class TrafficVo {

    private String communityName;
    private String trafficName;
    private String trafficStation;
    private BigDecimal distance;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getTrafficName() {
        return trafficName;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
    }

    public String getTrafficStation() {
        return trafficStation;
    }

    public void setTrafficStation(String trafficStation) {
        this.trafficStation = trafficStation;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public void setDistance(BigDecimal distance) {
        this.distance = distance;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

}
