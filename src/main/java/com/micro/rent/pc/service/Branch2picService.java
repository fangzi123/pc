package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.Branch2pic;

public interface Branch2picService {
	List<Branch2pic> findBranch2picByBranchId(Long id) throws Exception;
}
