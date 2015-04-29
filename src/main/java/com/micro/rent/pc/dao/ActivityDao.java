package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.vo.ActivityVo;


public interface ActivityDao {
    
    /**
     * 描述: 检索个人参加的活动
     * @param page 检索信息（分页）
     * @return 活动列表
     * @throws Exception
     */
    public List<ActivityVo> selectActivityByTenantId(Page<ActivityVo> page) throws Exception;
    
    /**
     * 描述: 删除个人参加的活动
     * @param 活动ID&个人ID
     * @throws Exception
     */
    public void deleteActivity(Map<String, String> paramMap) throws Exception;
}