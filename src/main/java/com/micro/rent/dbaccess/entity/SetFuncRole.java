package com.micro.rent.dbaccess.entity;

import java.util.Date;

public class SetFuncRole {
    private String funcRoleId;

    private String funcRoleCode;

    private String funcRoleName;

    private String funcRoleType;

    private String funcRoleDesc;

    private String status;

    private String lastUpdator;

    private Date lastUpdateTime;

    public String getFuncRoleId() {
        return funcRoleId;
    }

    public void setFuncRoleId(String funcRoleId) {
        this.funcRoleId = funcRoleId;
    }

    public String getFuncRoleCode() {
        return funcRoleCode;
    }

    public void setFuncRoleCode(String funcRoleCode) {
        this.funcRoleCode = funcRoleCode;
    }

    public String getFuncRoleName() {
        return funcRoleName;
    }

    public void setFuncRoleName(String funcRoleName) {
        this.funcRoleName = funcRoleName;
    }

    public String getFuncRoleType() {
        return funcRoleType;
    }

    public void setFuncRoleType(String funcRoleType) {
        this.funcRoleType = funcRoleType;
    }

    public String getFuncRoleDesc() {
        return funcRoleDesc;
    }

    public void setFuncRoleDesc(String funcRoleDesc) {
        this.funcRoleDesc = funcRoleDesc;
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