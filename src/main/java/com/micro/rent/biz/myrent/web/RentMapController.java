package com.micro.rent.biz.myrent.web;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.biz.myrent.service.MyRentService;
import com.micro.rent.biz.myrent.service.ProjectService;
import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.biz.myrent.vo.MapMatchWrap;
import com.micro.rent.biz.myrent.vo.MatchResultWrap;
import com.micro.rent.biz.myrent.vo.ProjectWrap;
import com.micro.rent.biz.weixin.service.AuthService;
import com.micro.rent.biz.weixin.service.impl.LocationEventMessageHandler;
import com.micro.rent.common.support.JsonUtils;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rental/map")
public class RentMapController extends _BaseController {

    @Resource
    private MapService mapService;
    @Autowired
    private MyRentService myRentService;
    @Autowired
    private AuthService authService;
    @Autowired
    private HouseService houseService;

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/displayMatch")
    public ModelAndView displayMatch(HttpServletRequest request,
                                     @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("myrent/rentmap");
        log.debug("code[{}]", new Object[]{code});

        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});

        // 默认显示用户当前位置所在的城市
        MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
                .get(openid);
        //mapPoint = new MapPoint("116.5", "39.9");

        // MapPoint mapPoint = crtMapPoint();
        if (mapPoint == null) {
            // throw new BizException("请检查定位设置是否打开");
            // 返回提示页面，提示用户打开位置定位
            mv = createMAV("myrent/note");
            return mv;
        }
        log.info(mapPoint.toString());

        // select All houseinfo
        Boolean hasProject = true;
        MatchResultWrap resultWrap = myRentService.findAllProject(mapPoint);

        if (resultWrap.getLstResult() == null
                || resultWrap.getLstResult().isEmpty()) {
            hasProject = false;
        }

        if (hasProject) {
            mv.addObject("resultList", resultWrap.getLstResult());
            mv.addObject("lstPoint",
                    JsonUtils.list2JsonString(resultWrap.getLstPoint()));
            mv.addObject("lstRt",
                    JsonUtils.list2JsonString(resultWrap.getLstRt()));
        } else {
            mv.addObject("message", "对不起，找不到合适的房源信息");
            mv.addObject("success", false);
        }
        mv.addObject("mapUrl", mapService.crtStaticMapUrl(null, mapPoint));

        mv.addObject("center", mapPoint);

        mv.addObject("weixinId", openid);
        return mv;
    }

    /**
     * 地图搜索
     *
     * @param request
     * @param code
     * @return
     */
    @RequestMapping("/displayMap")
    public ModelAndView displayMap(HttpServletRequest request,
                                   @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("myrent/rentmap");
        log.debug("code[{}]", new Object[]{code});

        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});

        // 默认显示用户当前位置所在的城市
        MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
                .get(openid);

//		mapPoint = new MapPoint("116.5", "39.9");
//		openid = "o00MJj9TY-UZSyqVfC5cZ5yy882E"; // TEST ONLY
//	     LocationEventMessageHandler.lrumap.put(openid, mapPoint);
//	     request.getSession().setAttribute("openId", openid);

        // MapPoint mapPoint = crtMapPoint();
        if (mapPoint == null) {
            // throw new BizException("请检查定位设置是否打开");
            // 返回提示页面，提示用户打开位置定位
            mv = createMAV("myrent/note");
            return mv;
        }
        log.info(mapPoint.toString());

        // select All houseinfo
        MapMatchWrap resultWrap = projectService
                .findAllProjectByProvince(mapPoint);
        Boolean hasProject = true;
        if (resultWrap.getLstResult() == null
                || resultWrap.getLstResult().isEmpty()) {
            hasProject = false;
        }

        if (hasProject) {
            mv.addObject("resultList", resultWrap.getLstResult());
            // mv.addObject("lstPoint",
            // JsonUtils.list2JsonString(resultWrap.getLstPoint()));
            mv.addObject("lstRt",
                    JsonUtils.list2JsonString(resultWrap.getLstResult()));
        } else {
            mv.addObject("message", "对不起，找不到合适的房源信息");
            mv.addObject("success", false);
        }

        mv.addObject("mapUrl", mapService.crtStaticMapUrl(null, mapPoint));

        mv.addObject("center", mapPoint);

        mv.addObject("weixinId", openid);
        return mv;
    }

//	@RequestMapping("/showMap")
//	@ResponseBody
//	public Map<String, Object> showMap(HttpServletRequest request,
//			@RequestParam(value = "code", required = false) String code) {
//		// ModelAndView mv = createMAV("myrent/rentmap");
//		// log.debug("code[{}]",new Object[]{code});
//		Map<String, Object> mv = new HashMap<String, Object>();
//
//		String openid = this.findStringParameterValue(request, "weixinId");
//		String lat = this.findStringParameterValue(request, "latitude");
//		String lng = this.findStringParameterValue(request, "longitude");
//		String distance = this.findStringParameterValue(request, "distance");
//		MapPoint mapPoint = new MapPoint(lng, lat);
//
//		Boolean hasProject = true;
//		MatchResultWrap resultWrap = myRentService.findCoverProject(mapPoint,
//				distance);
//		if (resultWrap.getLstResult() == null
//				|| resultWrap.getLstResult().isEmpty()) {
//			hasProject = false;
//		}
//
//		if (hasProject) {
//			mv.put("resultList", resultWrap.getLstResult());
//			mv.put("lstPoint",
//					JsonUtils.list2JsonString(resultWrap.getLstPoint()));
//			mv.put("lstRt", JsonUtils.list2JsonString(resultWrap.getLstRt()));
//		} else {
//			mv.put("message", "对不起，找不到合适的房源信息");
//			mv.put("success", false);
//		}
//		mv.put("mapUrl", mapService.crtStaticMapUrl(null, mapPoint));
//
//		mv.put("center", mapPoint);
//
//		mv.put("weixinId", openid);
//		return mv;
//	}

    @RequestMapping("/displayProject")
    @ResponseBody
    public ProjectWrap findProjectInfo(HouseInfo houseInfo) {//String projectId
        try {
            houseInfo.setCommunityName(new String(houseInfo.getCommunityName().getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {

        }
        ProjectWrap wrap = projectService.findProject(houseInfo);
        return wrap;
    }

    /**
     * 轮播一套公寓下 所有房屋的图片信息
     *
     * @param projectId
     * @return
     * @author wff
     */
    @RequestMapping("/display_houses_in_oneProject")
    @ResponseBody
    public List<HouseWrapVo> findHousesInOneProject(String projectId) {
        List<HouseWrapVo> hList = houseService.findHousesInOneProject(projectId);
        return hList;
    }

    @RequestMapping("/filterProject")
    public ModelAndView filterProject(HttpServletRequest request,
                                      @RequestParam(value = "code", required = false) String code,
                                      HouseInfo houseInfo) {
        ModelAndView mv = createMAV("myrent/rentmap");
        Map<String, Object> map = new HashMap<String, Object>();
        String layout = houseInfo.getLayout();
        BigDecimal longprice = houseInfo.getLongPrice();
        String price = null;
        if (longprice != null) {
            price = longprice.toBigInteger().toString();
        }
        map.put("layout", layout);
        map.put("ceiling", price);

        mv.addObject("layout", layout);
        mv.addObject("price", price);

        String openid = SessionUtil.getOpenId(request, authService);
        log.info("openid[{}]", new Object[]{openid});

        // 默认显示用户当前位置所在的城市
        MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
                .get(openid);
        //mapPoint = new MapPoint("116.5", "39.9");
        if (mapPoint == null) {
            // throw new BizException("请检查定位设置是否打开");
            // 返回提示页面，提示用户打开位置定位
            mv = createMAV("myrent/note");
            return mv;
        }
        MapMatchWrap resultWrap = projectService.findProjectBy(mapPoint, map);
        Boolean hasProject = true;
        if (resultWrap.getLstResult() == null
                || resultWrap.getLstResult().isEmpty()) {
            hasProject = false;
        }

        if (hasProject) {
            mv.addObject("resultList", resultWrap.getLstResult());
            // mv.addObject("lstPoint",
            // JsonUtils.list2JsonString(resultWrap.getLstPoint()));
            mv.addObject("lstRt",
                    JsonUtils.list2JsonString(resultWrap.getLstResult()));
        } else {
            mv.addObject("message", "对不起，找不到合适的房源信息");
            mv.addObject("success", false);
        }

        mv.addObject("mapUrl", mapService.crtStaticMapUrl(null, mapPoint));
        mv.addObject("center", mapPoint);
        mv.addObject("weixinId", openid);

        return mv;
    }

    @RequestMapping("/filterMap")
    public ModelAndView filterMap(HttpServletRequest request,
                                  @RequestParam(value = "code", required = false) String code) {
        ModelAndView mv = createMAV("myrent/map_filter");
        String openid = SessionUtil.getOpenId(request, authService);
        mv.addObject("weixinId", openid);
        return mv;
    }

    /**
     * 功能描述：根据区域搜索区域内房源数量
     * 作者: 张波波
     * 日期： 2014.11.28
     */
    @RequestMapping("/searchHouseCntByZone")
    @ResponseBody
    public void searchHouseCntByZone(HttpServletRequest request,
                                     @RequestParam(value = "areaMaxLng", required = false) String areaMaxLng,
                                     @RequestParam(value = "areaMaxLat", required = false) String areaMaxLat,
                                     @RequestParam(value = "areaMinLng", required = false) String areaMinLng,
                                     @RequestParam(value = "areaMinLat", required = false) String areaMinLat,
                                     @RequestParam(value = "zoneLevel", required = false) String zoneLevel) {
        // 根据zoneLevel不同检索不同的区域
        int zoneLev = Integer.valueOf(zoneLevel);
        Map<String, String> zoonName = new HashMap<String, String>();
        if (zoneLev < 12) {
            zoonName.put("zoonName", "行政区");
        } else if (zoneLev < 15) {
            zoonName.put("zoonName", "地区");
        } else {
            zoonName.put("zoonName", "小区");
        }
    }
}
