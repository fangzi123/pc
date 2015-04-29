package com.micro.rent.common.comm.shiro.service;


/**
 * @author
 * @version 1.0
 * @Description shiro动态资源扩展服务
 * @date 2013-2-27
 */
public interface ShiroService {

    /**
     * @param urlPattern
     * @param roles
     * @Description 动态增加身份验证/角色鉴权过滤器
     * @author
     */
    void addSecurityFilter(String urlPattern, String[] roles);


    /**
     * @param urlPattern
     * @Description 动态删除身份验证/角色鉴权过滤器
     * @author
     */
    void deleteSecurityFilter(String urlPattern);

}
