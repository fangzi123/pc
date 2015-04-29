package com.micro.rent.pc.entity;

import java.math.BigDecimal;

public class Fee {
    private Long id;

    private Long feeGroupId;

    private Object type;

    private BigDecimal price;

    private FeeGroup feeGroup;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeeGroupId() {
        return feeGroupId;
    }

    public void setFeeGroupId(Long feeGroupId) {
        this.feeGroupId = feeGroupId;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }


	public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public FeeGroup getFeeGroup() {
    	return feeGroup;
    }
    
    public void setFeeGroup(FeeGroup feeGroup) {
    	this.feeGroup = feeGroup;
    }
    
}