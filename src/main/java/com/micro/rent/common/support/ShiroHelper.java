package com.micro.rent.common.support;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.micro.rent.biz.shiro.ShiroDbRealm.ShiroUser;
import com.micro.rent.pc.web.shiro.ShiroTenantRealm.ShiroTenant;

/**
 * @author
 * @version 1.0
 * @Description shiro框架帮助类
 * @date 2013-3-21
 */
public class ShiroHelper {

    /**
     * @return
     * @Description 获取当前登录用户名称
     * @author
     */
    public static String getUsername() {
        ShiroUser su = getShiroUser();
        return su == null ? null : su.getUserName();
    }

    public static String getUserOrgCode() {
        ShiroUser su = getShiroUser();
        return su == null ? null : su.getOrgCode();
    }

    public static ShiroUser getShiroUser() {
        return ShiroUser.class.cast(SecurityUtils.getSubject().getPrincipal());
    }

    public static ShiroTenant getShiroTenant() {
        return ShiroTenant.class
                .cast(SecurityUtils.getSubject().getPrincipal());
    }

    public static Boolean hasSupplierRole() {
        Subject sub = SecurityUtils.getSubject();
        // 供货商角色
        if (sub.hasRole("100046")) {
            return true;
        }

        return false;
    }

    public static Boolean hasAdmin() {
        Subject sub = SecurityUtils.getSubject();
        // 创建角色
        if (sub.hasRole("994")) {
            return true;
        }

        return false;
    }

    public static boolean isAuth() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    public static Integer getTenantId() {
        ShiroTenant sUser = getShiroTenant();
        return sUser == null ? null : sUser.getId();
    }
}
