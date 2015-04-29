package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.PriceGroupDao;
import com.micro.rent.pc.entity.PriceGroup;
import com.micro.rent.pc.service.PriceGroupService;

@Service
public class PriceGroupServiceImpl implements PriceGroupService {

	@Autowired
	private PriceGroupDao priceGroupDao;
	
	@Override
	public List<PriceGroup> findByBranchId(Long branchId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return priceGroupDao.findByBranchId(map);
	}

	@Override
	public PriceGroup findByHouseId(Long houseId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		return priceGroupDao.findByHouseId(map);
	}
	
	

}
