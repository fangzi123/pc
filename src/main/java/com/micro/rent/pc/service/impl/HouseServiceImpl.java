package com.micro.rent.pc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.HouseDao;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.service.HouseService;
import com.micro.rent.pc.util.MapBuilder;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseDao houseDao;
	
	@Override
	public HouseVo findWithBranchBrandById(Long houseId) throws Exception {
		return houseDao.findWithBranchBrandById(MapBuilder.with("houseId", houseId).build());
	}

	@Override
	public HouseVo findWithAddressById(Long houseId) throws Exception {
		return houseDao.findWithAddressById(MapBuilder.with("houseId", houseId).build());
	}

	/**
     * 描述: 通过房源ID批量检索房源信息(分页)
     * @param page 
     * @return 房源列表
     * @throws Exception
     */
    public Page<HouseVo> searchHousesByhouseIds(Page<HouseVo> page) throws Exception {
        page.setResults(houseDao.searchHousesByhouseIds(page));
        return page;
    };
}
