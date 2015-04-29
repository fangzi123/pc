package com.micro.rent.biz.subscribe.web;

import com.micro.rent.common.web.controller._BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rental/subscribe")
public class SubscribeController extends _BaseController {
    @RequestMapping("/displaySubscribeInfo")
    public ModelAndView displayMatch(HttpServletRequest request) {
        ModelAndView mv;
        String op = request.getParameter("op");
        log.info("op=" + op);
        if ("corp".equals(op)) {
            mv = createMAV("subscribe/corp");
        } else if ("shanghai".equals(op)) {
            mv = createMAV("subscribe/shanghai");
        } else if ("hangzhou".equals(op)) {
            mv = createMAV("subscribe/hangzhou");
        } else {
            mv = createMAV("subscribe/others");
        }

        return mv;
    }


}
