package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.BranchDao;
import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.vo.BranchVo;
import com.micro.rent.pc.service.BranchService;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Override
	public Branch findBranchById(Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return branchDao.findBranchById(map);
	}

	@Override
	public List<Branch> findNearBranchsByBranchId(Long id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return branchDao.findNearBranchsByBranchId(map);
	}

	@Override
	public List<Branch> findByBrandId(Long brandId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		return branchDao.findByBrandId(map);
	}

	@Override
	public List<BranchVo> findByBrandIdWithPic(Long brandId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		return branchDao.findByBrandIdWithPic(map);
	}

	@Override
	public List<BranchVo> findDetailBrandId(Long brandId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("brandId", brandId);
		return branchDao.findDetailBrandId(map);
	}
	
	@Override
	public List<BranchVo> findNearbyBranchByAddress(Double x, Double y, Double distinct, Long brandId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("x", x);
		map.put("y", y);
		map.put("distinct", distinct);
		map.put("brandId", brandId);
		return branchDao.findNearbyBranchByAddress(map);
	}

}
