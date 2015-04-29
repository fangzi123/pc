package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.Houselayout2pic;


public interface Houselayout2picDao {
	
	List<Houselayout2pic> findHouselayout2picByBranchId(Map<String, Object> map) throws Exception;
	
	List<Houselayout2pic> findProjectHouselayout2picByBranchId(Map<String, Object> map) throws Exception;
}