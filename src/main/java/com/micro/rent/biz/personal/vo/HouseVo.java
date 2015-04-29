package com.micro.rent.biz.personal.vo;

import java.math.BigDecimal;

public class HouseVo {
    private String houseId;
    private String communityName;
    private String buildingNo;
    private String roomNumber;
    private String layout;
    private String renovation;
    private BigDecimal longPrice;
    private BigDecimal useArea;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String orderId;
    private String collectId;
    private String duration;
    private String weixinId;
    private Integer hallQuantity;
    /* 面积 */
    private BigDecimal area;
    /* 公寓品牌  */
    private String brandName;
    /* 图片路径 */
    private String picture;


    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    public BigDecimal getLongPrice() {
        return longPrice;
    }

    public void setLongPrice(BigDecimal longPrice) {
        this.longPrice = longPrice;
    }

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public Integer getHallQuantity() {
        return hallQuantity;
    }

    public void setHallQuantity(Integer hallQuantity) {
        this.hallQuantity = hallQuantity;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
