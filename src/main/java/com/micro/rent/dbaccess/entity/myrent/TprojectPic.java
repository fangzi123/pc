package com.micro.rent.dbaccess.entity.myrent;

public class TprojectPic {
    private String tProjectPicId;
    private String projectId;
    private String picture;

    public String gettProjectPicId() {
        return tProjectPicId;
    }

    public void settProjectPicId(String tProjectPicId) {
        this.tProjectPicId = tProjectPicId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}
