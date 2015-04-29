package com.micro.rent.biz.myrent.web;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.biz.myrent.service.MyRentService;
import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.biz.myrent.vo.MatchResultWrap;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import com.micro.rent.biz.personal.service.FavoritesService;
import com.micro.rent.biz.personal.service.OrderService;
import com.micro.rent.biz.weixin.service.AuthService;
import com.micro.rent.biz.weixin.service.impl.LocationEventMessageHandler;
import com.micro.rent.common.support.SessionUtil;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rental/match")
public class AutoMatchController extends _BaseController {

    @Autowired
    private MyRentService myRentService;
    @Resource(name = "baiduMapService")
    private MapService mapService;
    @Autowired
    private AuthService authService;

    @Autowired
    private HouseService houseService;

    @Autowired
    private FavoritesService favoritesService;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/displayMatch")
    public ModelAndView displayMatch(HttpServletRequest request,
                                     @RequestParam(value = "code", required = false) String code, RedirectAttributes redirectAttributes) throws Exception {
        log.debug("Request code:" + code);
        ModelAndView mv = createMAV("myrent/new_match");
        String cityName = "";
        String cityCode = "";
        String workPlace = this.findStringParameterValue(request, "workPlace");
        String weixinId = SessionUtil.getOpenId(request, authService);

        // 获取坐标
        MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap.get(weixinId);
        // 推荐房源
        List<HouseInfo> houseList = houseService.searchRecommendHouseList(); // 推荐房源列表（重复）
        mv.addObject("houseList", houseList);
        // 猜你喜欢房源
        if (mapPoint != null) {
            List<HouseInfo> nearHouseList =
                    houseService.searchNearbyHouses(mapPoint.getLat().doubleValue(), mapPoint.getLng().doubleValue());
            mv.addObject("nearHouseList", nearHouseList);
        }

        if (StringUtils.isEmpty(workPlace)) {
            if (mapPoint != null) {
                // 调用地图api，获取具体地址和城市
                Map<String, String> mpCity = mapService.getPlace(mapPoint);

                cityName = mpCity.get("city");
                workPlace = mpCity.get("address");
            }
        }
        log.debug("weixinId is {}", new Object[]{weixinId});

        // if(!StringUtils.isNotBlank(code)){
        // MapPoint mapPoint=new MapPoint(new BigDecimal(116.445935),new
        // BigDecimal(39.880329));//测试
        // if (mapPoint != null) {
        // //调用地图api，获取具体地址和城市
        // Map<String,String> mpCity= mapService.getPlace(mapPoint);
        //
        // cityName = mpCity.get("city");
        // address = mpCity.get("address");
        //
        // weixinId="11111";
        // }
        // }

        // 如果city为北京或者上海，则返回给前台，否则默认为北京市
        if ("北京市".equals(cityName)) {
            cityName = "北京";
            cityCode = "110100";
        } else if ("上海市".equals(cityName)) {
            cityName = "上海";
            cityCode = "310100";
        } else {
            cityName = "北京";
            cityCode = "110100";
        }
        mv.addObject("cityName", cityName);
        mv.addObject("cityCode", cityCode);
        mv.addObject("weixinId", weixinId);
        mv.addObject("workPlace", workPlace);
        mv.addObject("trafficType", this.findStringParameterValue(request, "trafficType"));
        mv.addObject("longPrice", this.findStringParameterValue(request, "longPrice"));
        mv.addObject("layout", this.findStringParameterValue(request, "layout"));
        return mv;
    }

    @RequestMapping("/displayMatch2")
    public ModelAndView displayMatch2(HttpServletRequest request) {
        ModelAndView mv = createMAV("myrent/match");

        mv.addObject("cityName",
                this.findStringParameterValue(request, "cityName"));
        mv.addObject("cityCode",
                this.findStringParameterValue(request, "cityCode"));
        mv.addObject("workPlace",
                this.findStringParameterValue(request, "workPlace"));
        mv.addObject("longPrice",
                this.findStringParameterValue(request, "longPrice"));
        mv.addObject("weixinId",
                this.findStringParameterValue(request, "weixinId"));
        mv.addObject("trafficType",
                this.findStringParameterValue(request, "trafficType"));
        mv.addObject("trafficName",
                this.findStringParameterValue(request, "trafficName"));
        mv.addObject("rentalType",
                this.findStringParameterValue(request, "rentalType"));
        mv.addObject("rentalName",
                this.findStringParameterValue(request, "rentalName"));
        return mv;
    }

    @RequestMapping("/new_matchResult")
    public ModelAndView matchResult(HttpServletRequest request,
                                    MyRentQueryVo queryVo, RedirectAttributes redirectAttributes) throws Exception {
        String weixinId = this.findStringParameterValue(request, "weixinId");
        // 根据查询条件搜索项目列表-前台校验必输项
        ModelAndView mv = createMAV("myrent/new_match_result");
        boolean moveToSearch = false;
        int noneFlag = 1;//检索不到房源时，1代表价格搜不到，0代表地点不存在
        try {
            MatchResultWrap resultWrap = myRentService.findAllHouseByQueryVo(queryVo);

            if (resultWrap.getLstResult() == null
                    || resultWrap.getLstResult().isEmpty()) {
                // 根据搜索条件，无房源存在
                moveToSearch = true;
            } else {
                // 根据搜索条件，有房源存在
                // String staticMapUrl = resultWrap.getStaticUrl();
                // mv.addObject("mapImg", staticMapUrl);
                mv.addObject("houseList", resultWrap.getLstResult());
                mv.addObject("queryVo", queryVo);
                // mv.addObject("lstPoint",
                // JsonUtils.list2JsonString(resultWrap.getLstPoint()));
                mv.addObject("weixinId", weixinId);
            }

        } catch (Exception e) {
            moveToSearch = true;
            noneFlag = 0;
        }

        if (moveToSearch) {
            mv = createMAV("myrent/new_match");
            mv.addObject("workPlace", queryVo.getWorkPlace());
            // 获取坐标
            MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap.get(weixinId);
            if (mapPoint != null) {
                // 推荐房源
                List<HouseInfo> houseList = houseService.searchRecommendHouseList(); // 推荐房源列表（重复）
                mv.addObject("houseList", houseList);
                // 猜你喜欢房源
                List<HouseInfo> nearHouseList =
                        houseService.searchNearbyHouses(mapPoint.getLat().doubleValue(), mapPoint.getLng().doubleValue());
                mv.addObject("nearHouseList", nearHouseList);
            }
            mv.addObject("trafficType", queryVo.getTrafficType());
            mv.addObject("longPrice", queryVo.getLongPrice());
            mv.addObject("layout", queryVo.getLayout());
            mv.addObject("weixinId", weixinId);
            mv.addObject("cityName", queryVo.getCityName());
            mv.addObject("isPlace", false);
            mv.addObject("noneFlag", noneFlag);
        }
        return mv;
    }

    @RequestMapping("/new_matchResultAjax")
    public void matchResultAjax(HttpServletRequest request,
                                MyRentQueryVo queryVo, ModelMap mm) {
        Boolean hasProject = true;
        // 根据查询条件搜索项目列表-前台校验必输项
        MatchResultWrap resultWrap = null;
        if (queryVo.getTrafficType() != null) {// 搜索页面
            resultWrap = myRentService.findAllHouseByQueryVo(queryVo);
        } else {
            resultWrap = myRentService.findOneProjectByQueryVo(queryVo);
        }
        if (resultWrap.getLstResult() == null
                || resultWrap.getLstResult().isEmpty()) {
            hasProject = false;
        }
        if (hasProject) {
            mm.put("success", true);
            mm.put("houseList", resultWrap.getLstResult());
            mm.put("queryVo", queryVo);
        } else {
            mm.put("message", "对不起，找不到合适的房源信息，请重新输入条件查询");
            mm.put("success", false);
        }
        mm.put("weixinId", this.findStringParameterValue(request, "weixinId"));
    }

    @RequestMapping("/projectHousResult")
    public ModelAndView projectHousResult(HttpServletRequest request,
                                          MyRentQueryVo queryVo) {
        Boolean hasProject = true;

        // 根据查询条件搜索项目列表-前台校验必输项
        MatchResultWrap resultWrap = myRentService
                .findOneProjectByQueryVo(queryVo);

        ModelAndView mv = createMAV("myrent/new_match_result");
        if (resultWrap.getLstResult() == null
                || resultWrap.getLstResult().isEmpty()) {
            hasProject = false;
        }

        if (hasProject) {
            // String staticMapUrl = resultWrap.getStaticUrl();
            // mv.addObject("mapImg", staticMapUrl);
            mv.addObject("houseList", resultWrap.getLstResult());
            mv.addObject("queryVo", queryVo);
            mv.addObject("weixinId", queryVo.getWeixinId());
            // mv.addObject("lstPoint",
            // JsonUtils.list2JsonString(resultWrap.getLstPoint()));

        } else {
            mv.addObject("message", "对不起，找不到合适的房源信息，请重新输入条件查询");
            mv.addObject("success", false);
        }

        mv.addObject("weixinId",
                this.findStringParameterValue(request, "weixinId"));
        return mv;
    }

    @RequestMapping("/detail")
    public ModelAndView findHouseInfo(@RequestParam(value = "houseId") String houseId, @RequestParam(value = "duration", required = false) String duration,
                                      @RequestParam(value = "weixinId", required = false) String weixinId) {
        ModelAndView mv = createMAV("myrent/house_info");

        HouseWrapVo houseInfo = houseService.findHouseInfoByHouseId(houseId);

        if (houseInfo == null) {
            mv.addObject("message", "对不起，找不到房源信息");
            mv.addObject("success", false);
        } else {
//			duration=houseService.getDuration(mapPoint.getLat(), mapPoint.getLng(), houseInfo.getLatitude(), houseInfo.getLongitude());
//			houseInfo.setDuration(duration);
//			houseInfo.setDuration(duration);
            if (houseInfo.getStreet() == null) {
                houseInfo.setStreet("");
            }

            if (duration != null) {
                try {
                    houseInfo.setDuration(new String(duration.getBytes("iso8859-1"), "UTF-8"));
                } catch (UnsupportedEncodingException e) {

                }
            }

//			boolean hasCollected = favoritesService.hasCollected(weixinId,
//					houseId);
            boolean hasOrdered = orderService.hasOrdered(weixinId, houseId);
            mv.addObject("houseInfo", houseInfo);
            mv.addObject("duration", duration);
            mv.addObject("weixinId", weixinId);
            // mv.addObject("picSize", houseInfo.getImgList().size()-1);
            mv.addObject("price", houseInfo.getLongPrice().toBigInteger());
//			mv.addObject("hasCollected", hasCollected);
            mv.addObject("hasOrdered", hasOrdered);
        }
        return mv;
    }

}
