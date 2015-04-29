package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SFilter;
import com.micro.rent.dbaccess.entity.SetResource;
import com.micro.rent.dbaccess.entity.SetUser;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @Description Shiro用户数据访问
 * @date 2013-2-5
 */

public interface ShiroDbDao {

    /**
     * @param username 用户名称
     * @return
     * @Description 通过用户名称查询用户信息
     * @author
     */
    SetUser findByUserName(String username);

    /**
     * @param username 用户名称
     * @return
     * @Description 通过用户名称查询用户资源权限
     * @author
     */
    List<SetResource> findResourceRolesByUserName(String username);


    /**
     * @return
     * @Description 查询所有过滤器
     * @author
     */
    List<SFilter> findAllFilters();

}
