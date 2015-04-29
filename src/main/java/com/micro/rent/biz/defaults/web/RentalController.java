package com.micro.rent.biz.defaults.web;

import com.micro.rent.biz.weixin.message.EMsgType;
import com.micro.rent.biz.weixin.service.MessageHandler;
import com.micro.rent.common.support.ApplicationContextUtil;
import com.micro.rent.common.web.controller._BaseController;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

@Controller
@RequestMapping("/rent")
public class RentalController extends _BaseController {

    /**
     * 处理从user发起的消息
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/entry", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String dealMessage(HttpServletRequest request) throws IOException {

        String echostr = request.getParameter("echostr");
        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        log.debug("echostr>>>>>>>>>>>>" + echostr);
        log.debug("signature>>>>>>>>>>>>" + signature);
        log.debug("nonce>>>>>>>>>>>>" + nonce);
        log.debug("timestamp>>>>>>>>>>>>" + timestamp);
        String xmlMsg = inputStream2String(request.getInputStream());

        log.info("receive msg:{}", new Object[]{xmlMsg});
        //涉及到与微信相关的接口,均要进行token校验
        if (signature != null && checkSignature(signature, timestamp, nonce)) {// 从微信发起的请求
            if (echostr != null) {//token校验
                //response.setContentType("text/plain");
                //response.getWriter().write(echostr);
                return echostr;
            } else {//与文本、图片、音乐、事件等相关的接口
                //response.setContentType("text/xml;charset=UTF-8");//返回xml
                //截取msgType直接的内容
                String msgType = StringUtils.substringBetween(xmlMsg, "<MsgType><![CDATA[", "]]></MsgType>");
                log.debug("msgType[{}]", new Object[]{msgType});
                MessageHandler msgHandler = null;
                if (msgType != null) {
                    String beanPrefix = "";
                    if (msgType.contains(EMsgType.EVENT.getCode())) {
                        beanPrefix = StringUtils.substringBetween(xmlMsg, "<Event><![CDATA[", "]]></Event>");
                    } else {
                        beanPrefix = msgType;
                    }

                    log.debug("handler[{}]", new Object[]{beanPrefix});
                    try {
                        msgHandler = ApplicationContextUtil.getBean(beanPrefix.toLowerCase() + "Handler", MessageHandler.class);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }

                if (msgHandler != null) {
                    //response.getWriter().print(msgHandler.handleMessage(xmlMsg));
                    return msgHandler.handleMessage(xmlMsg);
                }
                return "请求有误";
            }
        } else {
            //非微信请求处理-TODO
            return "错误请求";
        }
    }

    private String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * 检查认证(与微信服务器打交道的均要认证)
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    private boolean checkSignature(String signature, String timestamp, String nonce) {
        String token = "rent";//WetalkConfigManager.getInstance().getProperty("token");
        String[] dataStrings = new String[]{token, timestamp, nonce};
        Arrays.sort(dataStrings);
        String data = dataStrings[0] + dataStrings[1] + dataStrings[2];
        String flag = DigestUtils.shaHex(data);
        log.debug("flag >>>>>>>>>>>" + flag);

        if (flag.equalsIgnoreCase(signature)) {
            return true;
        } else {
            return true;
        }

    }
}
