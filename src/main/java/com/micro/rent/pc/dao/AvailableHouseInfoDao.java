package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.AvailableHouseInfoVo;


public interface AvailableHouseInfoDao {
    List<AvailableHouseInfoVo> findPagedByBranchId(Page<AvailableHouseInfoVo> page) throws Exception;
    
    List<AvailableHouseInfoVo> findMinByBranchId(Map<String, Object> map) throws Exception;
}