package com.micro.rent.pc.entity;
/**
 * @ClassName: Alliance
 * @Description: 公寓的加盟信息
 * @author: wff
 * @date: 2015年3月7日 下午12:13:13
 */
public class Alliance {
    private Integer id;
    private String contactPerson;
    private String phone;
    private String brandName;
    private String gender;
    private String email;
    private String managementType;//公寓房源的管理方式：集中式，分布式
    private Integer totalHouseNumber;
    private Long addressId;
    private String description;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getContactPerson() {
        return contactPerson;
    }
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getManagementType() {
        return managementType;
    }
    public void setManagementType(String managementType) {
        this.managementType = managementType;
    }
    public Integer getTotalHouseNumber() {
        return totalHouseNumber;
    }
    public void setTotalHouseNumber(Integer totalHouseNumber) {
        this.totalHouseNumber = totalHouseNumber;
    }
    public Long getAddressId() {
        return addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
