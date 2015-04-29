package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.House2pic;

public interface House2picService {
	List<House2pic> findByHouseId(Long houseId) throws Exception;
}
