package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.HouseVo;


public interface HouseDao {
	HouseVo findWithBranchBrandById(Map<String, Object> map) throws Exception;
	
	HouseVo findWithAddressById(Map<String, Object> map) throws Exception;
	
	/**
	 * 描述: 通过房源ID批量检索房源信息(分页)
	 * @param page 
	 * @return 房源列表
	 * @throws Exception
	 */
	List<HouseVo> searchHousesByhouseIds(Page<HouseVo> page) throws Exception;
}