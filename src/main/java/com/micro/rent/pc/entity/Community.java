package com.micro.rent.pc.entity;

/**
 * @ClassName: Community
 * @Description: 行政区划，小区，自定义的区域，商圈，地铁站附近的区域都在社区名义下'
 * @author: wff
 * @date: 2015年1月20日 下午2:32:22
 */
public class Community {
    private Integer id;
    private String name;// 社区名\地址名
    private Integer parentId;
    private Integer addressId;
    private Integer totalNumber;
    private String description;
    private String flag;// 第1位，表示本条记录是否有效，0:无效,1:有效

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}
