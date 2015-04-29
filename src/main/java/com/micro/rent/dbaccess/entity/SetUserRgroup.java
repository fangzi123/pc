package com.micro.rent.dbaccess.entity;

import java.util.Date;

public class SetUserRgroup extends SetUserRgroupKey {
    private String lastUpdator;

    private Date lastUpdateTime;

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