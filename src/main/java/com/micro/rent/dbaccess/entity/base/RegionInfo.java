package com.micro.rent.dbaccess.entity.base;

public class RegionInfo {

    private String tRegionInfoId;

    private String regionCode;

    private String regionName;

    private String parentId;

    private int regionLevel;

    private int regionOrder;

    public String gettRegionInfoId() {
        return tRegionInfoId;
    }

    public void settRegionInfoId(String tRegionInfoId) {
        this.tRegionInfoId = tRegionInfoId;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getRegionLevel() {
        return regionLevel;
    }

    public void setRegionLevel(int regionLevel) {
        this.regionLevel = regionLevel;
    }

    public int getRegionOrder() {
        return regionOrder;
    }

    public void setRegionOrder(int regionOrder) {
        this.regionOrder = regionOrder;
    }

}
