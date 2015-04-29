package com.micro.rent.pc.entity.vo;


public class Mark {
    private String lng;//经度
    private String lat;//纬度
    private String zoneName;//当前区域的名称
    private String houseNum;//当前区域房源数量
    
    private String point;// 地理坐标
    private String communityId;//社区Id
    private String houseIds;//房源Ids
    public String getLng() {
        return getPoint().split(",")[0].substring(1);
    }
    public void setLng(String lng) {
         this.lng= lng;
    }
    public String getLat() {
        lat=getPoint().split(",")[1];
        return lat.substring(0, lat.length()-1);
    }
    public void setLat(String lat) {
        this.lat =lat;
    }
    public String getZoneName() {
        return zoneName;
    }
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    public String getHouseNum() {
        return houseNum;
    }
    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    public String getCommunityId() {
        return communityId;
    }
    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }
    public String getHouseIds() {
        return houseIds;
    }
    public void setHouseIds(String houseIds) {
        this.houseIds = houseIds;
    }
}
