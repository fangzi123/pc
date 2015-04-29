package com.micro.rent.biz.myrent.vo;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.math.BigDecimal;

public class MyRentQueryVo {
    @XStreamOmitField
    private String provinceCode;
    private String cityCode;
    private String cityName;
    @XStreamOmitField
    private String districtCode;
    private String workPlace;//我的位置
    private String trafficType;//上班方式
    private BigDecimal longPrice;//租金上线
    private String rentalType;

    private String projectId;//项目编号
    private String layout;//户型   house表
    private BigDecimal longitude; //经度
    private BigDecimal latitude;//纬度
    private String weixinId;//微信id
    private String communityName;//社区名称

    private int stackIndex;

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getTrafficType() {
        return trafficType;
    }

    public void setTrafficType(String trafficType) {
        this.trafficType = trafficType;
    }

    public BigDecimal getLongPrice() {
        return longPrice;
    }

    public void setLongPrice(BigDecimal longPrice) {
        this.longPrice = longPrice;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public final String getProjectId() {
        return projectId;
    }

    public final void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public final String getLayout() {
        return layout;
    }

    public final void setLayout(String layout) {
        this.layout = layout;
    }

    public final BigDecimal getLongitude() {
        return longitude;
    }

    public final void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public final BigDecimal getLatitude() {
        return latitude;
    }

    public final void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public int getStackIndex() {
        return stackIndex;
    }

    public void setStackIndex(int stackIndex) {
        this.stackIndex = stackIndex;
    }

    public final String getWeixinId() {
        return weixinId;
    }

    public final void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public final String getCommunityName() {
        return communityName;
    }

    public final void setCommunityName(String communityName) {
        this.communityName = communityName;
    }


}
