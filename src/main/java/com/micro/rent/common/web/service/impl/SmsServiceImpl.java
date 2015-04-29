package com.micro.rent.common.web.service.impl;

import com.cloopen.rest.sdk.CCPRestSDK;
import com.micro.rent.common.web.service.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 短信服务
 *
 * @author liqianxi
 * @date 2014/11/6
 */
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 短信发送成功Code
     */
    public static final String SUCCESS_CODE = "000000";

    /**
     * 短信发送服务地址
     */
    @Value("${cloopen.server}")
    private String url;

    /**
     * 短信发送服务端口
     */
    @Value("${cloopen.port}")
    private String port;

    /**
     * 账号ID
     */
    @Value("${cloopen.accountSid}")
    private String accountID;

    /**
     * 账号Token
     */
    @Value("${cloopen.accountToken}")
    private String accountToken;

    /**
     * 应用ID
     */
    @Value("${cloopen.appId}")
    private String appID;

    /**
     * 发送短信
     *
     * @param templateID           模板ID
     * @param targetArray          目标
     * @param templateReplaceArray 模板替换内容
     * @return
     */
    @Override
    public Map<String, Object> sendTemplateSMS(String templateID,
                                               String targets, String[] templateReplaces) {
        CCPRestSDK restAPI = new CCPRestSDK();
        restAPI.init(this.url, this.port);
        restAPI.setAccount(this.accountID, this.accountToken);
        restAPI.setAppId(this.appID);
        Map<String, Object> result = restAPI.sendTemplateSMS(targets, templateID, templateReplaces);

        return result;
    }
}
