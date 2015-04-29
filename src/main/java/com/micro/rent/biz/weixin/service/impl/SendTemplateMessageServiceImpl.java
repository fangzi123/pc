package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.service.SendTemplateMessageService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @author liqianxi
 * @Description: 向微信用户发送模板消息
 * @date 2014-11-12
 */
@Service
public class SendTemplateMessageServiceImpl implements SendTemplateMessageService {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 向微信用户发送模板消息
     *
     * @param accessToken
     * @param openId
     * @param message
     */
    @Override
    public void sendTemplateMessage(String accessToken, String openId, String message) {
        HttpClient httpClient = new HttpClient();
        // 微信公众平台发送客服消息接口地址
//		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + accessToken;
        PostMethod postMethod = new PostMethod(url);
        // 消息内容
        StringBuilder bodyContent = new StringBuilder("{'touser':'");
        bodyContent.append(openId).append("','msgtype':'text','text':{'content':'");
        bodyContent.append(message).append("'}");
        bodyContent.append("}");
//		postMethod.setRequestBody(parametersBodys);
//		postMethod.setRequestHeader("Content-Type", "application/json;");
        postMethod.setRequestBody(bodyContent.toString());

//		StringRequestEntity requestEntity;
//		try {
//			requestEntity = new StringRequestEntity(bodyContent.toString(), "application/json", "utf-8");
//			postMethod.setRequestEntity(requestEntity);
//		} catch (UnsupportedEncodingException e) {
//			log.error(e.getMessage());
//		}

        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("预约成功微信消息发送失败！" + "HTTP请求失败[" + statusCode + "]");
            } else {
                String responseString = postMethod.getResponseBodyAsString();
                if (responseString.contains("errcode")) {
                    log.error("预约成功微信消息发送失败！" + "失败[" + responseString + "]");
                }
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
