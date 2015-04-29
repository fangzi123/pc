package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.vo.FeeVo;

public interface FeeService {
	List<FeeVo> findWithPriceGroupByHouseId(Long houseId) throws Exception;
	
	List<FeeVo> findByHouseId(Long houseId) throws Exception;
}
