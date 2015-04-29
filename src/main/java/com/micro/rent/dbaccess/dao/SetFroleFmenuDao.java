package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetFroleFmenu;


/**
 * @author
 * @version 1.0
 * @Description 角色菜单数据访问接口
 * @date 2013-03-01
 */

public interface SetFroleFmenuDao {

    /**
     * @param setRoleMenu
     * @Description 插入新记录
     * @author
     */
    void insert(SetFroleFmenu setRoleMenu);


    /**
     * @param roleId
     * @return
     * @Description 删除当前用户对应的所有菜单记录
     * @author
     */
    void deleteByRoleId(String roleId);

    /**
     * @param setRoleMenu
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetFroleFmenu setRoleMenu);


}
