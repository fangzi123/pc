package com.micro.rent.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.FeeDao;
import com.micro.rent.pc.entity.vo.FeeVo;
import com.micro.rent.pc.service.FeeService;
import com.micro.rent.pc.util.MapBuilder;

@Service
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeeDao feeDao;

	@Override
	public List<FeeVo> findWithPriceGroupByHouseId(Long houseId) throws Exception {
		return feeDao.findWithPriceGroupByHouseId(MapBuilder.with("houseId", houseId).build());
	}
	
	@Override
	public List<FeeVo> findByHouseId(Long houseId) throws Exception {
		return feeDao.findByHouseId(MapBuilder.with("houseId", houseId).build());
	}

}
