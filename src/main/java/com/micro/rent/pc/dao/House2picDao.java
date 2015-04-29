package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.House2pic;


public interface House2picDao {
	List<House2pic> findByHouseId(Map<String, Object> map) throws Exception;
}