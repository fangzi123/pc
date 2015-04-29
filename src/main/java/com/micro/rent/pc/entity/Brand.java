package com.micro.rent.pc.entity;
/**
 * @ClassName: Brand
 * @Description: TODO
 * @author: wff
 * @date: 2015年2月10日 下午12:17:13
 */
public class Brand {
    private Long id;
    private String name;
    private Long addressId;
    private String description;
    private String flag;
    private Long rankGroupId;
    private String logo;
    private String tagline;//广告语
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.description = description == null ? null : description.trim();
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Long getRankGroupId() {
        return rankGroupId;
    }
    public void setRankGroupId(Long rankGroupId) {
        this.rankGroupId = rankGroupId;
    }
    public String getLogo() {
        return logo;
    }
    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline == null ? null : tagline.trim();
    }
}
