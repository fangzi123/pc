package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.vo.BranchVo;

public interface BranchService {
	Branch findBranchById(Long id) throws Exception;

	List<Branch> findNearBranchsByBranchId(Long id) throws Exception;

	List<Branch> findByBrandId(Long brandId) throws Exception;

	List<BranchVo> findByBrandIdWithPic(Long brandId) throws Exception;
	
	List<BranchVo> findDetailBrandId(Long brandId) throws Exception;
	
	List<BranchVo> findNearbyBranchByAddress(Double x, Double y, Double distinct, Long brandId) throws Exception;
}
