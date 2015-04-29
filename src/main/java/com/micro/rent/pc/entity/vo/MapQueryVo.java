package com.micro.rent.pc.entity.vo;

/**
 * @ClassName: MatchQueryVo
 * @Description: 地图找房 查询条件
 * @author: wff
 * @date: 2015年1月20日 下午5:15:38
 */
public class MapQueryVo {

    private String cityId;// 城市ID
    private String address;// 地址
    private String maxPrice;// 租金上限
    private String minPrice;// 租金下限
    private String layout;// 户型
    private String zoom;// 搜索社区的级别
    private String maxLat;// 最大纬度
    private String maxLng;// 最大经度
    private String minLat;// 最小纬度
    private String minLng;// 最小经度

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

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getZoom() {
        return zoom;
    }

    public void setZoom(String zoom) {
        this.zoom = zoom;
    }

    public String getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(String maxLat) {
        this.maxLat = maxLat;
    }

    public String getMaxLng() {
        return maxLng;
    }

    public void setMaxLng(String maxLng) {
        this.maxLng = maxLng;
    }

    public String getMinLat() {
        return minLat;
    }

    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    public String getMinLng() {
        return minLng;
    }

    public void setMinLng(String minLng) {
        this.minLng = minLng;
    }
}
