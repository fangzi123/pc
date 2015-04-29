package com.micro.rent.pc.entity.vo;

import java.math.BigDecimal;
import java.util.Date;

public class MatchResultVo {
    /*** house**start **/
    private Integer hid;
    private String houseId;
    private Integer branchId;
    private String roomNo;
    private Double area;
    private Double availableArea;
    private String layout;// 户型：开间,一室,两室,三室,四室,五室 等
    private Integer hallNumber;
    private Integer toiletNumber;
    private Integer kitchenNumber;
    private Integer floor;
    private Integer totalFloor;
    private String orientation;// 朝向: 东,南,西,北,东南,东北,西南,西北
    private String decoration;// 装修情况：毛坯,普通装修,中等装修,精装修,豪华装修
    private Integer accommodate;
    private Date releaseTime;
    private String status;// 当前的出租状态：可出租,已预订,已出租
    private Integer paymentTypeGroupId;
    private Integer priceGroupId;
    private Integer minStayGroupId;
    private Integer amenityGroupId;// 公用设施
    private Integer feeGroupId;
    private String availableTime;// 可入住时间
    private Integer rankGroupId;
    private Integer addressId;
    private String flag;

    /** house**end **/
    private String picPath;// 房源图片
    private String communityName;// 公寓名称
    private String branchName;// 门店名称
    private String brandName;// 品牌名称
    private String address;// 房源地址
    private BigDecimal monthlyPrice;// 月租金
    private String point;// 地理坐标
    private String phone;// 联系电话
    private String paymentType;// 支付类型
    private Integer months;// 最短租期（月）
    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Double getAvailableArea() {
        return availableArea;
    }

    public void setAvailableArea(Double availableArea) {
        this.availableArea = availableArea;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public Integer getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(Integer hallNumber) {
        this.hallNumber = hallNumber;
    }

    public Integer getToiletNumber() {
        return toiletNumber;
    }

    public void setToiletNumber(Integer toiletNumber) {
        this.toiletNumber = toiletNumber;
    }

    public Integer getKitchenNumber() {
        return kitchenNumber;
    }

    public void setKitchenNumber(Integer kitchenNumber) {
        this.kitchenNumber = kitchenNumber;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(Integer totalFloor) {
        this.totalFloor = totalFloor;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getDecoration() {
        return decoration;
    }

    public void setDecoration(String decoration) {
        this.decoration = decoration;
    }

    public Integer getAccommodate() {
        return accommodate;
    }

    public void setAccommodate(Integer accommodate) {
        this.accommodate = accommodate;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPaymentTypeGroupId() {
        return paymentTypeGroupId;
    }

    public void setPaymentTypeGroupId(Integer paymentTypeGroupId) {
        this.paymentTypeGroupId = paymentTypeGroupId;
    }

    public Integer getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Integer priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Integer getMinStayGroupId() {
        return minStayGroupId;
    }

    public void setMinStayGroupId(Integer minStayGroupId) {
        this.minStayGroupId = minStayGroupId;
    }

    public Integer getAmenityGroupId() {
        return amenityGroupId;
    }

    public void setAmenityGroupId(Integer amenityGroupId) {
        this.amenityGroupId = amenityGroupId;
    }

    public Integer getFeeGroupId() {
        return feeGroupId;
    }

    public void setFeeGroupId(Integer feeGroupId) {
        this.feeGroupId = feeGroupId;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public Integer getRankGroupId() {
        return rankGroupId;
    }

    public void setRankGroupId(Integer rankGroupId) {
        this.rankGroupId = rankGroupId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(BigDecimal monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public String[] getHousePics() {
        return getPicPath().split(";");
    }
    public String getLng() {
        String lng= getPoint().split(",")[0];
        return lng.substring(1);
    }
    public String getLat() {
        String lat=getPoint().split(",")[1];
        return lat.substring(0,lat.length()-1);
    }

}
