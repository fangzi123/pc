package com.micro.rent.pc.dao;

import java.util.Map;

import com.micro.rent.pc.entity.Community;


public interface CommunityDao {
	Community findByHouseId(Map<String, Object> map) throws Exception;
	
	Community findById(Map<String, Object> map) throws Exception;
}