package com.micro.rent.dbaccess.entity.personal;

/**
 * @author dell
 * @version 1.0
 * @Description: 我的收藏
 * @date 2014-8-28
 */
public class Favorites {

    private String tCollectionId;

    private String weixinId;

    private String projectId;

    private String houseId;

    public String gettCollectionId() {
        return tCollectionId;
    }

    public void settCollectionId(String tCollectionId) {
        this.tCollectionId = tCollectionId;
    }

    public String getWeixinId() {
        return weixinId;
    }

    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

}
