package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.RoleMenuService;
import com.micro.rent.biz.shiro.service.SetFuncMenuService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.RoleMenu;
import com.micro.rent.dbaccess.entity.SetFroleFmenu;
import com.micro.rent.dbaccess.entity.SetFuncMenu;
import com.micro.rent.dbaccess.entity.SetFuncRole;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RoleMenuController extends _BaseController {


    //注入roleMenuService
    private RoleMenuService roleMenuService;
    //注入setFuncMenuService
    private SetFuncMenuService setFuncMenuService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示角色管理页面
     * @author
     */
    public ModelAndView roleMenuDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String _search_roleName = "_search_roleName";

        String roleName = this.findStringParameterValue(request, _search_roleName);

        String _search_key = null, _search_value = null;
        if (roleName != null) {
            _search_key = _search_roleName;
            _search_value = roleName;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("roleName", roleName);

        Page<RoleMenu> page = this.findPageFinal(request);
        page.setParams(params);

        // 查询分页记录
        roleMenuService.queryPaged(page);

        return createMAV("/mt/role/role_menu_read")
                .addObject("_search_key", _search_key)
                .addObject("_search_value", _search_value)
                .addObject("pageList", page);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示角色创建/编辑页面
     * @author
     */
    public ModelAndView roleMenuPersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subBtn = this.findStringParameterValue(request, "subBtn");
        if (subBtn.equals(Constants._add)) {
            return roleAddDisPlay(request, response);
        } else if (subBtn.equals(Constants._update)) {
            return roleUpdateDisPlay(request, response);
        } else {
            return roleMenuDisplay(request, response);
        }

    }

    private ModelAndView roleAddDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SetFuncMenu> menus = setFuncMenuService.queryAll();
        return createMAV("/mt/role/role_menu_create").addObject("menus", menus);
    }

    private ModelAndView roleUpdateDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = this.findStringParameterValue(request, "roleId");
        List<SetFuncMenu> candidateResources = setFuncMenuService.queryAll();
        List<SetFuncMenu> selectedResources = setFuncMenuService.querySelectedResource(roleId);
        return createMAV("/mt/role/role_menu_update")
                .addObject("candidate_resources", candidateResources)
                .addObject("selected_resources", selectedResources)
                .addObject("roleName", this.findStringParameterValue(request, "roleName"))
                .addObject("roleId", roleId);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色创建
     * @author
     */
    public ModelAndView roleMenuCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mids = this.findStringParameterValue(request, "mids");
        String roleName = this.findStringParameterValue(request, "roleName");

        if (StringUtils.isBlank(roleName)) {
            return roleAddDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
        }

        if (StringUtils.isBlank(mids)) {
            return roleAddDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
        }

        String[] ids = mids.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return roleAddDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
        }

        String roleSeq = Identities.create32LenUUID();//roleMenuService.queryRoleSeq();
        String roleId = roleSeq;

        List<SetFroleFmenu> list = new ArrayList<SetFroleFmenu>();

        for (String menuId : ids) {
            SetFroleFmenu roleMenu = new SetFroleFmenu();
            roleMenu.setFuncMenuId(menuId);
            roleMenu.setFuncRoleId(roleId);
            list.add(roleMenu);
        }

        SetFuncRole role = new SetFuncRole();
        role.setFuncRoleId(roleId);
        role.setFuncRoleName(roleName);
        role.setStatus(Constants.ROLE_EFFECT);

        roleMenuService.create(role, list);

        return roleAddDisPlay(request, response).addObject(Constants._message, "创建成功");

    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色编辑
     * @author
     */
    public ModelAndView roleMenuUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mids = this.findStringParameterValue(request, "mids");
        String roleName = this.findStringParameterValue(request, "roleName");
        String roleId = this.findStringParameterValue(request, "roleId");

        if (roleId == null) {
            return roleUpdateDisPlay(request, response).addObject(Constants._message, "角色主键空");
        }

        if (StringUtils.isBlank(roleName)) {
            return roleUpdateDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
        }

        if (StringUtils.isBlank(mids)) {
            return roleUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
        }

        String[] ids = mids.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return roleUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
        }


        List<SetFroleFmenu> list = new ArrayList<SetFroleFmenu>();

        for (String menuId : ids) {
            SetFroleFmenu roleMenu = new SetFroleFmenu();
            roleMenu.setFuncMenuId(menuId);
            roleMenu.setFuncRoleId(roleId);
            list.add(roleMenu);
        }

        SetFuncRole role = new SetFuncRole();
        role.setFuncRoleId(roleId);
        role.setFuncRoleName(roleName);
        role.setStatus(Constants.ROLE_EFFECT);

        roleMenuService.update(role, list);

        return roleMenuDisplay(request, response).addObject(Constants._message, "编辑成功");
    }

    public ModelAndView roleMenuDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String roleId = this.findStringParameterValue(request, Constants._delData);
        if (roleId == null) {
            return roleMenuDisplay(request, response).addObject(Constants._message, "主键为空");
        }
        roleMenuService.delete(roleId);
        return roleMenuDisplay(request, response).addObject(Constants._message, "删除成功");
    }

    public void setRoleMenuService(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    public void setSetFuncMenuService(SetFuncMenuService setFuncMenuService) {
        this.setFuncMenuService = setFuncMenuService;
    }

}
