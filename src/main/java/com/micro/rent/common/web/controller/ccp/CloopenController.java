package com.micro.rent.common.web.controller.ccp;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.common.web.service.SmsService;
import com.micro.rent.common.web.service.impl.SmsServiceImpl;
import com.micro.rent.pc.util.JedisUtil;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;

@Controller
@RequestMapping("/ccp")
public class CloopenController extends _BaseController {

    @Autowired
    private SmsService smsService;

    /**
     * 获取预约验证码
     *
     * @param request
     */
    @RequestMapping("get_verifyCode")
    public void getVerifyCode(HttpServletRequest request, ModelMap modelMap) {
        // 取得请求中的预约电话号码
        String mobile = this.findStringParameterValue(request, "mobile");
        if (mobile == null || mobile == "" || !NumberUtils.isDigits(mobile)) {
            modelMap.put("success", false);
            modelMap.put("message", "请输入正确的手机号！");
        } else {
            // 生成短信验证码
            try {
                WordGenerator wordGenerator = new RandomWordGenerator(
                        ConfigUtil.getConfig("verifycode.acceptchars"));
                String verifyCode = wordGenerator.getWord(4,
                        Locale.getDefault());
                // 将生成的短信验证码保存进redis
                saveVerifyCode(request, mobile, verifyCode);

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
    @RequestMapping("isTheSame_VerifyCode")
    public void isTheSame_VerifyCode(HttpServletRequest request, ModelMap modelMap) {
        boolean flag=false;
        String mobile = this.findStringParameterValue(request, "mobile");
        String verifyCode = this.findStringParameterValue(request, "verifyCode");
        if(this.isTheSameVerifyCode(request, mobile, verifyCode)){
            flag=true;
        };
        modelMap.addAttribute("success", flag);
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

    /*
     * 将生成的短信验证码保存进redis
     */
    public void saveVerifyCode(HttpServletRequest request, String tenentTel,
            String verifyCode) {
       // request.getSession().setAttribute(tenentTel, verifyCode);
        JedisUtil.set(tenentTel, verifyCode);
    }

}
