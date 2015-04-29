package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.myrent.vo.AuthInfo;
import com.micro.rent.biz.weixin.service.AuthService;
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

/**
 * @author dell
 * @version 1.0
 * @Description: 微信网页授权服务
 * @date 2014-8-28
 */
@Service
public class AuthServiceImpl implements AuthService {

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
     * 用户同意授权，获取code
     *
     * @param redirectURI 用户同意授权后，重定向路径
     * @param state       重定向时携带的参数值（a-zA-Z0-9的参数值）
     */
    @Override
    public void getCodeAndRedirect(String redirectURI, String state) {
        HttpClient httpClient = new HttpClient();
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appid
                + "&redirect_uri=" + redirectURI + "&response_type=code&scope=snsapi_userinfo&state="
                + state + "#wechat_redirect";
        GetMethod getMethod = new GetMethod(url);
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            log.debug("statusCode:" + statusCode);
        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 通过code换取网页授权信息
     *
     * @param code
     * @return 网页授权信息
     */
    @Override
    public AuthInfo baseAuth(String code) {

        HttpClient httpClient = new HttpClient();

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + secret;
        String searchUrl = "&code=" + code + "&grant_type=authorization_code";
        GetMethod getMethod = new GetMethod(url + searchUrl);

        try {
            int statusCode = httpClient.executeMethod(getMethod);
            log.debug("statusCode:" + statusCode);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("失败返回码[" + statusCode + "]");
            } else {
                String responseString = getMethod.getResponseBodyAsString();
                log.debug(responseString);
                return responseString.contains("errcode") ? null :
                        JsonUtils.jsonString2Object(responseString, AuthInfo.class);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

}
