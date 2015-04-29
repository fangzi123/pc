package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.web.token.CaptchaUsernamePasswordToken;
import com.micro.rent.common.comm.captcha.CustomGenericManageableCaptchaService;
import com.micro.rent.common.web.controller._BaseController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @version 1.0
 * @Description 身份验证
 * @date 2013-2-5
 */
public class AuthenticationController extends _BaseController {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    private String usernameParamName;
    private String passwordnameParamName;
    private String captchaParamName;
    private CustomGenericManageableCaptchaService captchaService;

    /**
     * 登录身份验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView authenticate(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {
        /*if(SecurityUtils.getSubject().isAuthenticated()){
			SecurityUtils.getSubject().logout();
		}	*/

        String strUsername = findStringParameterValue(request,
                usernameParamName);
        String strPassword = findStringParameterValue(request,
                passwordnameParamName);
        String strCaptcha = findStringParameterValue(request,
                captchaParamName);

        Subject objCurrentUser = SecurityUtils.getSubject();

        log.debug("user principal: {}",
                new Object[]{objCurrentUser.getPrincipal()});
        log.debug("user authenticated: {}",
                new Object[]{objCurrentUser.isAuthenticated()});

        if (!objCurrentUser.isAuthenticated()) {
            CaptchaUsernamePasswordToken objToken = new CaptchaUsernamePasswordToken(
                    strUsername, strPassword, false, null, strCaptcha);
            try {
                objCurrentUser.login(objToken);
                //成功以后移除验证码信息
                captchaService.removeCaptcha((String) (SecurityUtils.getSubject().getSession().getId()));
            } catch (UnknownAccountException uae) {
                log.error("不存在用户[{}]", new Object[]{objToken.getPrincipal()});
                throw new UnknownAccountException(String.format("不存在用户[%s]", objToken.getPrincipal()));
            } catch (IncorrectCredentialsException ice) {
                log.error("用户[{}]密码错误",
                        new Object[]{objToken.getPrincipal()});
                throw new IncorrectCredentialsException(String.format("用户[%s]密码错误", objToken.getPrincipal()));
            } catch (DisabledAccountException dae) {
                log.error(dae.getMessage(), dae);
                throw new DisabledAccountException(dae.getMessage(), dae);
            } catch (Throwable t) {
                log.error(t.getMessage(), t);
                throw new AuthenticationException(t.getMessage());
            }

        }
        // 已通过身份验证
        return createMAV("redirect:/default.html");
    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response) throws Exception {
        SecurityUtils.getSubject().logout();
        return createMAV("redirect:/login.html");
    }

    public void setUsernameParamName(String usernameParamName) {
        this.usernameParamName = usernameParamName;
    }

    public void setPasswordnameParamName(String passwordnameParamName) {
        this.passwordnameParamName = passwordnameParamName;
    }

    /**
     * @param captchaParamName the captchaParamName to set
     */
    public void setCaptchaParamName(String captchaParamName) {
        this.captchaParamName = captchaParamName;
    }


    public void setCaptchaService(
            CustomGenericManageableCaptchaService captchaService) {
        this.captchaService = captchaService;
    }

}
