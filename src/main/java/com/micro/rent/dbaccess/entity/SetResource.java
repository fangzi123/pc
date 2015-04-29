package com.micro.rent.dbaccess.entity;

import java.util.Date;

public class SetResource {
    private String resourceId;

    private String securityFilter;

    private String urlPattern;

    private String status;

    private String lastUpdator;

    private Date lastUpdateTime;

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getSecurityFilter() {
        return securityFilter;
    }

    public void setSecurityFilter(String securityFilter) {
        this.securityFilter = securityFilter;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastUpdator() {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator) {
        this.lastUpdator = lastUpdator;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}