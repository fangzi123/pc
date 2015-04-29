package com.micro.rent.pc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.ActivityDao;
import com.micro.rent.pc.entity.vo.ActivityVo;
import com.micro.rent.pc.service.ActivityService;

/**
 * 描述: 活动相关操作
 * @author zbb
 */
@Service
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityDao activityDao;
	
	/**
     * 描述: 检索个人参加的活动
     * @param page 检索信息（分页）
     * @return 活动列表
     * @throws Exception
     */
    public Page<ActivityVo> selectActivityByTenantId(Page<ActivityVo> page) 
            throws Exception {
        List<ActivityVo> activityList = activityDao.selectActivityByTenantId(page);
        page.setResults(activityList);
        return page;
    }
    
    /**
     * 描述: 删除个人参加的活动
     * @param 活动ID&个人ID
     * @throws Exception
     */
    public void deleteActivity(Map<String, String> paramMap) 
            throws Exception {
        activityDao.deleteActivity(paramMap);
    }
}
