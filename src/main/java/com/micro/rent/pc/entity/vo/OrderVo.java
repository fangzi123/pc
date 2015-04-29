package com.micro.rent.pc.entity.vo;

import java.util.Date;

public class OrderVo {
    private String name;
    private Integer houseId;
    private String verifyCode;
    private String mobile;// mobile
    private String orderDate;// visitingTime;
    private Date visitingTime;

    private Integer tenantId;

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
