package com.micro.rent.pc.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micro.rent.common.support.CookieUtil;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.entity.House;
import com.micro.rent.pc.service.FavoriteService;

@Controller
@RequestMapping("/pc/collect")
public class FavoriteController extends _BaseController {
    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping(value = "/house", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap collectHouse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelMap mm = new ModelMap();
        Integer houseId = this.findIntegerParameterValue(request, "houseId");
        // TODO 未判断房源是否存在
        String collectInfo = houseId + "";
        boolean collect = false;
        if (ShiroHelper.isAuth()) {
            Integer tenantId = ShiroHelper.getTenantId();
            Favorite favorite = favoriteService.getFavoriteHouse(tenantId,
                    houseId);
            if (favorite == null) {
                favoriteService.addFavoriteHouse(tenantId, houseId);
                collect = true;
            } else {
                Integer id = favorite.getId();
                if (id != null) {
                    String flag = favorite.getFlag();
                    char f = favoriteService.changeFavoriteFlag(id, flag);
                    if (f == '0') {
                        collect = false;
                    } else {
                        collect = true;
                    }
                }

            }
        } else {
            Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
            if (cookie == null) {
                CookieUtil.saveCookie(response, House.FAVORITE_HOUSE,
                        collectInfo, null);
                collect = true;
            } else {
                collect = !containInfo(cookie, houseId+"");
                // 收藏的cookie格式 des(houseId).base64 , des(houseId).base64 ...

                if (collect) {
                    String val = String.format("%s,%s", cookie.getValue(),
                            collectInfo);
                    cookie.setValue(val);

                } else {
                    String hid =houseId + "";
                    String val = cookie.getValue();
                    if (val.startsWith(hid)) {
                        val = val.replace(hid, "");
                    } else {
                        val = val.replace("," + hid, "");
                    }

                    cookie.setValue(val);
                }

                response.addCookie(cookie);

            }

        }
        mm.addAttribute("collect", collect);
        return mm;
    }

    @RequestMapping(value = "/house/is", method = { RequestMethod.POST })
    @ResponseBody
    public ModelMap isCollectHouse(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelMap mm = new ModelMap();
        Integer houseId = this.findIntegerParameterValue(request, "houseId");
        boolean collect = false;
        if (ShiroHelper.isAuth()) {
            Integer tenantId = ShiroHelper.getTenantId();
            Favorite favorite = favoriteService.getFavoriteHouse(tenantId, houseId);
            if (favorite == null) {
                collect = false;
            } else if (favorite.getId() != null) {
                char f = favorite.getFlag().charAt(0);
                if (f == '1') {
                    collect = true;
                } 
            }
        } else {
            Cookie cookie = CookieUtil.getCookie(request, House.FAVORITE_HOUSE);
            if (cookie == null) {
                collect = false;
            } else {
                // 收藏的cookie格式 des(houseId).base64 , des(houseId).base64 ...
                collect = containInfo(cookie, houseId+"");
            }
        }
        mm.addAttribute("collect", collect);
        return mm;
    }

    

}
