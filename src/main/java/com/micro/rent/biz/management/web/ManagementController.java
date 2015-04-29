package com.micro.rent.biz.management.web;

import com.micro.rent.biz.shiro.service.UserGroupService;
import com.micro.rent.common.exceptions.BizException;
import com.micro.rent.common.support.ShiroHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.SetUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pwd")
public class ManagementController extends _BaseController {
    @Autowired
    private UserGroupService userGroupService;

    /**
     * 显示密码修改页面
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/displayChangePasswd")
    public ModelAndView displayChangePasswd(HttpServletRequest request,
                                            HttpServletResponse response) throws Exception {

        ModelAndView mv = createMAV("/pwd/displayChangePasswd");

        return mv;

    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/updatePasswd")
    public ModelAndView updatePasswd(HttpServletRequest request,
                                     HttpServletResponse response) throws Exception {

        String oldPasswd = request.getParameter("oldPasswd");
        if (null == oldPasswd) {
            return createMAV("/pwd/displayChangePasswd");
        }
        boolean flag = validOldPasswd(oldPasswd);
        ModelAndView mv = null;
        if (flag) {
            String newPasswd = request.getParameter("newPasswd");
            userGroupService.updateUserPasswd(ShiroHelper.getUsername(), DigestUtils.md5Hex(newPasswd));
            mv = createMAV("/pwd/success");
        } else {
            mv = createMAV("/pwd/displayChangePasswd").addObject("result", "error");
        }

        return mv;

    }

    /**
     * 验证原密码
     *
     * @param request
     * @param model
     * @param session
     * @return
     */

    private boolean validOldPasswd(String passwd) {
        String md5OldPasswd = DigestUtils.md5Hex(passwd);
        String userName = ShiroHelper.getUsername();
        SetUser user = userGroupService.queryUserByUserName(userName);

        if (null == user) {
            log.error("用户账号不存在");
            throw new BizException("用户账号不存在");
        }
        return md5OldPasswd.equals(user.getPassword());
    }

}
