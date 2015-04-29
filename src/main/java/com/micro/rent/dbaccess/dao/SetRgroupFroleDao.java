package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetRgroupFrole;
import com.micro.rent.dbaccess.entity.SetRgroupFroleKey;

/**
 * @author
 * @version 1.0
 * @Description 功能角色组关系数据访问接口
 * @date 2013-03-01
 */

public interface SetRgroupFroleDao {

    /**
     * @param setRgroupFrole
     * @Description 插入新记录
     * @author
     */
    void insert(SetRgroupFrole setRgroupFrole);


    /**
     * @param rgroupId
     * @return
     * @Description 删除当前用户对应的所有菜单记录
     * @author
     */
    void deleteByGroupId(String rgroupId);

    /**
     * @param setRgroupFroleKey
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetRgroupFroleKey setRgroupFroleKey);


}
