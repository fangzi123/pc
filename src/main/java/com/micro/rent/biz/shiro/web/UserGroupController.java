package com.micro.rent.biz.shiro.web;

import com.micro.rent.biz.shiro.service.UserGroupService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.SetRoleGroup;
import com.micro.rent.dbaccess.entity.SetUser;
import com.micro.rent.dbaccess.entity.SetUserRgroup;
import com.micro.rent.dbaccess.entity.UserGroup;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroupController extends _BaseController {

    // 注入userGroupService
    private UserGroupService userGroupService;

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 显示用户组管理页面
     * @author
     */
    public ModelAndView userGroupDisplay(HttpServletRequest request,
                                         HttpServletResponse response) throws Exception {

        String _search_userName = "_search_userName";

        // log.info("==========打印获取到的搜索参数==========");
        String userName = this.findStringParameterValue(request,
                _search_userName);
        // log.info("search menuName: {}", new Object[] { userName });

        String _search_key = null, _search_value = null;
        if (userName != null) {
            _search_key = _search_userName;
            _search_value = userName;
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userName", userName);

        Page<UserGroup> page = this.findPageFinal(request);
        page.setParams(params);

        userGroupService.queryPaged(page);

        return createMAV("/mt/user/user_read")
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
    public ModelAndView userPersistenceDisplay(HttpServletRequest request,
                                               HttpServletResponse response) throws Exception {
        String subBtn = this.findStringParameterValue(request, "subBtn");

        if (subBtn.equals(Constants._add)) {
            return userAddDisPlay(request, response);
        } else if (subBtn.equals(Constants._update)) {
            return userUpdateDisPlay(request, response);
        } else {
            return userGroupDisplay(request, response);
        }

    }

    private ModelAndView userAddDisPlay(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        List<SetRoleGroup> groups = userGroupService.queryAll();

        return createMAV("/mt/user/user_create").addObject("groups", groups);
    }

    private ModelAndView userUpdateDisPlay(HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        String userId = this
                .findStringParameterValue(request, "userId");
        List<SetRoleGroup> candidateResources = userGroupService
                .queryCandidateResource(userId);
        List<SetRoleGroup> selectedResources = userGroupService
                .querySelectedResource(userId);

        return createMAV("/mt/user/user_update")
                .addObject("candidate_resources", candidateResources)
                .addObject("selected_resources", selectedResources)
                .addObject("userName",
                        this.findStringParameterValue(request, "userName"))
                .addObject("status",
                        this.findStringParameterValue(request, "status"))
                .addObject("password",
                        this.findStringParameterValue(request, "password"))
                .addObject("orgCode",
                        this.findStringParameterValue(request, "orgCode"))
                .addObject("userId", userId);
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色组创建
     * @author
     */
    public ModelAndView userGroupCreate(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String gids = this.findStringParameterValue(request, "gids");
        String userName = this.findStringParameterValue(request, "userName");
        String password = this.findStringParameterValue(request, "password");
        String status = this.findStringParameterValue(request, "status");
        String orgCode = this.findStringParameterValue(request, "orgCode");

        if (!StringUtils.isBlank(orgCode)) {
            log.info("orgCode:" + orgCode);
            String[] strs = StringUtils.splitByWholeSeparator(orgCode, "|");
            orgCode = strs[0];
        }

        if (StringUtils.isBlank(userName)) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "用户名称为空");
        }

        Integer isExist = userGroupService.queryUserExist(userName);// 查询用户名是否存在
        if (isExist > 0) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "用户名已存在");
        }

        if (StringUtils.isBlank(password)) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "密码为空");
        }
        if (StringUtils.isBlank(status)) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "用户状态为空");
        }

        if (StringUtils.isBlank(gids)) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "角色组主键串为空");
        }

        String[] ids = gids.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return userAddDisPlay(request, response).addObject(
                    Constants._message, "角色组菜单为空");
        }

        String userSeq = Identities.create32LenUUID();//userGroupService.queryUserSeq();
        String userId = userSeq;

        List<SetUserRgroup> list = new ArrayList<SetUserRgroup>();

        for (String rgroupId : ids) {
            SetUserRgroup userGroup = new SetUserRgroup();
            userGroup.setRgroupId(rgroupId);
            userGroup.setUserId(userId);
            list.add(userGroup);
        }

        SetUser user = new SetUser();
        user.setUserId(userId);
        user.setUsername(userName);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setStatus(status);
        user.setOrgCode(orgCode);
        userGroupService.create(user, list);

        return userAddDisPlay(request, response).addObject(Constants._message,
                "创建成功");
    }

    /**
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Description 角色组编辑
     * @author
     */
    public ModelAndView userGroupUpdate(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String gids = this.findStringParameterValue(request, "gids");
        String userName = this.findStringParameterValue(request, "userName");
        String password = this
                .findStringParameterValue(request, "userPassword");
        String md5Passwd = DigestUtils.md5Hex(password);
        String status = this.findStringParameterValue(request, "status");
        String userId = this.findStringParameterValue(request, "userId");

        String orgCode = this.findStringParameterValue(request, "orgCode");

        if (!StringUtils.isBlank(orgCode)) {
            log.info("orgCode:" + orgCode);
            String[] strs = StringUtils.splitByWholeSeparator(orgCode, "|");
            orgCode = strs[0];
        }

        if (userId == null) {
            return userUpdateDisPlay(request, response).addObject(
                    Constants._message, "用户主键为空");
        }

        if (StringUtils.isBlank(userName)) {
            return userUpdateDisPlay(request, response).addObject(
                    Constants._message, "用户名称为空");
        }
        // if(StringUtils.isBlank(password)){
        // return userUpdateDisPlay(request,
        // response).addObject(Constants._message, "密码为空");
        // }
        if (StringUtils.isBlank(status)) {
            return userUpdateDisPlay(request, response).addObject(
                    Constants._message, "用户状态为空");
        }

        if (StringUtils.isBlank(gids)) {
            return userUpdateDisPlay(request, response).addObject(
                    Constants._message, "角色组主键串为空");
        }

        String[] ids = gids.split(",");
        if (ArrayUtils.isEmpty(ids)) {
            return userUpdateDisPlay(request, response).addObject(
                    Constants._message, "角色组菜单为空");
        }

        SetUser user = userGroupService.queryById(userId);

        List<SetUserRgroup> list = new ArrayList<SetUserRgroup>();

        for (String rgroupId : ids) {
            SetUserRgroup userGroup = new SetUserRgroup();
            userGroup.setRgroupId(rgroupId);
            userGroup.setUserId(userId);
            list.add(userGroup);
        }

        user.setUsername(userName);
        // if(!user.getPassword().equals(password)) {
        // user.setPassword(DigestUtils.md5Hex(password));
        // }
        user.setStatus(status);
        user.setOrgCode(orgCode);
        if (null != password && !password.equals("")) {
            user.setPassword(md5Passwd);
        }
        userGroupService.update(user, list);

        return userGroupDisplay(request, response).addObject(
                Constants._message, "编辑成功");
    }

    public ModelAndView userGroupDelete(HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String userId = this.findStringParameterValue(request,
                Constants._delData);
        if (userId == null) {
            return userGroupDisplay(request, response).addObject(
                    Constants._message, "主键为空");
        }
        userGroupService.delete(userId);
        return userGroupDisplay(request, response).addObject(
                Constants._message, "删除成功");
    }

    public void setUserGroupService(UserGroupService userGroupService) {
        this.userGroupService = userGroupService;
    }

}
