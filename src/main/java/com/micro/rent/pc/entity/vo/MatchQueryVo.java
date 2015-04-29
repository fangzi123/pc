package com.micro.rent.pc.entity.vo;


/**
 * @ClassName: MatchQueryVo
 * @Description: 条件找房 查询条件
 * @author: wff
 * @date: 2015年1月20日 下午5:15:38
 */
public class MatchQueryVo {

    private String address;// 地址
    private String maxPrice;// 租金上限
    private String minPrice;// 租金下限
    private String layout;// 户型
    private String orientation;// 朝向
    private String balcony;// 阳台
    private String bathroom;// 卫生间
    private String bayWindow;// 飘窗
    private String sortType;// 排序类型 {0：默认, 1：最新, 2：价格 ,3：面积}
    private String ascOrDesc;// 升序：0\降序:1
    private String cityId;//城市ID

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getBayWindow() {
        return bayWindow;
    }

    public void setBayWindow(String bayWindow) {
        this.bayWindow = bayWindow;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getAscOrDesc() {
        return ascOrDesc;
    }

    public void setAscOrDesc(String ascOrDesc) {
        this.ascOrDesc = ascOrDesc;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
