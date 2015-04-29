package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.Branch2picDao;
import com.micro.rent.pc.entity.Branch2pic;
import com.micro.rent.pc.service.Branch2picService;

@Service
public class Branch2picServiceImpl implements Branch2picService {
	
	@Autowired
	private Branch2picDao branch2picDao;

	@Override
	public List<Branch2pic> findBranch2picByBranchId(Long branchId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return branch2picDao.findBranch2picByBranchId(map);
	}

}
