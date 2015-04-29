package com.micro.rent.pc.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: House
 * @Description: 房源表
 * @author: wff
 * @date: 2015年1月20日 下午3:28:22
 */
public class House {
    private Long id;
    private String houseId;
    private Long branchId;
    private String roomNo;
    private BigDecimal area;
    private BigDecimal availableArea;
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
    private Date availableTime;
    private Integer rankGroupId;
    private Integer addressId;
    private String flag;

    public static final String FAVORITE_HOUSE = "favorite_house";
    public static final String PREORDER_HOUSE = "preOrder_house";
    public static final String PREORDER_MOBILE = "preorder_mobile";

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
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

    public Date getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(Date availableTime) {
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

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getAvailableArea() {
        return availableArea;
    }

    public void setAvailableArea(BigDecimal availableArea) {
        this.availableArea = availableArea;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }
}
