package com.micro.rent.biz.shiro.web.filter;


import com.micro.rent.biz.shiro.web.token.CaptchaUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationToken;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author
 * @version 1.0
 * @Description 在CustomFormAuthenticationFilter基础上整合验证码
 * @date 2013-2-26
 */
public class CaptchaFormAuthenticationFilter extends
        CustomFormAuthenticationFilter {

    private String usernameParamName;
    private String passwordnameParamName;
    private String captchaParamName;

    @Override
    protected AuthenticationToken createToken(ServletRequest request,
                                              ServletResponse response) {
        //获取用户名
        String strUsername = request.getParameter(usernameParamName);
        //获取密码
        String strPassword = request.getParameter(passwordnameParamName);
        //获取验证码
        String strCaptcha = request.getParameter(captchaParamName);

        boolean bRememberMe = isRememberMe(request);

        String host = getHost(request);

        return new CaptchaUsernamePasswordToken(strUsername, strPassword,
                bRememberMe, host, strCaptcha);
    }

    public void setUsernameParamName(String usernameParamName) {
        this.usernameParamName = usernameParamName;
    }

    public void setPasswordnameParamName(String passwordnameParamName) {
        this.passwordnameParamName = passwordnameParamName;
    }

    public void setCaptchaParamName(String captchaParamName) {
        this.captchaParamName = captchaParamName;
    }

}
