package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.AvailableHouseInfoVo;

public interface AvailableHouseInfoService {
	Page<AvailableHouseInfoVo> findPagedByBranchId(Page<AvailableHouseInfoVo> page) throws Exception;
	
	List<AvailableHouseInfoVo> findMinByBranchId(Long branchId) throws Exception;
}
