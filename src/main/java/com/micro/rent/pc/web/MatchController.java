package com.micro.rent.pc.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.IPUtil;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.BeanToMapUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.HousePriceRange;
import com.micro.rent.pc.entity.vo.MatchQueryVo;
import com.micro.rent.pc.entity.vo.MatchResultVo;
import com.micro.rent.pc.service.MatchService;
import com.micro.rent.pc.service.comm.EnumService;
import com.micro.rent.pc.util.Constants;

@Controller
@RequestMapping("/match")
public class MatchController extends _BaseController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private EnumService enumService;

    /**
     * @Title: match
     * @Description: 条件找房
     * @param request
     * @return
     * @throws Exception
     * @author: wff
     * @return: ModelAndView
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/list")
    public ModelAndView match(HttpServletRequest request, MatchQueryVo mqo)
            throws Exception {
        mqo.setCityId(IPUtil.getRegion());
        Page<MatchResultVo> page = this.findPageFinal(request);
        page.setParams(BeanToMapUtil.convertBean(mqo));
        matchService.findHousesByAddressPaged(page);
        ModelAndView mv = this.createMAV("match/matchResult", page);
        mv.addObject("address", mqo.getAddress());
        //枚举
        mv.addObject("house_orientation_enum", enumService.findEnum(Constants.HOUSE_ORIENTATION_ENUM));
        mv.addObject("house_layout_enum", enumService.findEnum(Constants.HOUSE_LAYOUT_ENUM));
        HousePriceRange hpr=matchService.findHousePriceRangeById(1);
        mv.addObject("pricerange", hpr);
        return mv;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/listp")
    public void matchAjax(HttpServletRequest request, MatchQueryVo mqo,
            ModelMap mm) throws Exception {
        mqo.setCityId(IPUtil.getRegion());
        Page<MatchResultVo> page = this.findPageFinal(request);
        page.setParams(BeanToMapUtil.convertBean(mqo));
        matchService.findHousesByAddressPaged(page);
        mm.addAttribute(page);
    }
}