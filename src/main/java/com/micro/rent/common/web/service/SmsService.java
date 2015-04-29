package com.micro.rent.common.web.service;

import java.util.Map;

/**
 * 短信服务接口
 *
 * @author liqianxi
 * @date 2014/11/6
 */
public interface SmsService {

    /**
     * 发送模板信息
     *
     * @param templateID       短信模板ID
     * @param targets          发送目标（多个目标之间用","(半角逗号)进行分割）
     * @param templateReplaces 模板的替换内容
     * @return 发送结果
     */
    Map<String, Object> sendTemplateSMS(String templateID, String targets, String[] templateReplaces);

}
