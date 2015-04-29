package com.micro.rent.biz.myrent.vo;

import java.math.BigDecimal;

public class MatchResultWrap2 {

    private BigDecimal lng;
    private BigDecimal lat;
    private String houseId;
    private String tHouseId;
    private String duration;
    private String projectId;

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String gettHouseId() {
        return tHouseId;
    }

    public void settHouseId(String tHouseId) {
        this.tHouseId = tHouseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


}
