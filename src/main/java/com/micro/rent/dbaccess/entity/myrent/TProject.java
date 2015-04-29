package com.micro.rent.dbaccess.entity.myrent;

import java.math.BigDecimal;

public class TProject {
    private String tProjectId;

    private String projectId;

    private String brandName;

    private String projectDesc;

    private String companyDesc;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String provinceId;

    private String cityCode;

    private String districtCode;

    private String street;

    private String doorplate;

    private String communityName;

    private String constructionDate;

    private Integer quantity;

    private String category;

    private BigDecimal totalFloor;

    private Integer elevatorQuantity;

    private String planNo;

    public String gettProjectId() {
        return tProjectId;
    }

    public void settProjectId(String tProjectId) {
        this.tProjectId = tProjectId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDoorplate() {
        return doorplate;
    }

    public void setDoorplate(String doorplate) {
        this.doorplate = doorplate;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(String constructionDate) {
        this.constructionDate = constructionDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getTotalFloor() {
        return totalFloor;
    }

    public void setTotalFloor(BigDecimal totalFloor) {
        this.totalFloor = totalFloor;
    }

    public Integer getElevatorQuantity() {
        return elevatorQuantity;
    }

    public void setElevatorQuantity(Integer elevatorQuantity) {
        this.elevatorQuantity = elevatorQuantity;
    }

    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }
}