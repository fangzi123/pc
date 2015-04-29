package com.micro.rent.pc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.MapQueryVo;
import com.micro.rent.pc.entity.vo.Mark;

public interface MatchByMapDao {

    List<Mark> findAllCommunityByZoom(MapQueryVo mqv) throws Exception;

    List<HouseVo> findHousesByCommunityId(Integer communityId) throws Exception;

    List<Mark> findAllCommunityByFilter(MapQueryVo mqv) throws Exception;

    List<HouseVo> findHousesByHouseIds(@Param(value = "houseIds") String houseIds) throws Exception;

}
