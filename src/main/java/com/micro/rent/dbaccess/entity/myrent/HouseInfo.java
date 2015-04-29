package com.micro.rent.dbaccess.entity.myrent;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class HouseInfo {

    /* 房源图片列表 */
    List<String> pictures;
    /* 房源某张图片 */
    String picture;
    /* 所属公寓品牌 */
    String brandName;
    private String tHouseId;
    private String houseId;
    private String projectId;
    private String status;
    private String rentBeginDate;
    private String rentEndDate;
    private String rentAvailPeriod;
    private String provinceId;
    private String cityCode;
    private String districtCode;
    private String communityName;
    private String doorplate;
    private String buildingNo;
    private String roomNumber;
    private BigDecimal useArea;
    private String layout;
    private Integer hallQuantity;
    private Integer toiletQuantity;
    private Integer kitchenQuantity;
    private BigDecimal floor;
    private BigDecimal totalFloor;
    private String orientation;
    private BigDecimal area;
    private BigDecimal longPrice;
    private BigDecimal shortPrice;
    private String paymentType;
    private String renovation;
    private String furniture;
    private String appliances;
    private String tv;
    private String internet;
    private BigDecimal waterPrice;
    private BigDecimal electricPrice;
    private BigDecimal warmPrice;
    private BigDecimal refrigerationPrice;
    private BigDecimal gasPrice;
    private Date releaseTime;
    private String rentalType;
    private String projectDesc;
    private String companyDesc;
    private String longitude;
    private String latitude;
    private String mapImg;
    private String weixinId;
    private String telephone;
    // 是否推荐房源（0：不是/1：是）
    private Integer flag;

    public String gettHouseId() {
        return tHouseId;
    }

    public void settHouseId(String tHouseId) {
        this.tHouseId = tHouseId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRentBeginDate() {
        return rentBeginDate;
    }

    public void setRentBeginDate(String rentBeginDate) {
        this.rentBeginDate = rentBeginDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public String getRentAvailPeriod() {
        return rentAvailPeriod;
    }

    public void setRentAvailPeriod(String rentAvailPeriod) {
        this.rentAvailPeriod = rentAvailPeriod;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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

    public BigDecimal getUseArea() {
        return useArea;
    }

    public void setUseArea(BigDecimal useArea) {
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

    public BigDecimal getFloor() {
        return floor;
    }

    public void setFloor(BigDecimal floor) {
        this.floor = floor;
    }

    public BigDecimal getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(BigDecimal totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
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

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getAppliances() {
        return appliances;
    }

    public void setAppliances(String appliances) {
        this.appliances = appliances;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public BigDecimal getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(BigDecimal waterPrice) {
        this.waterPrice = waterPrice;
    }

    public BigDecimal getElectricPrice() {
        return electricPrice;
    }

    public void setElectricPrice(BigDecimal electricPrice) {
        this.electricPrice = electricPrice;
    }

    public BigDecimal getWarmPrice() {
        return warmPrice;
    }

    public void setWarmPrice(BigDecimal warmPrice) {
        this.warmPrice = warmPrice;
    }

    public BigDecimal getRefrigerationPrice() {
        return refrigerationPrice;
    }

    public void setRefrigerationPrice(BigDecimal refrigerationPrice) {
        this.refrigerationPrice = refrigerationPrice;
    }

    public BigDecimal getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(BigDecimal gasPrice) {
        this.gasPrice = gasPrice;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getMapImg() {
        return mapImg;
    }

    public void setMapImg(String mapImg) {
        this.mapImg = mapImg;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pirctures) {
        this.pictures = pirctures;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String pircture) {
        this.picture = pircture;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
