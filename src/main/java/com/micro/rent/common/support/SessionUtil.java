package com.micro.rent.common.support;

import com.micro.rent.biz.myrent.vo.AuthInfo;
import com.micro.rent.biz.weixin.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    /**
     * 微信ID
     */
    private final static String OPEN_ID = "openId";

    /**
     * @return String sessionId
     * @throws
     * @Description: 为每个线程生成唯一的sessionId
     */
    public static String getSessionId() {
        String sessionId = System.currentTimeMillis() + "-"
                + (int) ((Math.random() * 10000));
        return sessionId;
    }

    /**
     * 取得Session中保存的用户微信ID
     * <p>如果Session范围存在微信ID，则返回该微信ID。<br/>否则，通过微信生成的code获取用户的微信ID，并保存到Session范围</p>
     *
     * @param request
     * @param authService
     * @return 用户微信ID
     */
    public static String getOpenId(HttpServletRequest request, AuthService authService) {
        String openId = (String) request.getSession().getAttribute(OPEN_ID);
        String code = WebUtils.findParameterValue(request, "code");
        if (StringUtils.isEmpty(openId) && !StringUtils.isEmpty(code)) {
            AuthInfo authInfo = authService.baseAuth(code);
            openId = authInfo == null ? "" : authInfo.getOpenid();
            request.getSession().setAttribute(OPEN_ID, openId);
        }

        return openId;
    }

}
