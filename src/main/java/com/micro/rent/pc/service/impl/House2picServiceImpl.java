package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.House2picDao;
import com.micro.rent.pc.entity.House2pic;
import com.micro.rent.pc.service.House2picService;

@Service
public class House2picServiceImpl implements House2picService {
	
	@Autowired
	House2picDao house2picDao;
	
	@Override
	public List<House2pic> findByHouseId(Long houseId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		return house2picDao.findByHouseId(map);
	}

}
