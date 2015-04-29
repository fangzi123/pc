/**
 *
 */
package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.service.WeixinUserInfoService;
import com.micro.rent.biz.weixin.vo.WeixinUserInfo;
import com.micro.rent.common.support.JsonUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author liqianxi
 * @version 1.0
 * @Description: 微信用户基本信息服务接口实现
 * @date 2014-12-13
 */
@Service
public class WeixinUserInfoServiceImpl implements WeixinUserInfoService {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取微信用户基本信息
     *
     * @param weixinId    用户对于公众号的唯一ID(OpenId)
     * @param accessToken 调用接口凭证
     */
    @Override
    public WeixinUserInfo getWeixinUserInfo(String weixinId, String accessToken) {
        HttpClient httpClient = new HttpClient();
        String url =
                "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken
                        + "&openid=" + weixinId + "&lang=zh_CN";
        GetMethod getMethod = new GetMethod(url);
        log.debug("accessToken:" + accessToken);

        try {
            int statusCode = httpClient.executeMethod(getMethod);
            log.debug("statusCode:" + statusCode);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                return null;
            } else {
                String responseString = new String(getMethod.getResponseBodyAsString().getBytes(getMethod.getResponseCharSet()));

                log.info(responseString);

                return responseString.contains("errcode") ? null :
                        JsonUtils.jsonString2Object(responseString, WeixinUserInfo.class);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

}
