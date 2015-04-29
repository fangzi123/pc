package com.micro.rent.biz.base.service;

import com.micro.rent.dbaccess.entity.base.RegionInfo;

import java.util.List;

public interface RegionService {

    List<RegionInfo> findReginsByParentCodeAndRegionLevel(String parentCode,
                                                          int regionLevel);

    List<RegionInfo> findDirectCityByGovnmentByParentId(String parentId);

    List<RegionInfo> findReginsByParentId(String parentId);

    List<RegionInfo> findReginsByRegionCode(String regionCode);
}
