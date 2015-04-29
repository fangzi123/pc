package com.micro.rent.biz.shiro.web.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import java.util.Map;

public class CustomFormAuthenticationFilter extends FormAuthenticationFilter {

    /**
     * @return
     * @Description 获取应用到的路径
     * @author
     */
    public Map<String, Object> getAppliedPaths() {
        return this.appliedPaths;
    }
}
