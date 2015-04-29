package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.RoleGroupSerivce;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RoleGroupController extends _BaseController {


    //注入roleGroupSerivce
    private RoleGroupSerivce roleGroupSerivce;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示角色组管理页面
     * @author
     */
    public ModelAndView roleGroupDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String _search_rgroupName = "_search_rgroupName";

        log.info("==========打印获取到的搜索参数==========");
        String rgroupName = this.findStringParameterValue(request, _search_rgroupName);
        log.info("search menuName: {}", new Object[]{rgroupName});

        String _search_key = null, _search_value = null;
        if (rgroupName != null) {
            _search_key = _search_rgroupName;
            _search_value = rgroupName;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("groupName", rgroupName);

        Page<RoleGroup> page = this.findPageFinal(request);
        page.setParams(params);

        // 查询分页记录
        roleGroupSerivce.queryPaged(page);


        return createMAV("/mt/rgroup/rgroup_read")
                .addObject("_search_key", _search_key)
                .addObject("_search_value", _search_value)
                .addObject("pageList", page);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示角色组创建/编辑页面
     * @author
     */
    public ModelAndView groupPersistenceDisplay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String subBtn = this.findStringParameterValue(request, "subBtn");
        if (subBtn.equals(Constants._add)) {
            return groupAddDisPlay(request, response);
        } else if (subBtn.equals(Constants._update)) {
            return groupUpdateDisPlay(request, response);
        } else {
            return roleGroupDisplay(request, response);
        }

    }

    private ModelAndView groupAddDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<SetFuncRole> fRoles = roleGroupSerivce.queryFrAll();
        List<SetResourceRole> rRoles = roleGroupSerivce.queryRrAll();
        return createMAV("/mt/rgroup/rgroup_create").addObject("fRoles", fRoles).addObject("rRoles", rRoles);
    }

    private ModelAndView groupUpdateDisPlay(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String rgroupId = this.findStringParameterValue(request, "rgroupId");
        List<SetFuncRole> fcandidateResources = roleGroupSerivce.queryFCandidateResource(rgroupId);
        List<SetFuncRole> fselectedResources = roleGroupSerivce.queryFSelectedResource(rgroupId);

        List<SetResourceRole> rcandidateResources = roleGroupSerivce.queryRCandidateResource(rgroupId);
        List<SetResourceRole> rselectedResources = roleGroupSerivce.queryRSelectedResource(rgroupId);

        return createMAV("/mt/rgroup/rgroup_update")
                .addObject("fcandidate_resources", fcandidateResources)
                .addObject("fselected_resources", fselectedResources)
                .addObject("rcandidate_resources", rcandidateResources)
                .addObject("rselected_resources", rselectedResources)
                .addObject("roleType", this.findStringParameterValue(request, "roleType"))
                .addObject("groupName", this.findStringParameterValue(request, "groupName"))
                .addObject("rgroupId", rgroupId);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色组创建
     * @author
     */
    public ModelAndView groupCreate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ridStr = this.findStringParameterValue(request, "rids");
        String fidStr = this.findStringParameterValue(request, "fids");
        String groupName = this.findStringParameterValue(request, "groupName");

        if (StringUtils.isBlank(groupName)) {
            return groupAddDisPlay(request, response).addObject(Constants._message, "角色组名称为空");
        }

        String groupSeq = Identities.create32LenUUID();//roleGroupSerivce.queryGroupSeq();
        String rgroupId = groupSeq;

        String[] rids = null;
        String[] fids = null;
        if (!StringUtils.isBlank(ridStr)) {
            rids = ridStr.split(",");
        }

        if (!StringUtils.isBlank(fidStr)) {
            fids = fidStr.split(",");
        }

        List<SetRgroupRrole> rrList = new ArrayList<SetRgroupRrole>();
        List<SetRgroupFrole> frList = new ArrayList<SetRgroupFrole>();

        if (!ArrayUtils.isEmpty(rids)) {
            for (String roleId : rids) {
                SetRgroupRrole setRgroupRrole = new SetRgroupRrole();
                setRgroupRrole.setResourceRoleId(roleId);
                setRgroupRrole.setRgroupId(rgroupId);
                rrList.add(setRgroupRrole);
            }
        }
        if (!ArrayUtils.isEmpty(fids)) {
            for (String roleId : fids) {
                SetRgroupFrole setRgroupFrole = new SetRgroupFrole();
                setRgroupFrole.setFuncRoleId(roleId);
                setRgroupFrole.setRgroupId(rgroupId);
                frList.add(setRgroupFrole);
            }
        }

        if (frList.isEmpty() && rrList.isEmpty()) {
            return groupAddDisPlay(request, response).addObject(Constants._message, "资源角色和功能角色不能同时为空");
        }

        SetRoleGroup setRoleGroup = new SetRoleGroup();
        setRoleGroup.setRgroupId(rgroupId);
        setRoleGroup.setRgroupName(groupName);

        roleGroupSerivce.create(setRoleGroup, frList, rrList);

        return groupAddDisPlay(request, response).addObject(Constants._message, "创建成功");

    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色组编辑
     * @author
     */
    public ModelAndView groupUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ridStr = this.findStringParameterValue(request, "rids");
        String fidStr = this.findStringParameterValue(request, "fids");
        String groupName = this.findStringParameterValue(request, "groupName");
        String rgroupId = this.findStringParameterValue(request, "rgroupId");

        if (rgroupId == null) {
            return groupUpdateDisPlay(request, response).addObject(Constants._message, "角色组主键为空");
        }

        if (StringUtils.isBlank(groupName)) {
            return groupUpdateDisPlay(request, response).addObject(Constants._message, "角色组名称为空");
        }

        String[] rids = null;
        String[] fids = null;
        if (!StringUtils.isBlank(ridStr)) {
            rids = ridStr.split(",");
        }

        if (!StringUtils.isBlank(fidStr)) {
            fids = fidStr.split(",");
        }

        List<SetRgroupRrole> rrList = new ArrayList<SetRgroupRrole>();
        List<SetRgroupFrole> frList = new ArrayList<SetRgroupFrole>();

        if (!ArrayUtils.isEmpty(rids)) {
            for (String roleId : rids) {
                SetRgroupRrole setRgroupRrole = new SetRgroupRrole();
                setRgroupRrole.setResourceRoleId(roleId);
                setRgroupRrole.setRgroupId(rgroupId);
                rrList.add(setRgroupRrole);
            }
        }
        if (!ArrayUtils.isEmpty(fids)) {
            for (String roleId : fids) {
                SetRgroupFrole setRgroupFrole = new SetRgroupFrole();
                setRgroupFrole.setFuncRoleId(roleId);
                setRgroupFrole.setRgroupId(rgroupId);
                frList.add(setRgroupFrole);
            }
        }

        if (frList.isEmpty() && rrList.isEmpty()) {
            return groupUpdateDisPlay(request, response).addObject(Constants._message, "资源角色和功能角色不能同时为空");
        }

        SetRoleGroup setRoleGroup = new SetRoleGroup();
        setRoleGroup.setRgroupId(rgroupId);
        setRoleGroup.setRgroupName(groupName);

        roleGroupSerivce.update(setRoleGroup, frList, rrList, rgroupId);

        return roleGroupDisplay(request, response).addObject(Constants._message, "编辑成功");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色删除
     * @author
     */
    public ModelAndView groupDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String _delData = this.findStringParameterValue(request, Constants._delData);
        if (StringUtils.isBlank(_delData)) {
            return roleGroupDisplay(request, response).addObject(Constants._message, "主键为空");
        }

        String rgroupId = _delData;

        roleGroupSerivce.delete(rgroupId);

        return roleGroupDisplay(request, response).addObject(Constants._message, "删除成功");
    }

    public void setRoleGroupSerivce(RoleGroupSerivce roleGroupSerivce) {
        this.roleGroupSerivce = roleGroupSerivce;
    }

}
