package com.micro.rent.pc.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.CookieUtil;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.House;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.vo.ActivityVo;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.service.ActivityService;
import com.micro.rent.pc.service.FavoriteService;
import com.micro.rent.pc.service.HouseService;
import com.micro.rent.pc.service.TenantService;
import com.micro.rent.pc.util.Constants;

/**
 * 描述: 用户相关信息操作
 * @author zbb
 */
@Controller
@RequestMapping("/user")
public class UserInfoCenterController extends _BaseController {

    @Autowired
    private FavoriteService favoriteService;
    
    @Autowired
    private TenantService tenantService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private HouseService houseService;
    /**
     * 描述: 获取个人收藏房源入口
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/favorite",  method=RequestMethod.GET)
    public ModelAndView getFavHouseList(HttpServletRequest request, ModelMap modelMap,
            HttpServletResponse response) 
            throws Exception {
        ModelAndView mv = this.createMAV("userInfo/userInfoCenter");
        Page<HouseVo> page = new Page<HouseVo>();
        page.setPageLimit(12);
        // 个人信息
        Tenant tenantInfo = new Tenant();
        if (ShiroHelper.isAuth()) {
            Map<?, ?> map=this.left(ShiroHelper.getShiroTenant().getMobile());
            tenantInfo=(Tenant) map.get("tenantInfo");
            // 收藏房源
            page.getParams().put("mobile", map.get("mobile"));
            favoriteService.getFavoriteHouseForPage(page);
            mv.addObject("headImgFlg", map.get("headImgFlg"));
        } else {
            Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
            if (cookie != null) {
                String houseIds = cookie.getValue();
                houseIds= houseIds.replaceAll("^,", "");
                if (houseIds.length() != 0) {
                    page.getParams().put("houseIds", houseIds);
                    houseService.searchHousesByhouseIds(page);
                }
            }
            Cookie cookie_mobile = CookieUtil.getCookie(request, House.PREORDER_MOBILE);
            if(cookie_mobile==null){
                mv.addObject("cookie_mobile_null",true);
            }
        }
        
        mv.addObject("pageList", page);
        mv.addObject("tenant", tenantInfo);
        mv.addObject("tab", Constants.MYFAVORITE);
        return mv;
    }
    
    /**
     * 描述: 获取个人收藏房源（AJAX）
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/favorite", method=RequestMethod.POST)
    public void getFavHouseListAjax(HttpServletRequest request, ModelMap modelMap) 
            throws Exception {
        Page<HouseVo> page = this.findPageFinal(request);
        Integer pageSt=this.findIntegerParameterValue(request, "pageStart");
        if (pageSt == null || pageSt.equals(0)) {
            pageSt = 1;
        }
        page.setPageStart(pageSt);
        page.setPageLimit(12);
        if(ShiroHelper.isAuth()){
            page.getParams().put("mobile", ShiroHelper.getShiroTenant().getUserName());
            favoriteService.getFavoriteHouseForPage(page);
        }
        else {
            Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
            if (cookie != null) {
                String houseIds = cookie.getValue();
                houseIds= houseIds.replaceAll("^,", "");
                if (houseIds.length() != 0) {
                    page.getParams().put("houseIds", houseIds);
                    houseService.searchHousesByhouseIds(page);
                }
            }
        }
        modelMap.addAttribute(page);
    }
    
    /**
     * 描述: 删除个人收藏房源
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/favorite", method=RequestMethod.POST, params="houseId")
    public void delFavHouseAjax(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) 
            throws Exception {
        String tenantId=this.findStringParameterValue(request, "id");
        String houseId=this.findStringParameterValue(request, "houseId");
        if(ShiroHelper.isAuth()){
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("id", tenantId);
            paramMap.put("houseId", houseId);
            favoriteService.deleteFavouriteHouse(paramMap);
        }else{
            Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
            String val = cookie.getValue();
            val=val.replaceAll(",?"+houseId, "");
            cookie.setValue(val);
            response.addCookie(cookie);
        }
    }
    
    /**
     * 描述: 获取个人预约房源入口
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/preOrder",  method=RequestMethod.GET)
    public ModelAndView getOrderHouseList(HttpServletRequest request) 
            throws Exception {
       ModelAndView mv = this.createMAV("userInfo/userInfoCenter");
       String mobile="";
       Cookie cookie = CookieUtil.getCookie(request, House.PREORDER_MOBILE);
       if(ShiroHelper.isAuth()){
           mobile=ShiroHelper.getShiroTenant().getMobile();
       }else{
           if(cookie==null){
               mobile=this.findStringParameterValue(request, "mobile");
               if(StringUtils.isBlank(mobile)){
                   Page<HouseVo> page = new Page<HouseVo>();
                   mv.addObject("pagePreOrder", page);
                   mv.addObject("cookie_mobile_null",true);
                   mv.addObject("tab", Constants.MYPREORDER);
                   return mv;
               }
           }else{
               mobile=cookie.getValue();
           }
       }
       Map<?, ?> map=this.left(mobile);
       Page<HouseVo> page = new Page<HouseVo>();
       page.setPageLimit(12);
       page.getParams().put("mobile", mobile);
       tenantService.getOrderedHouseForPage(page);
       mv.addObject("tenant", map.get("tenantInfo"));
       mv.addObject("headImgFlg",map.get("headImgFlg"));
       mv.addObject("pagePreOrder", page);
       mv.addObject("tab", Constants.MYPREORDER);
       return mv;
    }
    
    /**
     * 描述: 获取个人预约房源（AJAX）
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/preOrder")
    public void getOrderHouseListAjax(HttpServletRequest request, ModelMap modelMap) 
            throws Exception {
        Cookie cookie = CookieUtil.getCookie(request, House.PREORDER_MOBILE);
        String mobile="";
        if(ShiroHelper.isAuth()){
            mobile=ShiroHelper.getShiroTenant().getUserName();
        }
        else{
            if(cookie==null){
                Page<HouseVo> page = new Page<HouseVo>();
                modelMap.addAttribute(page);
                return;
            }else{
                mobile=cookie.getValue();
            }
        }
        Page<HouseVo> page = this.findPageFinal(request);
        Integer pageSt = this.findIntegerParameterValue(request, "pageStart");
        if (pageSt == null || pageSt.equals(0)) {
            pageSt = 1;
        }
        page.setPageStart(pageSt);
        page.setPageLimit(12);
        page.getParams().put("mobile", mobile);
        tenantService.getOrderedHouseForPage(page);
        modelMap.addAttribute(page);
    }
    
    
    /**
     * 描述: 删除个人预约房源
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/preOrder", params="houseId")
    public void delOrderHouseAjax(HttpServletRequest request,HttpServletResponse response, ModelMap modelMap) 
            throws Exception {
        String id=this.findStringParameterValue(request, "id");
        String houseId=this.findStringParameterValue(request, "houseId");
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("houseId", houseId);
        if(ShiroHelper.isAuth()){
            paramMap.put("id", id);
        }else{
            Cookie cookie = CookieUtil.getCookie(request, House.PREORDER_HOUSE);
            String mobile=CookieUtil.getCookie(request, House.PREORDER_MOBILE).getValue();
            String val = cookie.getValue();
            val=val.replaceAll(",?"+mobile+"_"+houseId, "");
            cookie.setValue(val);
            response.addCookie(cookie);
            paramMap.put("mobile", mobile);
        }
        tenantService.deleteOrderedHouse(paramMap);
        modelMap.addAttribute(true);
    }
    
    /**
     * @Description: 我的活动入口
     * @param request
     * @return
     * @throws Exception
    	* @author: wff
     * @return: ModelAndView
     */
    @RequestMapping(value = "/activity",method=RequestMethod.GET)
    public ModelAndView getOActivityList(HttpServletRequest request) 
            throws Exception {
        Map<?, ?> map=this.left(ShiroHelper.getShiroTenant().getMobile());
        Page<ActivityVo> page = new Page<ActivityVo>();
        page.getParams().put("mobile", map.get("mobile"));
        page.setPageLimit(9);
        activityService.selectActivityByTenantId(page);
        List<ActivityVo> results = page.getResults();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        float now = Float.valueOf(sdf.format(new Date()));
        for (int i=0; i<results.size(); i++) {
            float stTime = Float.valueOf(results.get(i).getStartTime().replace("-", ""));
            float endTime = Float.valueOf(results.get(i).getEndTime().replace("-", ""));
            if (stTime > now) {
                results.get(i).setDataFlg("0");
            } else if (now >= stTime && now <= endTime) {
                results.get(i).setDataFlg("1");
            } else if (now > endTime) {
                results.get(i).setDataFlg("2");
            }
        }
        ModelAndView mv = this.createMAV("userInfo/userInfoCenter");
        mv.addObject("tenant", map.get("tenantInfo"));
        mv.addObject("headImgFlg",map.get("headImgFlg"));
        mv.addObject("pageActivity", page);
        mv.addObject("tab", Constants.MYACTIVITY);
        return mv;
    }
    
    /**
     * 描述: 获取个人参加活动
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/activity")
    public void getOActivityListAjax(HttpServletRequest request, ModelMap modelMap) 
            throws Exception {
        // 预约房源
        Page<ActivityVo> page = this.findPageFinal(request);
        Integer pageSt = this.findIntegerParameterValue(request, "pageStart");
        if (pageSt == null || pageSt.equals(0)) {
            pageSt = 1;
        }
        page.setPageStart(pageSt);
        page.setPageLimit(9);
        page.getParams().put("mobile", ShiroHelper.getShiroTenant().getUserName());
        activityService.selectActivityByTenantId(page);
        modelMap.addAttribute(page);
    }
    
    
    /**
     * 描述: 删除个人参加活动
     * @param request
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/activity", params="houseId")
    public void delActivityAjax(HttpServletRequest request, ModelMap modelMap) 
            throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tenantId", this.findStringParameterValue(request, "id"));
        paramMap.put("activityId", this.findStringParameterValue(request, "houseId"));
        activityService.deleteActivity(paramMap);
        modelMap.addAttribute(true);
    }
    /**
     * @Title: updateTenant
     * @Description: 更新个人资料
    	* @author: wff
     * @return: void
     */
    @RequestMapping(value = "/updateTenant")
    public void updateTenant(HttpServletRequest request,Tenant tenant, ModelMap mm) 
            throws Exception {
        tenant.setMobile(ShiroHelper.getShiroTenant().getMobile());
        tenantService.updateTenantInfoByMobile(tenant);
        mm.addAttribute("success", true);
    }
    /**
     * @Description: 注销账户
     * @throws Exception
    	* @author: wff
     * @return: void
     */
    @RequestMapping(value = "/unsubscribe")
    public void unsubscribe(HttpServletRequest request, ModelMap mm) 
            throws Exception {
        String mobile=ShiroHelper.getShiroTenant().getMobile();
        tenantService.deleteTenant(mobile);
        mm.addAttribute("success", true);
    }
    /**
     * @Description: 个人资料入口
     * @throws Exception
    	* @author: wff
     * @return: void
     */
    @RequestMapping(value = "/mydata")
    public ModelAndView mydata(HttpServletRequest request) 
            throws Exception {
        ModelAndView mv = this.createMAV("userInfo/userInfoCenter");
        Map<String, Object> map=this.left(ShiroHelper.getShiroTenant().getMobile());
        mv.addObject("tenant", map.get("tenantInfo"));
        mv.addObject("headImgFlg", map.get("headImgFlg"));
        mv.addObject("tab", Constants.MYDATA);
        return mv;
    }
    /**
     * @Title: left
     * @Description: 个人中心页面左侧逻辑
     * @return
     * @throws Exception
    	* @author: wff
     * @return: Map
     */
    public Map<String, Object> left(String mobile) throws Exception{
     // 个人信息
        Tenant paramTen = new Tenant();
        paramTen.setMobile(mobile);
        Tenant tenantInfo = tenantService.searchTenantInfo(paramTen);
        // 头像
        boolean headImgFlg = false;
        if(tenantInfo!=null){
            String headImg = tenantInfo.getHeadImg();
            if (headImg != null && headImg.substring(0, 4) == "http") {
                // headImg为微信头像
                headImgFlg = true;
            }
        }
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("mobile", paramTen.getMobile());
        map.put("headImgFlg", headImgFlg);
        map.put("tenantInfo", tenantInfo);
        return map;
    } 
}
