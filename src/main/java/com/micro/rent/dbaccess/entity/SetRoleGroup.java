package com.micro.rent.dbaccess.entity;

import java.util.Date;

public class SetRoleGroup {
    private String rgroupId;

    private String rgroupCode;

    private String rgroupName;

    private String lastUpdator;

    private Date lastUpdateTime;

    public String getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(String rgroupId) {
        this.rgroupId = rgroupId;
    }

    public String getRgroupCode() {
        return rgroupCode;
    }

    public void setRgroupCode(String rgroupCode) {
        this.rgroupCode = rgroupCode;
    }

    public String getRgroupName() {
        return rgroupName;
    }

    public void setRgroupName(String rgroupName) {
        this.rgroupName = rgroupName;
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