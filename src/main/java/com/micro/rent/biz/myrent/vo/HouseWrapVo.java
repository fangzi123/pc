package com.micro.rent.biz.myrent.vo;

import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;

import java.math.BigDecimal;
import java.util.List;

public class HouseWrapVo {
    private String houseId;
    private String projectId;
    private String street;
    private String communityName;
    private String doorplate;
    private String buildingNo;
    private String roomNumber;
    private String area;
    private String useArea;
    private String layout;
    private Integer hallQuantity;
    private Integer toiletQuantity;
    private Integer kitchenQuantity;
    private String orientation;
    private BigDecimal longPrice;
    private BigDecimal shortPrice;
    private String paymentType;
    private String renovation;
    private String telephone;
    private String brandName;

    /**
     * 最短租期
     */
    private int shortestPeriod;

    /**
     * 公司描述(暂用于社区信息描述)
     */
    private String companyDesc;

    private String picture;//图片

    private List<ThousePic> imgList;

    // 最短租约
    //

    // private String houseDesc;

    private List<HouseVo> nearHouseList;

    private String projectDesc;
    private BigDecimal latitude;
    private BigDecimal longitude;

    private String duration;

    public HouseWrapVo() {
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDoorplate() {
        return doorplate;
    }

    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
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

    public String getUseArea() {
        return useArea;
    }

    public void setUseArea(String useArea) {
        this.useArea = useArea;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Integer getHallQuantity() {
        return hallQuantity;
    }

    public void setHallQuantity(Integer hallQuantity) {
        this.hallQuantity = hallQuantity;
    }

    public Integer getToiletQuantity() {
        return toiletQuantity;
    }

    public void setToiletQuantity(Integer toiletQuantity) {
        this.toiletQuantity = toiletQuantity;
    }

    public Integer getKitchenQuantity() {
        return kitchenQuantity;
    }

    public void setKitchenQuantity(Integer kitchenQuantity) {
        this.kitchenQuantity = kitchenQuantity;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public BigDecimal getLongPrice() {
        return longPrice;
    }

    public void setLongPrice(BigDecimal longPrice) {
        this.longPrice = longPrice;
    }

    public BigDecimal getShortPrice() {
        return shortPrice;
    }

    public void setShortPrice(BigDecimal shortPrice) {
        this.shortPrice = shortPrice;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRenovation() {
        return renovation;
    }

    public void setRenovation(String renovation) {
        this.renovation = renovation;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<ThousePic> getImgList() {
        return imgList;
    }

    public void setImgList(List<ThousePic> imgList) {
        this.imgList = imgList;
    }

    public List<HouseVo> getNearHouseList() {
        return nearHouseList;
    }

    public void setNearHouseList(List<HouseVo> nearHouseList) {
        this.nearHouseList = nearHouseList;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public final String getPicture() {
        return picture;
    }

    public final void setPicture(String picture) {
        this.picture = picture;
    }

    public final String getTelephone() {
        return telephone;
    }

    public final void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public final String getBrandName() {
        return brandName;
    }

    public final void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * 取得公司描述(暂用于社区信息描述)
     *
     * @return 公司描述(暂用于社区信息描述)
     */
    public String getCompanyDesc() {
        return companyDesc;
    }

    /**
     * 设定公司描述(暂用于社区信息描述)
     */
    public void setCompanyDesc(String comanyDesc) {
        this.companyDesc = comanyDesc;
    }

    /**
     * 取得最短租期
     *
     * @return 最短租期
     */
    public int getShortestPeriod() {
        return shortestPeriod;
    }

    /**
     * 设定最短租期
     *
     * @param shortestPeriod
     */
    public void setShortestPeriod(int shortestPeriod) {
        this.shortestPeriod = shortestPeriod;
    }
}
