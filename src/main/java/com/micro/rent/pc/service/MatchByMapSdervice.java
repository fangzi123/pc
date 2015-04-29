package com.micro.rent.pc.service;

import java.util.List;

import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.MapQueryVo;
import com.micro.rent.pc.entity.vo.MapResultVo;

public interface MatchByMapSdervice {
    /**
     * @Title: findAllCommunityByZoom
     * @Description: 查找社区
     * @return
    	* @author: wff
     * @param zoom 
     * @return: MapResultVo
     */
    MapResultVo findAllCommunityByZoom(MapQueryVo mqv) throws Exception;
    /**
     * @Title: findHousesByCommunityId
     * @Description: 查询小区房源列表
     * @param communityId
     * @return
    	* @author: wff
     * @return: List<HouseVo>
     */
    List<HouseVo> findHousesByCommunityId(Integer communityId) throws Exception;
    MapResultVo findAllCommunityByFilter(MapQueryVo mqv) throws Exception;
    /**
     * @Title: findHousesByHouseIds
     * @Description: 根据house的ID 查找房源列表
     * @param houseIds
     * @return
     * @throws Exception
    	* @author: wff
     * @return: List<HouseVo>
     */
    List<HouseVo> findHousesByHouseIds(String houseIds)throws Exception;

}
