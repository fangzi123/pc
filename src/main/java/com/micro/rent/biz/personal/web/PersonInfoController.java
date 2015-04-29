package com.micro.rent.biz.personal.web;

import com.micro.rent.biz.personal.service.FavoritesService;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.biz.weixin.service.AuthService;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.common.web.controller._BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/rental/person")
public class PersonInfoController extends _BaseController {
    @Autowired
    private FavoritesService favoritesService;
    @Autowired
    private AuthService authService;

    @RequestMapping("/queryPerson")
    public ModelAndView queryFavoritesList(HttpServletRequest request,
                                           @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("personal/person_info");
        return mv;
    }

    @RequestMapping("/info")
    public ModelAndView queryInfo(HttpServletRequest request,
                                  @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("personal/personal");

        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});

        // 默认显示用户当前位置所在的城市
//		MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
//				.get(openid);
//		mapPoint=new MapPoint("116.5","39.9");
//		openid="o00MJj9TY-UZSyqVfC5cZ5yy882E";
//		LocationEventMessageHandler.lrumap.put(openid, mapPoint);
//		request.getSession().setAttribute("openId", openid);
//		if (mapPoint == null) {
//			// throw new BizException("请检查定位设置是否打开");
//			// 返回提示页面，提示用户打开位置定位
//			mv = createMAV("myrent/note");
//			return mv;
//		}

        log.debug("weixinId is " + openid);

        List<HouseVo> resultList = favoritesService.queryCollectList(openid);
        mv.addObject("resultList", resultList);
        mv.addObject("weixinId", openid);
        mv.addObject("type", "f");

        return mv;
    }
}
