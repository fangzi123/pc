package com.micro.rent.pc.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.HouseVo;

public interface HouseService {
	HouseVo findWithBranchBrandById(Long houseId) throws Exception;

	HouseVo findWithAddressById(Long houseId) throws Exception;
	
	/**
     * 描述: 通过房源ID批量检索房源信息(分页)
     * @param page 
     * @return 房源列表
     * @throws Exception
     */
	Page<HouseVo> searchHousesByhouseIds(Page<HouseVo> page) throws Exception;
}
