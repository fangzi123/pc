package com.micro.rent.pc.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.biz.enum_.OrderStatus;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.support.CookieUtil;
import com.micro.rent.common.support.DateUtil;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.common.web.service.EmailService;
import com.micro.rent.pc.entity.House;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.TenantOrder;
import com.micro.rent.pc.entity.vo.OrderVo;
import com.micro.rent.pc.service.TenantService;
import com.micro.rent.pc.util.JedisUtil;
import com.micro.rent.pc.web.shiro.ShiroTenantRealm.ShiroTenant;

@Controller
@RequestMapping("/order")
public class PreOrderController extends _BaseController {
    @Autowired
    private TenantService tenantService;

    @Autowired
    private EmailService emailService;
    /**
     * @Title: addPreOrder
     * @Description: 确认预约
     * @param request
     * @param order
     * @param modelMap
    	* @author: wff
     * @return: void
     * @throws Exception 
     */
    @RequestMapping(value = "add_preOrder", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap addPreOrder(HttpServletRequest request,HttpServletResponse response, OrderVo order,
            ModelMap modelMap) throws Exception {
        Integer houseId = this
                .findIntegerParameterValue(request, "houseId");
        String orderDate = this.findStringParameterValue(request,
                "orderDate");
            try {
                order.setVisitingTime(DateUtil.getDate(orderDate,
                        Constants.DATE_FORMAT_NO_SEPARATOR));
                if(ShiroHelper.isAuth()){ //已登录  只选日期
                    order.setMobile(ShiroHelper.getShiroTenant().getMobile());
                    order.setTenantId(ShiroHelper.getTenantId());
                    TenantOrder to= tenantService.addTenantOrder(order);
                    if(to.getId()!=null){
                        modelMap.put("success", true);//预约成功
                    }else{
                        modelMap.put("message","已预约该房子,house_id=" + houseId
                                + ",mobile=" + ShiroHelper.getShiroTenant().getMobile());
                    }
                }else{//未登录
                    Cookie cookieHou = CookieUtil.getCookie(request, House.PREORDER_HOUSE);
                    Cookie cookie = CookieUtil.getCookie(request, House.PREORDER_MOBILE);
                    String preOrderInfo="";
                    String mobile="";
                    if (cookie == null) {//第一次预约或者cookie里无值  输验证码
                        mobile = this.findStringParameterValue(request, "mobile");
                        preOrderInfo=mobile+"_"+houseId;
                        String verifyCode = this
                                .findStringParameterValue(request, "verifyCode");
                        if(this.isTheSameVerifyCode(request, mobile, verifyCode)){
                            CookieUtil.saveCookie(response, House.PREORDER_HOUSE,
                                    preOrderInfo, null);
                            CookieUtil.saveCookie(response, House.PREORDER_MOBILE,
                                    mobile, null);
                            modelMap.put("success", true);//预约成功
                            tenantService.addTenantOrder(order);
                        }else{
                            modelMap.put("success", false);
                            modelMap.put("message", "输入的验证码有误，请重新输入！");
                        }
                    } else {//第二次 只选日期
                        mobile=cookie.getValue();
                        preOrderInfo=mobile+"_"+houseId;
                        boolean isPreOrder = containInfo(cookieHou, preOrderInfo);
                        if (isPreOrder) {
                            modelMap.put("message","已预约该房子,house_id=" + houseId
                                    + ",mobile=" + mobile);
                        } else {
                            // 收藏的cookie格式 des(houseId).base64 , des(houseId).base64 ...
                            String val = String.format("%s,%s", cookieHou.getValue(),
                                    preOrderInfo);
                            cookieHou.setValue(val);
                            modelMap.put("success", true);//预约成功
                            order.setMobile(mobile);
                            tenantService.addTenantOrder(order);
                        }
                        response.addCookie(cookieHou);
                    }
                }
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("message", "输入的日期有误，请重新输入！");
            }
        return modelMap;
    }

    @RequestMapping(value = "add_preOrder", method = { RequestMethod.GET })
    public ModelAndView showPreOrder() {
        ModelAndView mv = new ModelAndView("");
        ShiroTenant sUser = ShiroHelper.getShiroTenant();
        if (sUser != null) {
            mv.addObject("mobile", sUser.getMobile());
            mv.addObject("name", sUser.getUserName());
        }
        return mv;
    }

}
