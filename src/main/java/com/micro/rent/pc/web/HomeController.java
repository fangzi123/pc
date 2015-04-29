package com.micro.rent.pc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.IPUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.BrandService;

@Controller
public class HomeController extends _BaseController {
    @Autowired
    BrandService brandService;

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public ModelAndView recommendBrands(HttpServletRequest request)
            throws Exception {
        ModelAndView mv = new ModelAndView("shouye");
        String region = IPUtil.getRegion();
        if (region == null) {
            region = "110000";
        }
        List<BrandVo> brandList = brandService.getRecommendBrandList(region);
        mv.addObject(brandList);
        return mv;
    }

}
