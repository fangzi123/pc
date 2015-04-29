package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.HouseVo;

/**
 * 描述: 房屋预约DAO
 * @author zbb
 * @date 2015-3-4
 */
public interface TenantOrderDao {

    /**
     * 功能: 检索个人预约的房源
     * @mobile: 个人账号
     * @author zbb
     * @date 2015-3-4
     */
    List<HouseVo> selectOrderedHouseInfo(Page<HouseVo> page);
    
    /**
     * 功能: 删除预约房源
     * @author zbb
     * @date 2015-3-4
     */
    void deleteOrderedHouse(Map<String, String> paramMap);
}
