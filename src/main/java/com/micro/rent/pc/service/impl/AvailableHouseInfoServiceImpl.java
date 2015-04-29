package com.micro.rent.pc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.AvailableHouseInfoDao;
import com.micro.rent.pc.entity.vo.AvailableHouseInfoVo;
import com.micro.rent.pc.service.AvailableHouseInfoService;

@Service
public class AvailableHouseInfoServiceImpl implements AvailableHouseInfoService {

	@Autowired
	private AvailableHouseInfoDao availableHouseInfoDao;

	@Override
	public Page<AvailableHouseInfoVo> findPagedByBranchId(Page<AvailableHouseInfoVo> page)
			throws Exception {
		List<AvailableHouseInfoVo> list = availableHouseInfoDao.findPagedByBranchId(page);
		page.setResults(list);
		return page;
	}

	@Override
	public List<AvailableHouseInfoVo> findMinByBranchId(Long branchId) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("branchId", branchId);
		return availableHouseInfoDao.findMinByBranchId(map);
	}
}
