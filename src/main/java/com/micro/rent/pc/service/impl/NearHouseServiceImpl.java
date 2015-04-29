package com.micro.rent.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.pc.entity.vo.AvailableHouseInfoVo;
import com.micro.rent.pc.entity.vo.BranchVo;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.AvailableHouseInfoService;
import com.micro.rent.pc.service.BranchService;
import com.micro.rent.pc.service.BrandService;
import com.micro.rent.pc.service.NearHouseService;
import com.micro.rent.pc.util.GeometryUtil;

@Service
public class NearHouseServiceImpl implements NearHouseService {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private AvailableHouseInfoService availableHouseInfoService;

	@Override
	public List<BrandVo> findNearBrandByAddressBrand(Double x, Double y, Long brandId) throws Exception {
		
		int i = 1;
		Double distinct = GeometryUtil.getRadiusLat(1000);
		List<BrandVo> brandVoList = brandService.findNearbyBrandByAddress(x, y, distinct, brandId);
		while (brandVoList == null ||  brandVoList.size() < 1) {
			i = i + 1;
			distinct = GeometryUtil.getRadiusLat(1000 * i);
			brandVoList = brandService.findNearbyBrandByAddress(x, y, distinct, brandId);
		}
		
		for (BrandVo brandVo : brandVoList) {
			List<BranchVo> branchVoList = branchService.findNearbyBranchByAddress(x, y, distinct, brandVo.getId());
			brandVo.setBranchList(branchVoList);
			if (branchVoList != null && branchVoList.size() > 0) {
				for (BranchVo branchVo : branchVoList) {
					List<AvailableHouseInfoVo> availableHouseInfoVoList = availableHouseInfoService.findMinByBranchId(branchVo.getId());
					branchVo.setAvailableHouseInfoList(availableHouseInfoVoList);
				}
			}
		}
		return brandVoList;
	}

}
