package com.micro.rent.dbaccess.dao.base;

import com.micro.rent.dbaccess.entity.base.RegionInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegionDao {

    /**
     * 根据父级编码和地区等级查询地区信息
     *
     * @param parentCode
     * @param regionLevel
     * @return
     */
    List<RegionInfo> findReginsByParentCodeAndRegionLevel(@Param("parentId") String parentId,
                                                          @Param("regionLevel") int regionLevel);

    List<RegionInfo> findReginsByParentId(@Param("parentId") String parentId);

    List<RegionInfo> findDirectCityByGovnmentByParentId(@Param("parentId") String parentId);

    /**
     * 根据行政区划代码查询地区信息
     *
     * @param regionCode
     * @return
     */
    List<RegionInfo> findReginsByRegionCode(@Param("regionCode") String regionCode);
}
