package com.micro.rent.pc.entity.vo;

import java.util.List;

import com.micro.rent.pc.entity.AvailableHouseInfo;
import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.House2pic;
import com.micro.rent.pc.entity.PriceGroup;

public class AvailableHouseInfoVo extends AvailableHouseInfo {
	private PriceGroup priceGroup;
	private List<House2pic> house2picList;

	private Branch branch;

	private String picPath;

	public PriceGroup getPriceGroup() {
		return priceGroup;
	}

	public void setPriceGroup(PriceGroup priceGroup) {
		this.priceGroup = priceGroup;
	}

	public List<House2pic> getHouse2pic() {
		return house2picList;
	}

	public void setHouse2pic(List<House2pic> house2picList) {
		this.house2picList = house2picList;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<House2pic> getHouse2picList() {
		return house2picList;
	}

	public void setHouse2picList(List<House2pic> house2picList) {
		this.house2picList = house2picList;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
