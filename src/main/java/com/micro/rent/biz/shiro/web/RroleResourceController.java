package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.RroleResourceService;
import com.micro.rent.biz.shiro.service.SetResourceService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.RroleResource;
import com.micro.rent.dbaccess.entity.SetResource;
import com.micro.rent.dbaccess.entity.SetResourceRole;
import com.micro.rent.dbaccess.entity.SetRroleResource;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RroleResourceController extends _BaseController {


    //注入rroleResourceService
    private RroleResourceService rroleResourceService;
    //注入setResourceService
    private SetResourceService setResourceService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示角色管理页面
     * @author
     */
    public ModelAndView roleResourceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String _search_roleName = "_search_roleName";

        String roleName = this.findStringParameterValue(request, _search_roleName);

        String _search_key = null, _search_value = null;
        if (roleName != null) {
            _search_key = _search_roleName;
            _search_value = roleName;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("resourceRoleName", roleName);

        // 查询分页记录
        Page<RroleResource> page = this.findPageFinal(request);
        page.setParams(params);

        rroleResourceService.queryPaged(page);

        return createMAV("/mt/role/role_resource_read")
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
    public ModelAndView roleResourcePersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subBtn = this.findStringParameterValue(request, "subBtn");
        if (subBtn.equals(Constants._add)) {
            return roleResourceAddDisPlay(request, response);
        } else if (subBtn.equals(Constants._update)) {
            return roleResourceUpdateDisPlay(request, response);
        } else {
            return roleResourceDisplay(request, response);
        }

    }

    private ModelAndView roleResourceAddDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SetResource> resources = setResourceService.queryAll();
        return createMAV("/mt/role/role_resource_create").addObject("resources", resources);
    }

    private ModelAndView roleResourceUpdateDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String resourceRoleId = this.findStringParameterValue(request, "resourceRoleId");
        List<SetResource> candidateResources = setResourceService.queryCandidateResource(resourceRoleId);
        List<SetResource> selectedResources = setResourceService.querySelectedResource(resourceRoleId);
        return createMAV("/mt/role/role_resource_update")
                .addObject("candidate_resources", candidateResources)
                .addObject("selected_resources", selectedResources)
                .addObject("roleName", this.findStringParameterValue(request, "resourceRoleName"))
                .addObject("roleId", resourceRoleId);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色创建
     * @author
     */
    public ModelAndView roleResourceCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = this.findStringParameterValue(request, "ids");
        String roleName = this.findStringParameterValue(request, "roleName");

        if (StringUtils.isBlank(roleName)) {
            return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
        }

        if (StringUtils.isBlank(idStr)) {
            return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
        }

        String[] ids = idStr.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return roleResourceAddDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
        }

        String roleSeq = Identities.create32LenUUID();//rroleResourceService.queryRoleSeq();
        String roleId = roleSeq;

        List<SetRroleResource> list = new ArrayList<SetRroleResource>();

        for (String resourceId : ids) {
            SetRroleResource setRroleResource = new SetRroleResource();
            setRroleResource.setResourceId(resourceId);
            setRroleResource.setResourceRoleId(roleId);
            list.add(setRroleResource);
        }

        SetResourceRole role = new SetResourceRole();
        role.setResourceRoleId(roleId);
        role.setResourceRoleName(roleName);
        role.setStatus(Constants.ROLE_EFFECT);

        rroleResourceService.create(role, list);

        return roleResourceAddDisPlay(request, response).addObject(Constants._message, "创建成功");

    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色编辑
     * @author
     */
    public ModelAndView roleResourceUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String idStr = this.findStringParameterValue(request, "ids");
        String roleName = this.findStringParameterValue(request, "roleName");
        String roleid = this.findStringParameterValue(request, "roleId");

        if (StringUtils.isBlank(roleid)) {
            return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色主键为空");
        }

        if (StringUtils.isBlank(roleName)) {
            return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色姓名为空");
        }

        if (StringUtils.isBlank(idStr)) {
            return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单主键串为空");
        }

        String[] ids = idStr.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return roleResourceUpdateDisPlay(request, response).addObject(Constants._message, "角色菜单为空");
        }

        String roleId = roleid;

        List<SetRroleResource> list = new ArrayList<SetRroleResource>();

        for (String resourceId : ids) {
            SetRroleResource setRroleResource = new SetRroleResource();
            setRroleResource.setResourceId(resourceId);
            setRroleResource.setResourceRoleId(roleId);
            list.add(setRroleResource);
        }

        SetResourceRole role = new SetResourceRole();
        role.setResourceRoleId(roleId);
        role.setResourceRoleName(roleName);
        role.setStatus(Constants.ROLE_EFFECT);

        rroleResourceService.update(role, list);

        return roleResourceDisplay(request, response).addObject(Constants._message, "编辑成功");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色删除
     * @author
     */
    public ModelAndView roleResourceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String _delData = this.findStringParameterValue(request, Constants._delData);
        if (StringUtils.isBlank(_delData)) {
            return roleResourceDisplay(request, response).addObject(Constants._message, "主键为空");
        }

        String roleId = _delData;

        rroleResourceService.delete(roleId);

        return roleResourceDisplay(request, response).addObject(Constants._message, "删除成功");
    }

    public void setRroleResourceService(RroleResourceService rroleResourceService) {
        this.rroleResourceService = rroleResourceService;
    }

    public void setSetResourceService(SetResourceService setResourceService) {
        this.setResourceService = setResourceService;
    }

}
