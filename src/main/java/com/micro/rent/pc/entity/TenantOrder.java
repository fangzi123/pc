package com.micro.rent.pc.entity;

import java.util.Date;

public class TenantOrder {
    private Integer id;
    /**
     * enum 订单类型：预约,预订
     */
    private String type;
    private Integer tenantId;
    private Integer houseId;
    private Integer projectId;
    private String name;
    private String mobile;
    private Integer idTypeId;
    private String idNumber;
    private Date visitingTime;
    /**
     * 租凭期限
     */
    private Integer tenancy;
    private Date checkinTime;
    /**
     * enum 订单状态：正常,失效,取消
     */
    private String status;
    private Date createTime;
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getIdTypeId() {
        return idTypeId;
    }

    public void setIdTypeId(Integer idTypeId) {
        this.idTypeId = idTypeId;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public Date getVisitingTime() {
        return visitingTime;
    }

    public void setVisitingTime(Date visitingTime) {
        this.visitingTime = visitingTime;
    }

    public Integer getTenancy() {
        return tenancy;
    }

    public void setTenancy(Integer tenancy) {
        this.tenancy = tenancy;
    }

    public Date getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Date checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
