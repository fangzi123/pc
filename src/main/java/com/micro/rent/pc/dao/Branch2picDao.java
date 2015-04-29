package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.Branch2pic;


public interface Branch2picDao {
	List<Branch2pic> findBranch2picByBranchId(Map<String, Object> map) throws Exception;
}