package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.vo.FeeVo;


public interface FeeDao {
	List<FeeVo> findWithPriceGroupByHouseId(Map<String, Object> map) throws Exception;
	
	List<FeeVo> findByHouseId(Map<String, Object> map) throws Exception;
}