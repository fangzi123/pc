package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetRroleResource;

/**
 * @author
 * @version 1.0
 * @Description 角色资源数据访问接口
 * @date 2013-03-01
 */

public interface SetRroleResourceDao {

    /**
     * @param setRroleResource
     * @Description 插入新记录
     * @author
     */
    void insert(SetRroleResource setRroleResource);


    /**
     * @param roleId
     * @return
     * @Description 删除当前用户对应的所有菜单记录
     * @author
     */
    void deleteByRoleId(String roleId);

    /**
     * @param setRroleResource
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetRroleResource setRroleResource);

}
