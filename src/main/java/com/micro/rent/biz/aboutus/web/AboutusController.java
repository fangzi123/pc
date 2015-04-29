package com.micro.rent.biz.aboutus.web;

import com.micro.rent.common.web.controller._BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/rental/aboutus")
public class AboutusController extends _BaseController {

    /**
     * 简介
     *
     * @param request
     * @return
     */
    @RequestMapping("introductCmp")
    public ModelAndView introductCompany(HttpServletRequest request) {
        ModelAndView mv = createMAV("aboutus/company_introduction");


        return mv;
    }

    /**
     * 联系我们
     *
     * @param request
     * @return
     */
    @RequestMapping("contactUs")
    public ModelAndView contactUs(HttpServletRequest request) {
        ModelAndView mv = createMAV("aboutus/company_introduction");

        return mv;
    }

    /**
     * 优惠活动
     *
     * @param request
     * @return
     */
    @RequestMapping("offer")
    public ModelAndView offer(HttpServletRequest request) {
        ModelAndView mv = createMAV("aboutus/offer");

        return mv;
    }
}
