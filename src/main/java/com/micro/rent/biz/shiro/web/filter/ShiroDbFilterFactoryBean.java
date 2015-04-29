package com.micro.rent.biz.shiro.web.filter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.micro.rent.biz.shiro.service.ShiroDbService;
import com.micro.rent.dbaccess.entity.SFilter;

public class ShiroDbFilterFactoryBean extends ShiroFilterFactoryBean implements
        InitializingBean {

    private static transient final Logger log = LoggerFactory
            .getLogger(ShiroDbFilterFactoryBean.class);
    private ShiroDbService shiroDbService;
    // 追加到filterChainDefinition中的匹配定义
    private Map<String, String> appendChainDefinition;

    public ShiroDbFilterFactoryBean() {
        super();
        appendChainDefinition = new LinkedHashMap<String, String>();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.apache.shiro.spring.web.ShiroFilterFactoryBean#setFilterChainDefinitions
     * (java.lang.String)
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {
        throw new UnsupportedOperationException();
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean#
     * setFilterChainDefinitionMap(java.util.Map)
     */
    @Override
    public void setFilterChainDefinitionMap(
            Map<String, String> filterChainDefinitionMap) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        List<SFilter> lstFilters = shiroDbService.findAllFilters();
        if (lstFilters != null && !lstFilters.isEmpty()) {
            for (SFilter f : lstFilters) {
                StringBuilder valueBuilder = new StringBuilder();
                valueBuilder
                        .append(f.getSecurityFilter().equals("anon") ? "anon"
                                : String.format("%s,cRoles[%s]",
                                f.getSecurityFilter(),
                                f.getResourceId()));
                log.debug("read filter definition: {}",
                        new Object[] { valueBuilder.toString() });
                filterChainDefinitionMap.put(f.getUrlPattern(),
                        valueBuilder.toString());
            }
            filterChainDefinitionMap.putAll(appendChainDefinition);
        }
        super.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }

    /**
     * @param shiroDbService
     *            the shiroDbService to set
     */
    public void setShiroDbService(ShiroDbService shiroDbService) {
        this.shiroDbService = shiroDbService;
    }

    /**
     * @return the appendChainDefinition
     */
    public Map<String, String> getAppendChainDefinition() {
        return appendChainDefinition;
    }

    /**
     * @param appendChainDefinition
     *            the appendChainDefinition to set
     */
    public void setAppendChainDefinition(
            Map<String, String> appendChainDefinition) {
        this.appendChainDefinition = appendChainDefinition;
    }

}
