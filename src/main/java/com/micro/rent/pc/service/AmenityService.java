package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.Amenity;

public interface AmenityService {
	Amenity findAmenityById(Long id) throws Exception;
	List<Amenity> findAmenitysByBranchId(Long branchId) throws Exception;
	List<Amenity> findByHouseId(Long houseId) throws Exception;
}
