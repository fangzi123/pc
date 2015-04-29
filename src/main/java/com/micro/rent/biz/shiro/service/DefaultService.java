package com.micro.rent.biz.shiro.service;

import com.micro.rent.dbaccess.entity.SetFuncMenu;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @Description
 * @date 2013-2-27
 */
public interface DefaultService {
    /**
     * @return
     * @Description 获取所有有效菜单
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
