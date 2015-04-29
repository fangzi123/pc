package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetFuncMenu;

import java.util.List;


public interface DefaultDao {
    /**
     * @return
     * @Description 查找所有有效菜单
     * @author
     */
    List<SetFuncMenu> findAllMenu();

    /**
     * @param username
     * @return
     * @Description 通过用户名称查找有效菜单
     * @author
     */
    List<SetFuncMenu> findMenuByUserName(String username);
}
