package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.CommunityDao;
import com.micro.rent.pc.entity.Community;
import com.micro.rent.pc.service.CommunityService;
import com.micro.rent.pc.util.MapBuilder;

@Service
public class CommunityServiceImpl implements CommunityService {

	@Autowired
	private CommunityDao communityDao;
	
	@Override
	public Community findByHouseId(Long houseId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", houseId);
		return communityDao.findByHouseId(map);
	}

	@Override
	public Community findById(Long id) throws Exception {
		return communityDao.findById(MapBuilder.with("id", id).build());
	}

}
