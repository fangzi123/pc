package com.micro.rent.biz.shiro.web.filter;

import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import java.util.Map;

public class CustomRolesAuthorizationFilter extends RolesAuthorizationFilter {
    /**
     * @return
     * @Description 获取应用到的路径
     * @author
     */
    public Map<String, Object> getAppliedPaths() {
        return this.appliedPaths;
    }
}
