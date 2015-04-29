package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.vo.BrandVo;

public interface NearHouseService {
	List<BrandVo> findNearBrandByAddressBrand(Double x, Double y, Long brandId) throws Exception;
}
