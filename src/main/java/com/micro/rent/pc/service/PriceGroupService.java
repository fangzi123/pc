package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.PriceGroup;

public interface PriceGroupService {
	List<PriceGroup> findByBranchId(Long branchId) throws Exception;
	
	PriceGroup findByHouseId(Long houseId) throws Exception;
}
