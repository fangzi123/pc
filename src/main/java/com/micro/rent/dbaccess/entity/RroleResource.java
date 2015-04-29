package com.micro.rent.dbaccess.entity;


public class RroleResource {

    private String resourceRoleId;

    private String resourceId;

    private String resourceRoleName;

    private String urlPattern;

    public String getResourceRoleId() {
        return resourceRoleId;
    }

    public void setResourceRoleId(String resourceRoleId) {
        this.resourceRoleId = resourceRoleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceRoleName() {
        return resourceRoleName;
    }

    public void setResourceRoleName(String resourceRoleName) {
        this.resourceRoleName = resourceRoleName;
    }

    public String getUrlPattern() {
        return urlPattern;
    }

    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }
}
