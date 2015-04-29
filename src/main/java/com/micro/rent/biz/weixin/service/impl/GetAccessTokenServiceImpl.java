package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.service.GetAccessTokenService;
import com.micro.rent.common.support.JsonUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

/**
 * @author liqianxi
 * @Description: 获取窝牛微信公众平台的access_token
 * @date 2014-11-11
 */
@Service
public class GetAccessTokenServiceImpl implements GetAccessTokenService {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 应用id
     */
    @Value("${appid}")
    private String appid;
    /**
     * 应用密码
     */
    @Value("${secret}")
    private String secret;

    /**
     * 取得微信公众平台的access_token
     *
     * @return 微信公众平台的access_token及其有效时间(返回值格式：)
     * 返回值格式为：expires_in + "_" + access_token
     */
    public String getAccessToken() {
        HttpClient httpClient = new HttpClient();
        // 微信公众平台获取access_token接口地址
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret;
        GetMethod getMethod = new GetMethod(url);

        String result = "";
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            log.debug("statusCode:" + statusCode);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("取微信公众平台的access_token失败！" + "HTTP请求失败[" + statusCode + "]");
            } else {
                String responseString = getMethod.getResponseBodyAsString();
                if (responseString.contains("errcode")) {
                    log.error("取微信公众平台的access_token失败！" + "失败[" + responseString + "]");
                } else {
                    Map<String, Object> responseMap = JsonUtils.jsonString2Map(responseString);
                    result = responseMap.get("expires_in") + ACCESS_TOKEN_INFO_SEPERATOR + responseMap.get("access_token");
                }
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }
}
