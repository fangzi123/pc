package com.micro.rent.biz.myrent.vo;

import java.math.BigDecimal;

public class MatchResultVo {
    /**
     * 公寓项目Id
     */
    private String projectId;
    /**
     * 公寓房源编号ID
     */
    private String tHouseId;

    private String houseId;

    /**
     * 小区名称
     */
    private String communityName;
    /**
     * 地理坐标：经度
     */
    private BigDecimal longitude;
    /**
     * 地理坐标：纬度
     */
    private BigDecimal latitude;

    private String provinceCode;
    private String cityCode;
    private String districtCode;
    /**
     * 地址 楼号
     */
    private String buildingNO;
    /**
     * 地址 室号
     */
    private String roomNumber;
    /**
     * 卧室数量
     */
    private String layout;
    /**
     * 装修 类型
     */
    private String renovation;
    /**
     * 时间（分）
     */
    private String duration;


    private BigDecimal longPrice;//租金价格
    private String houPicture;//房源图片路径
    private BigDecimal useArea;//房屋面积
    private BigDecimal hallQuantity;//厅数量
    private BigDecimal doorplate;//房屋单元

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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

    public String getBuildingNO() {
        return buildingNO;
    }

    public void setBuildingNO(String buildingNO) {
        this.buildingNO = buildingNO;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String gettHouseId() {
        return tHouseId;
    }

    public void settHouseId(String tHouseId) {
        this.tHouseId = tHouseId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public BigDecimal getLongPrice() {
        return longPrice;
    }

    public void setLongPrice(BigDecimal longPrice) {
        this.longPrice = longPrice;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public final BigDecimal getUseArea() {
        return useArea;
    }

    public final void setUseArea(BigDecimal useArea) {
        this.useArea = useArea;
    }

    public final BigDecimal getHallQuantity() {
        return hallQuantity;
    }

    public final void setHallQuantity(BigDecimal hallQuantity) {
        this.hallQuantity = hallQuantity;
    }

    public final String getHouPicture() {
        return houPicture;
    }

    public final void setHouPicture(String houPicture) {
        this.houPicture = houPicture;
    }

    public final BigDecimal getDoorplate() {
        return doorplate;
    }

    public final void setDoorplate(BigDecimal doorplate) {
        this.doorplate = doorplate;
    }

}
