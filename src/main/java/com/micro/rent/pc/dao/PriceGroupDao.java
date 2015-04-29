package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.PriceGroup;


public interface PriceGroupDao {
	
	List<PriceGroup> findByBranchId(Map<String, Object> map) throws Exception;
	
	PriceGroup findByHouseId(Map<String, Object> map) throws Exception;
}