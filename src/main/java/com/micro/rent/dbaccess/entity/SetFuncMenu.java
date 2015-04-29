package com.micro.rent.dbaccess.entity;

import java.math.BigDecimal;
import java.util.Date;

public class SetFuncMenu {
    private String menuId;

    private String pmenuId;

    private String menuCode;

    private String menuName;

    private String menuNameBrief;

    private String isLeaf;

    private BigDecimal lv;

    private String url;

    private BigDecimal displayOrder;

    private String status;

    private String lastUpdator;

    private Date lastUpdateTime;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPmenuId() {
        return pmenuId;
    }

    public void setPmenuId(String pmenuId) {
        this.pmenuId = pmenuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuNameBrief() {
        return menuNameBrief;
    }

    public void setMenuNameBrief(String menuNameBrief) {
        this.menuNameBrief = menuNameBrief;
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    public BigDecimal getLv() {
        return lv;
    }

    public void setLv(BigDecimal lv) {
        this.lv = lv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(BigDecimal displayOrder) {
        this.displayOrder = displayOrder;
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