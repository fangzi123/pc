package com.micro.rent.biz.defaults.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author
 * @version 1.0
 * @Description 首页控制器
 * @date
 */
@Controller
public class DefaultController extends com.micro.rent.common.web.controller._BaseController {

    @RequestMapping("/index")
    protected ModelAndView handleRequestInternal(HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {
        ModelAndView mv = null;

        mv = createMAV("default");

        return mv;
    }

}
