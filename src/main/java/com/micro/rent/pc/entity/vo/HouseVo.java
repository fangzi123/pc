package com.micro.rent.pc.entity.vo;

import java.math.BigDecimal;

import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.House;
import com.micro.rent.pc.entity.MinStayGroup;
import com.micro.rent.pc.entity.PaymentTypeGroup;
import com.micro.rent.pc.entity.PriceGroup;

public class HouseVo extends House{
    private BigDecimal price;
   // private String address;
    private String communityName;
    private String branchName;
    private String brandName;
    private String phone;
    private String picPath;
    private Integer totalNumber;
    
    private Address address;
    
    private Branch branch;
    
    private Brand brand;
    
    private PaymentTypeGroup paymentTypeGroup;
    
    private MinStayGroup minStayGroup;
    
    private PriceGroup priceGroup;
    
    /* 用户昵称 */
    private String nickname;
    /* 用户手机号&用户账号 */
    private String mobile;
    /* 月租价 */
    private String monthlyPrice;
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public PaymentTypeGroup getPaymentTypeGroup() {
        return paymentTypeGroup;
    }

    public void setPaymentTypeGroup(PaymentTypeGroup paymentTypeGroup) {
        this.paymentTypeGroup = paymentTypeGroup;
    }

    public MinStayGroup getMinStayGroup() {
        return minStayGroup;
    }

    public void setMinStayGroup(MinStayGroup minStayGroup) {
        this.minStayGroup = minStayGroup;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public PriceGroup getPriceGroup() {
        return priceGroup;
    }

    public void setPriceGroup(PriceGroup priceGroup) {
        this.priceGroup = priceGroup;
    }
    
}
