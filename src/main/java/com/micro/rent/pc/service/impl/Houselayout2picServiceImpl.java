package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.Houselayout2picDao;
import com.micro.rent.pc.entity.Houselayout2pic;
import com.micro.rent.pc.service.Houselayout2picService;

@Service
public class Houselayout2picServiceImpl implements Houselayout2picService {

	@Autowired
	private Houselayout2picDao houselayout2picDao;
	
	@Override
	public List<Houselayout2pic> findHouselayout2picByBranchId(Long branchId)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return this.houselayout2picDao.findHouselayout2picByBranchId(map);
	}

	@Override
	public List<Houselayout2pic> findProjectHouselayout2picByBranchId(Long branchId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return houselayout2picDao.findProjectHouselayout2picByBranchId(map);
	}
	
	

}
