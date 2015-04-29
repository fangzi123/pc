package com.micro.rent.biz.shiro.web;

import com.micro.rent.common.web.controller._BaseController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author caobin
 * @version 1.0
 * @Description 未授权跳转
 * @date 2013-2-5
 */
public class UnauthorizedController extends _BaseController {

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        return createMAV("errors/403");
    }
}
