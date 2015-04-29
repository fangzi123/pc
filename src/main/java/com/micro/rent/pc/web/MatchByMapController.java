package com.micro.rent.pc.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.IPUtil;
import com.micro.rent.common.support.JsonUtils;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.MapQueryVo;
import com.micro.rent.pc.entity.vo.MapResultVo;
import com.micro.rent.pc.service.MatchByMapSdervice;
import com.micro.rent.pc.service.comm.EnumService;
import com.micro.rent.pc.util.Constants;
/**
 * @ClassName: MatchByMapController
 * @Description: 地图找房
 * @author: wff
 * @date: 2015年2月2日 上午11:23:01
 */
@Controller
@RequestMapping("/map")
public class MatchByMapController extends _BaseController {
    @Autowired
    private MatchByMapSdervice  matchByMapSdervice;
    @Autowired
    private EnumService enumService;
    /**
     * @Title: display
     * @Description: 地图找房入口
     * @return
     * @author: wff
     * @return: ModelAndView
     * @throws Exception 
     */
    @RequestMapping(value="display")
    public ModelAndView display(HttpServletRequest request,MapQueryVo mqv) throws Exception{
        mqv.setCityId(IPUtil.getRegion());
        if(StringUtils.isBlank(mqv.getZoom())){
            mqv.setZoom("行政区");
        }
        ModelAndView mv =this.createMAV("match/matchByMap");
        MapResultVo mapResultVo=matchByMapSdervice.findAllCommunityByZoom(mqv);
        mv.addObject("mapResultVo", JsonUtils.obj2JsonString(mapResultVo));
        mv.addObject("house_layout_enum", enumService.findEnum(Constants.HOUSE_LAYOUT_ENUM));
        return mv;                 
    }

    /**
     * @Title: filterMap
     * @Description:地图找房页筛选
     * @param request
     * @author: wff
     * @return: void
     * @throws Exception 
     */
    @RequestMapping(value="filter")
    public void filterMap(HttpServletRequest request,MapQueryVo mqv,ModelMap mm) throws Exception{
        mqv.setCityId(IPUtil.getRegion());
        if(Integer.valueOf(mqv.getZoom())<=11){
            mqv.setZoom(Constants.DISTRICT);
        }else if(Integer.valueOf(mqv.getZoom())>=12){
            mqv.setZoom(Constants.COMMUNITY);
        }
        MapResultVo mapResultVo=matchByMapSdervice.findAllCommunityByFilter(mqv);
        mm.addAttribute("mapResultVo", JsonUtils.obj2JsonString(mapResultVo));
    }
    /**
     * @Title: communityHouses
     * @Description: 小区房子列表
     * @param request
    	* @author: wff
     * @return: void
     * @throws Exception 
     */
    @RequestMapping(value="community")
    public void communityHouses(HttpServletRequest request,ModelMap mm) throws Exception{
        String  houseIds=this.findStringParameterValue(request, "houseIds");
        List<HouseVo> houseList= matchByMapSdervice.findHousesByHouseIds(houseIds);
        mm.addAttribute("houseList", houseList);
    }
}
