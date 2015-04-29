package com.micro.rent.pc.web;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.common.web.service.SmsService;
import com.micro.rent.common.web.service.impl.SmsServiceImpl;
import com.micro.rent.pc.service.TenantService;
import com.micro.rent.pc.util.Constants;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;

@Controller
@RequestMapping("/user")
public class TenantController extends _BaseController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private SmsService smsService;

    @RequestMapping(value = "/password/dynamic", method = { RequestMethod.GET })
    public void getDynamicPassword(HttpServletRequest request, ModelMap modelMap) {
        String mobile = this.findStringParameterValue(request, "username");
        String status = this.findStringParameterValue(request, "status");
        if (mobile == null || mobile == "" || !NumberUtils.isDigits(mobile)) {
            modelMap.put("success", false);
            modelMap.put("message", "请输入正确的手机号！");
        } else {
            // 生成短信验证码
            try {
                WordGenerator wordGenerator = new RandomWordGenerator(
                        ConfigUtil.getConfig("dynamicpassword.acceptchars"));

                String verifyCode = wordGenerator.getWord(6,
                        Locale.getDefault());
                // 重设密码
                // 未加密i
                if(Constants.LOGIN.equals(status)){
                    tenantService.resetDynamicPasswordByMobile(mobile, verifyCode);
                }
                // 调用短信平台接口发送验证码
                if (sendVerifyCodeMessage(mobile, verifyCode)) {
                    modelMap.put("success", true);
                    modelMap.put("verifyCode", verifyCode);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("message", "验证码取得失败，请重新获取！");
                }
            } catch (Exception e) {
                logger.error("验证码备选字符读取失败！", e);
                modelMap.put("success", false);
                modelMap.put("message", "验证码取得失败，请重新获取！");
            }
        }
    }

    private boolean sendVerifyCodeMessage(String tenentTel, String verifyCode) {
        boolean flag = false;
        try {
            String templateId = ConfigUtil
                    .getConfig("cloopen.orderVerifyCodeTemplate");
            Map<String, Object> result = smsService.sendTemplateSMS(templateId,
                    tenentTel, new String[] { verifyCode, "1" });
            if (SmsServiceImpl.SUCCESS_CODE.equals(result.get("statusCode"))) {
                flag = true;
            } else {
                // 异常返回输出错误码和错误信息
                log.error("短信获取失败：[code:" + result.get("statusCode")
                        + ", message:" + result.get("statusMsg") + "]");
            }
        } catch (Exception e) {
            log.error("验证码短信模板ID取得失败！", e);
        }
        return flag;
    }

}
