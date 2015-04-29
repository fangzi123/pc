package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.SetFuncMenuService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.SetFuncMenu;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetFuncMenuController extends _BaseController {

    //注入setFuncMenuService
    private SetFuncMenuService setFuncMenuService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示菜单管理页面
     * @author
     */
    public ModelAndView menuDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String _search_lv = "_search_lv", _search_menuName = "_search_menuName";

        String lv = this.findStringParameterValue(request, _search_lv);
        String menuName = this.findStringParameterValue(request, _search_menuName);

        String _search_key = null, _search_value = null;
        if (lv != null) {
            _search_key = _search_lv;
            _search_value = lv;
        } else if (menuName != null) {
            _search_key = _search_menuName;
            _search_value = menuName;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("lv", lv);
        params.put("menuName", menuName);

        // 查询分页记录
        Page<SetFuncMenu> page = findPageFinal(request);
        page.setParams(params);

        setFuncMenuService.queryPaged(page);

        return createMAV("/mt/menu/menu_read")
                .addObject("_search_key", _search_key)
                .addObject("_search_value", _search_value)
                .addObject("pageList", page);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示创建菜单页面
     * @author
     */
    public ModelAndView menuPersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subBtn = this.findStringParameterValue(request, "subBtn");
        if (subBtn.equals(Constants._add)) {
            return menuCreateDisplay(request, response);
        } else if (subBtn.equals(Constants._update)) {
            return menuUpdateDisplay(request, response);
        } else {
            return menuDisplay(request, response);
        }

    }

    private ModelAndView menuCreateDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SetFuncMenu> menus = setFuncMenuService.queryAll();
        return createMAV("/mt/menu/menu_create").addObject("menus", menus);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 更新建菜单显示
     * @author
     */
    public ModelAndView menuUpdateDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String menuId = this.findStringParameterValue(request, "menuId");
        //根据主键查询对应的菜单信息
        SetFuncMenu menu = setFuncMenuService.queryById(menuId);

        if (menu != null) {
            List<SetFuncMenu> menus = setFuncMenuService.queryAll();
            return createMAV("/mt/menu/menu_update").addObject("menu", menu).addObject("menus", menus);
        }

        return menuDisplay(request, response);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 创建菜单页面提交
     * @author
     */
    public ModelAndView menuCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SetFuncMenu menu = this.bindModel(request, SetFuncMenu.class);
        if (menu == null) {
            return menuCreateDisplay(request, response).addObject(Constants._message, "创建的菜单信息为空");
        }

        //String seq = setFuncMenuService.querySeq();
        String seq = Identities.create32LenUUID();
//		if(StringUtils.isBlank(seq)){
//			return menuCreateDisplay(request, response).addObject(Constants._message, "主键生成错误");
//		}

        //设置主键
        //menu.setMenuId(NumberUtils.createBigDecimal(seq));
        menu.setMenuId(seq);
        BigDecimal lv = menu.getLv();
        if (lv == null) {
            menu.setLv(new BigDecimal(1));
            menu.setPmenuId("-1");
            menu.setMenuCode(seq);
        } else {
            menu.setLv(lv.add(new BigDecimal(1)));
            String pmenuId = menu.getPmenuId();
            if (pmenuId == null) {
                return menuCreateDisplay(request, response).addObject(Constants._message, "父菜单编号为空");
            }
            SetFuncMenu perMenu = setFuncMenuService.queryById(pmenuId);
            if (perMenu == null) {
                return menuCreateDisplay(request, response).addObject(Constants._message, "父菜单不存在");
            }
            String pmc = perMenu.getMenuCode();
            if (StringUtils.isBlank(pmc)) {
                return menuCreateDisplay(request, response).addObject(Constants._message, "父菜单菜单代码为空");
            }
            //生成菜单代码
            String menuCode = pmc.concat("-").concat(seq);
            menu.setMenuCode(menuCode);
        }

        menu.setStatus(Constants.MENU_EFFECT);

        setFuncMenuService.create(menu);

        return menuCreateDisplay(request, response).addObject(Constants._message, "创建成功");
    }

    public ModelAndView menuDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String menuId = this.findStringParameterValue(request, Constants._delData);
        if (StringUtils.isBlank(menuId)) {
            return menuDisplay(request, response).addObject(Constants._message, "主键为空");
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("menuCode", menuId);
        setFuncMenuService.deleteMenu(params);
        return menuDisplay(request, response).addObject(Constants._message, "删除成功");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 编辑菜单页面提交
     * @author
     */
    public ModelAndView menuUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        SetFuncMenu menu = this.bindModel(request, SetFuncMenu.class);
        if (menu.getMenuId() == null) {
            return menuUpdateDisplay(request, response).addObject(Constants._message, "主键为空");
        }
        BigDecimal lv = menu.getLv();
        String menuId = String.valueOf(menu.getMenuId());

        if (lv == null) {
            menu.setLv(new BigDecimal(1));
            menu.setPmenuId("-1");
            menu.setMenuCode(menuId);
        } else {
            menu.setLv(lv.add(new BigDecimal(1)));
            String pmenuId = menu.getPmenuId();
            if (pmenuId == null) {
                return menuUpdateDisplay(request, response).addObject(Constants._message, "父菜单编号为空");
            }
            SetFuncMenu perMenu = setFuncMenuService.queryById(pmenuId);
            if (perMenu == null) {
                return menuUpdateDisplay(request, response).addObject(Constants._message, "父菜单不存在");
            }
            String pmc = perMenu.getMenuCode();
            if (StringUtils.isBlank(pmc)) {
                return menuUpdateDisplay(request, response).addObject(Constants._message, "父菜单菜单代码为空");
            }
            //生成菜单代码
            String menuCode = pmc.concat("-").concat(menuId);
            menu.setMenuCode(menuCode);
        }

        setFuncMenuService.update(menu);

        return menuDisplay(request, response).addObject(Constants._message, "编辑成功");
    }

    public void setSetFuncMenuService(SetFuncMenuService setFuncMenuService) {
        this.setFuncMenuService = setFuncMenuService;
    }

}
