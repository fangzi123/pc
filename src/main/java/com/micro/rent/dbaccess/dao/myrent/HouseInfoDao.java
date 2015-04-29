package com.micro.rent.dbaccess.dao.myrent;


import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.biz.myrent.vo.ProjectWrap;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;

import java.util.List;
import java.util.Map;

public interface HouseInfoDao {

    HouseInfo findBaseHouseInfoBytHouseId(String tHouseId);

    HouseInfo findBaseHouseInfoByHouseId(String houseId);

    /**
     * 批量导入房源
     *
     * @param houseInfo
     * @author wff
     */
    void batchSave(HouseInfo houseInfo);

    ProjectWrap statHouseByProject(HouseInfo houseInfo);//String projectId

    HouseWrapVo findHouseInfoByHouseId(String houseId);

    List<HouseVo> findNearHouseList(Map<String, Object> map);

    List<HouseWrapVo> findHousesInOneProject(String projectId);

    /**
     * 查找一套公寓下 价格最贵的一个房子的houseId
     *
     * @param projectId
     * @return
     * @author wff
     */
    String findTheMaxPriceHouseIdByComName(String ComName);

    /**
     * @return 推荐房源列表
     * @throws Exception
     * @deprecated: 检索推荐房源
     * @date 2014.12.6
     * @author zbb
     */
    List<HouseVo> searchRecommendHouseList();

    /**
     * @param map： 地理区域（经纬度的最大和最小值）
     * @return 附近房源列表
     * @throws Exception
     * @deprecated: 检索附近房源（以公寓公司为单位）
     * @date 2014.12.6
     * @author zbb
     */
    List<HouseVo> searchNearHousesUnitProject(Map<String, Object> map);

    /**
     * 取得指定项目的最大房屋编号
     *
     * @param projectId 项目ID
     * @return 最大房屋编号
     */
    String selectMaxHouseIdByProjectId(String projectId);
}
