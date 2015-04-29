package com.micro.rent.pc.entity;

import java.math.BigDecimal;

public class AvailableHouseInfo {
    private Long id;

    private Long branchId;

    private Long projectId;

    private Object layout;

    private BigDecimal area;

    private Long priceGroupId;

    private Integer totalNumber;

    private Boolean flag;
    
    private Long houseId;

    public Long getId() {
        return id;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Object getLayout() {
        return layout;
    }

    public void setLayout(Object layout) {
        this.layout = layout;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public Long getPriceGroupId() {
        return priceGroupId;
    }

    public void setPriceGroupId(Long priceGroupId) {
        this.priceGroupId = priceGroupId;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
    
}