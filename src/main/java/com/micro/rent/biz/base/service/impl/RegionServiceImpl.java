package com.micro.rent.biz.base.service.impl;

import com.micro.rent.biz.base.service.RegionService;
import com.micro.rent.dbaccess.dao.base.RegionDao;
import com.micro.rent.dbaccess.entity.base.RegionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionDao regionDao;

    public List<RegionInfo> findReginsByParentCodeAndRegionLevel(
            String parentCode, int regionLevel) {

        return regionDao.findReginsByParentCodeAndRegionLevel(parentCode,
                regionLevel);
    }

    public List<RegionInfo> findReginsByParentId(String parentId) {
        return regionDao.findReginsByParentId(parentId);
    }

    public List<RegionInfo> findDirectCityByGovnmentByParentId(String parentId) {
        return regionDao.findDirectCityByGovnmentByParentId(parentId);
    }

    public List<RegionInfo> findReginsByRegionCode(String regionCode) {
        return regionDao.findReginsByRegionCode(regionCode);
    }
}
