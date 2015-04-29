package com.micro.rent.pc.web.shiro;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.micro.rent.common.comm.IPUtil;
import com.micro.rent.common.support.CookieUtil;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.entity.House;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.BrandService;
import com.micro.rent.pc.service.FavoriteService;
import com.micro.rent.pc.service.TenantService;

@Controller
// @RequestMapping("/pc/authc")
public class AuthController extends _BaseController {
    private String LOGIN = "/";
    // private String HOME = "redirect:/";

    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BrandService brandService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private TenantService tenantService;
    /**
     * 登录身份验证
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public ModelMap auth(HttpServletRequest request,
            HttpServletResponse response,ModelMap mm) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        log.debug("user principal: {}", new Object[] { subject.getPrincipal() });
        log.debug("user authenticated: {}",
                new Object[] { subject.isAuthenticated() });

        if (!subject.isAuthenticated()) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String rememberMe = request.getParameter("rememberMe");

            if (subject.isRemembered()) {
                // ShiroTenant sUser = (ShiroTenant) subject.getPrincipal();
                // username = sUser.getUserName();
                // password=
            } else if (username == null || password == null) {
               mm.addAttribute("success", false);
               return mm;
            }

            UsernamePasswordToken objToken = new UsernamePasswordToken(
                    username, password);
            if ("true".equals(rememberMe)) {
                objToken.setRememberMe(true);
            }
            try {
                subject.login(objToken);
//                List<BrandVo> brandList = brandService.getRecommendBrandList(IPUtil.getRegion());
//                mv.addObject(brandList);
            } catch (UnknownAccountException uae) {
                log.info("不存在用户[{}]", new Object[] { objToken.getPrincipal() });
                mm.addAttribute("success", false);
                return mm;

            } catch (IncorrectCredentialsException ice) {
                log.info("用户[{}]密码错误", new Object[] { objToken.getPrincipal() });
                mm.addAttribute("success", false);
                return mm;

            } catch (DisabledAccountException dae) {
                log.info(dae.getMessage(), dae);
                mm.addAttribute("success", false);
                return mm;
            } catch (Throwable t) {
                log.info(t.getMessage(), t);
                mm.addAttribute("success", false);
                return mm;
            }
        }

        addFavorite(request, response);
        mm.addAttribute("success", true);
        return mm;
    }

    /**
     * 登录后将cookie中的收藏信息入库
     *
     * @Title: addFavorite
     * @Description: TODO
     * @param request
     * @return: void
     */
    private void addFavorite(HttpServletRequest request,
            HttpServletResponse response) {
        Integer tenantId = ShiroHelper.getTenantId();
        Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
        if (cookie != null) {
            List<Integer> houseIds = favoriteService
                    .getFavoriteHouseId(tenantId);
            for (String hid : cookie.getValue().split(",")) {
                if(StringUtils.isNotBlank(hid)){
                    Integer houseId = Integer.parseInt(hid);
                    if (houseIds.contains(houseId)) {
                        // 已收藏
                    } else {
                        
                        Favorite favorite = favoriteService.addFavoriteHouse(
                                tenantId, houseId);
                        if (favorite == null) {
                            // houseId 无效
                        } else {
                            houseIds.add(houseId);
                        }
                    }
                }
            }
            CookieUtil.removeCookie(response, House.FAVORITE_HOUSE);
        }

    }

    /**
     * 登出
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        SecurityUtils.getSubject().logout();
    }

//    @RequestMapping(value = "/login", method = { RequestMethod.GET })
//    public ModelAndView toLogin(HttpServletRequest request) throws Exception {
//        ModelAndView mv = new ModelAndView("login");
//        return mv;
//    }
    
    @RequestMapping(value = "/register", method = { RequestMethod.POST })
    public ModelMap register(HttpServletRequest request,ModelMap mm) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Tenant tenant=tenantService.getTenantByMobile(username);
        if(tenant==null){
            tenantService.registerTenant(username, password);
        }else{
            mm.addAttribute("success", false);
            return mm;
        }
        mm.addAttribute("success", true);
        return mm;
    }

}
