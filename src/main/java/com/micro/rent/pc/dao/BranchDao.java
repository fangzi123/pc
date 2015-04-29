package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.Branch;
import com.micro.rent.pc.entity.vo.BranchVo;


public interface BranchDao {
	Branch findBranchById(Map<String, Object> map) throws Exception;
	
	List<Branch> findNearBranchsByBranchId(Map<String, Object> map) throws Exception;
	
	List<Branch> findByBrandId(Map<String, Object> map) throws Exception;
	
	List<BranchVo> findByBrandIdWithPic(Map<String, Object> map) throws Exception;
	
	List<BranchVo> findDetailBrandId(Map<String, Object> map) throws Exception;
	
	List<BranchVo> findNearbyBranchByAddress(Map<String, Object> map) throws Exception;
}