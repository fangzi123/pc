package com.micro.rent.biz.myrent.vo;

import java.math.BigDecimal;

public class ProjectMapVo {
    private String projectId;
    private String communityName;
    private int houses;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String provinceCode;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getHouses() {
        return houses;
    }

    public void setHouses(int house) {
        this.houses = house;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }


}
