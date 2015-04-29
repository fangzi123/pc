package com.micro.rent.pc.entity.vo;

import java.util.List;

import com.micro.rent.pc.entity.Address;
import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.Contact;

public class BranchVo extends Branch {

	private String picPath;

	private java.math.BigDecimal monthlyPrice;

	private Address address;

	private Contact contact;
	
	private List availableHouseInfoList;

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public java.math.BigDecimal getMonthlyPrice() {
		return monthlyPrice;
	}

	public void setMonthlyPrice(java.math.BigDecimal monthlyPrice) {
		this.monthlyPrice = monthlyPrice;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public List getAvailableHouseInfoList() {
		return availableHouseInfoList;
	}

	public void setAvailableHouseInfoList(List availableHouseInfoList) {
		this.availableHouseInfoList = availableHouseInfoList;
	}
	
}
