package com.micro.rent.dbaccess.entity;

import java.util.Date;

public class SetResourceRole {
    private String resourceRoleId;

    private String resourceRoleCode;

    private String resourceRoleName;

    private String resourceRoleType;

    private String resourceRoleDesc;

    private String status;

    private String lastUpdator;

    private Date lastUpdateTime;

    public String getResourceRoleId() {
        return resourceRoleId;
    }

    public void setResourceRoleId(String resourceRoleId) {
        this.resourceRoleId = resourceRoleId;
    }

    public String getResourceRoleCode() {
        return resourceRoleCode;
    }

    public void setResourceRoleCode(String resourceRoleCode) {
        this.resourceRoleCode = resourceRoleCode;
    }

    public String getResourceRoleName() {
        return resourceRoleName;
    }

    public void setResourceRoleName(String resourceRoleName) {
        this.resourceRoleName = resourceRoleName;
    }

    public String getResourceRoleType() {
        return resourceRoleType;
    }

    public void setResourceRoleType(String resourceRoleType) {
        this.resourceRoleType = resourceRoleType;
    }

    public String getResourceRoleDesc() {
        return resourceRoleDesc;
    }

    public void setResourceRoleDesc(String resourceRoleDesc) {
        this.resourceRoleDesc = resourceRoleDesc;
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