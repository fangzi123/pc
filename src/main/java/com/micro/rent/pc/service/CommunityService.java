package com.micro.rent.pc.service;

import com.micro.rent.pc.entity.Community;

public interface CommunityService {
	Community findByHouseId(Long houseId) throws Exception;
	
	Community findById(Long id) throws Exception;
}
