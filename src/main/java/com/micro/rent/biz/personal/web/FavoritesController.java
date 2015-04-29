package com.micro.rent.biz.personal.web;

import com.micro.rent.biz.personal.service.FavoritesService;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.biz.weixin.service.AuthService;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.personal.Favorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dell
 * @Description: 我的收藏
 * @date 2014-8-28
 */
@Controller
@RequestMapping("/rental/favorites")
public class FavoritesController extends _BaseController {

    @Autowired
    private FavoritesService favoritesService;
    @Autowired
    private AuthService authService;

    // @RequestMapping("/queryList")
    // public ModelAndView queryFavoritesList(HttpServletRequest request,
    // @RequestParam(value = "code", required = false) String code) {
    // ModelAndView mv = createMAV("personal/personal");
    //
    // String weixinId = SessionUtil.getOpenId(request, authService);
    // String openid = this.findStringParameterValue(request, "weixinId");
    // // weixinId = "oYaa9t5qNkThgz5XtS0KPeoeSyfQ";
    // log.debug("weixinId is " + weixinId);
    // // weixinId = "wx111111"; //TEST ONLY
    // List<HouseInfo> resultList = favoritesService
    // .queryFavoritesList(weixinId);
    //
    // mv.addObject("resultList", resultList);
    // mv.addObject("weixinId", weixinId);
    // return mv;
    // }

    @RequestMapping("/myList")
    public ModelAndView queryFavoritesHouseList(HttpServletRequest request,
                                                @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("personal/personal");

        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});

        log.debug("weixinId is " + openid);
        List<HouseVo> resultList = favoritesService.queryCollectList(// mapPoint,
                openid);

        // if (resultList == null) {
        // // throw new BizException("请检查定位设置是否打开");
        // // 返回提示页面，提示用户打开位置定位
        // mv = createMAV("myrent/note");
        // return mv;
        // }
        mv.addObject("resultList", resultList);
        mv.addObject("weixinId", openid);
        mv.addObject("type", "f");
        return mv;
    }

    @RequestMapping("/addCollect")
    public void collectHouse(ModelMap modelMap, Favorites favorites) {

        try {
            favoritesService.addCollect(favorites);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            modelMap.put("success", false);
        }

        modelMap.put("success", true);
    }

    @RequestMapping("/hasCollected")
    @ResponseBody
    public boolean hasCollected(HttpServletRequest request, @RequestParam(value = "houseId") String houseId) {
        boolean hasCollected = false;
        String openid = SessionUtil.getOpenId(request, authService);
        if (openid == null) {
            hasCollected = false;
        } else {
            hasCollected = favoritesService.hasCollected(openid, houseId);
        }
        return hasCollected;
    }
}
