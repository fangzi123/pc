package com.micro.rent.biz.weixin.vo;

/**
 * @author liqianxi
 * @version 1.0
 * @Description: 微信用户基本信息
 * @date 2014-12-13
 */
public class WeixinUserInfo {

    /**
     * 微信用户openid
     */
    private String openid;

    /**
     * 微信用户昵称
     */
    private String nickname;

    /**
     * 微信用户性别
     */
    private String sex;

    /**
     * 微信用户所在城市
     */
    private String city;

    /**
     * 微信用户所在国家
     */
    private String country;

    /**
     * 微信用户所在省
     */
    private String province;

    /**
     * 微信用户头像
     */
    private String headimgurl;

    /**
     * 微信用户特权信息
     */
    private String[] privilege;

    /**
     * 公众号unionid
     */
    private String unionid;

    /**
     * language
     */
    private String language;

    /**
     * 取得微信用户openid
     *
     * @return 微信用户openid
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设定微信用户openid
     *
     * @param openid 微信用户openid
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 取得微信用户昵称
     *
     * @return 微信用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设定微信用户昵称
     *
     * @param nickname 微信用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 取得微信用户性别
     *
     * @return 微信用户性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设定微信用户性别
     *
     * @param sex 微信用户性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 取得微信用户所在城市
     *
     * @return 微信用户所在城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设定微信用户所在城市
     *
     * @param city 微信用户所在城市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 取得微信用户所在国家
     *
     * @return 微信用户所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设定微信用户所在国家
     *
     * @param country 微信用户所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 取得微信用户所在省
     *
     * @return 微信用户所在省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设定微信用户所在省
     *
     * @param province 微信用户所在省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 取得微信用户头像
     *
     * @return 微信用户头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设定微信用户头像
     *
     * @param 微信用户头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    /**
     * 取得微信用户特权信息
     *
     * @return 微信用户特权信息
     */
    public String[] getPrivilege() {
        return privilege;
    }

    /**
     * 设定微信用户特权信息
     *
     * @param privilege 微信用户特权信息
     */
    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    /**
     * 取得公众号unionid
     *
     * @return 公众号unionid
     */
    public String getUnionid() {
        return unionid;
    }

    /**
     * 设定公众号unionid
     *
     * @param unionid 公众号unionid
     */
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    /**
     * 取得language
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * 设定language
     *
     * @param language language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

}
