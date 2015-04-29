package com.micro.rent.dbaccess.entity;


/**
 * @author
 * @version 1.0
 * @Description 安全过滤器
 * @date
 */
public class SFilter {
    private String resourceId;

    private String securityFilter;

    private String urlPattern;

    /**
     * @return the resourceId
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId the resourceId to set
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return the securityFilter
     */
    public String getSecurityFilter() {
        return securityFilter;
    }

    /**
     * @param securityFilter the securityFilter to set
     */
    public void setSecurityFilter(String securityFilter) {
        this.securityFilter = securityFilter;
    }

    /**
     * @return the urlPattern
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * @param urlPattern the urlPattern to set
     */
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }


}
