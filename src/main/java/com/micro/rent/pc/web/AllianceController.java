package com.micro.rent.pc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.biz.base.service.RegionService;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.base.RegionInfo;
import com.micro.rent.pc.entity.vo.AllianceVo;
import com.micro.rent.pc.service.AllianceService;

@Controller
@RequestMapping("/alliance")
public class AllianceController extends _BaseController {
    @Autowired
    private AllianceService allianceService;
    @Autowired
    private RegionService regionService;
    
    @RequestMapping(value = "/add")
    public void add(HttpServletRequest request, AllianceVo ae,ModelMap mm)
            throws Exception {
        if(this.isTheSameVerifyCode(request, ae.getPhone(), ae.getVerifyCode())){
            allianceService.add(ae);
            mm.addAttribute("success", true);
        }else{
            mm.addAttribute("success", false);
        }
    }
    @RequestMapping(value = "/index")
    public ModelAndView index(HttpServletRequest request, AllianceVo ae)
            throws Exception {
        ModelAndView mv=this.createMAV("alliance");
        List<RegionInfo>  regionInfo=regionService.findReginsByParentId("1");
        mv.addObject("regionInfo", regionInfo);
        return mv;
    }

}