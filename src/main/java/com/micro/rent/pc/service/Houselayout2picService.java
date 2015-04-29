package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.Houselayout2pic;

public interface Houselayout2picService {
	List<Houselayout2pic> findHouselayout2picByBranchId(Long branchId) throws Exception;
	
	List<Houselayout2pic> findProjectHouselayout2picByBranchId(Long branchId) throws Exception;
}
