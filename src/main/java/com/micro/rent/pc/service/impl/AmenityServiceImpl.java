package com.micro.rent.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.dao.AmenityDao;
import com.micro.rent.pc.entity.Amenity;
import com.micro.rent.pc.service.AmenityService;
import com.micro.rent.pc.util.MapBuilder;

@Service
public class AmenityServiceImpl implements AmenityService {

	@Autowired
	private AmenityDao amenityDao;
	
	@Override
	public Amenity findAmenityById(Long id) throws Exception {
		return amenityDao.findAmenityById(MapBuilder.with("id", id).build());
	}

	@Override
	public List<Amenity> findAmenitysByBranchId(Long branchId) throws Exception {
		return amenityDao.findAmenityByBranchId(MapBuilder.with("branchId", branchId).build());
	}

	@Override
	public List<Amenity> findByHouseId(Long houseId) throws Exception {
		return amenityDao.findByHouseId(MapBuilder.with("houseId", houseId).build());
	}

}
