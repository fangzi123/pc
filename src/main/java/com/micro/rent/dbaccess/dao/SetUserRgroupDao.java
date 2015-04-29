package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetUserRgroup;
import com.micro.rent.dbaccess.entity.SetUserRgroupKey;


/**
 * @author
 * @version 1.0
 * @Description 用户组关系数据访问接口
 * @date 2013-03-01
 */

public interface SetUserRgroupDao {

    /**
     * @param setUserRgroup
     * @Description 插入新记录
     * @author
     */
    void insert(SetUserRgroup setUserRgroup);


    /**
     * @param userId
     * @return
     * @Description 删除当前用户对应的所有组记录
     * @author
     */
    void deleteByUserId(String userId);

    /**
     * @param setRgroupRoleKey
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetUserRgroupKey setUserRgroupKey);


}
