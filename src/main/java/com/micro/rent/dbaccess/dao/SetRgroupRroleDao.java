package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetRgroupRrole;
import com.micro.rent.dbaccess.entity.SetRgroupRroleKey;


/**
 * @author
 * @version 1.0
 * @Description 资源角色组关系数据访问接口
 * @date 2013-03-01
 */

public interface SetRgroupRroleDao {

    /**
     * @param setRgroupRrole
     * @Description 插入新记录
     * @author
     */
    void insert(SetRgroupRrole setRgroupRrole);


    /**
     * @param rgroupId
     * @return
     * @Description 删除当前用户对应的所有菜单记录
     * @author
     */
    void deleteByGroupId(String rgroupId);

    /**
     * @param setRgroupRroleKey
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetRgroupRroleKey setRgroupRroleKey);


}
