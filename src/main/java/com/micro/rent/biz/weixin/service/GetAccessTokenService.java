package com.micro.rent.biz.weixin.service;

/**
 * @author liqianxi
 * @Description: 获取窝牛微信公众平台的access_token
 * @date 2014-11-11
 */
public interface GetAccessTokenService {

    /**
     * 微信公众平台access_token信息间分隔符
     */
    public static final String ACCESS_TOKEN_INFO_SEPERATOR = ":";

    /**
     * 取得微信公众平台的access_token
     *
     * @return 微信公众平台的access_token及其有效时间(返回值格式：)
     * 返回值格式为：expires_in + "_" + access_token
     */
    String getAccessToken();
}
