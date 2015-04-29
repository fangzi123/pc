package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.pc.entity.Amenity;


public interface AmenityDao {
    List<Amenity> findAmenityByBranchId(Map<String, Object> map) throws Exception;

	Amenity findAmenityById(Map<String, Object> map) throws Exception;
	
	List<Amenity> findByHouseId(Map<String, Object> map) throws Exception;
}