package com.micro.rent.dbaccess.entity;


public class UserGroup {

    private String userId;

    private String rgroupId;

    private String userName;

    private String rgroupName;

    private String status;

    private String password;

    private String orgCode;

    private String chineseName;

    private String englishName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRgroupId() {
        return rgroupId;
    }

    public void setRgroupId(String rgroupId) {
        this.rgroupId = rgroupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRgroupName() {
        return rgroupName;
    }

    public void setRgroupName(String rgroupName) {
        this.rgroupName = rgroupName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

}
