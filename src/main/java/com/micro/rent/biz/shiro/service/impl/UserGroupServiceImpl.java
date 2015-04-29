package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.UserGroupService;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.dao.SetRoleGroupDao;
import com.micro.rent.dbaccess.dao.SetUserDao;
import com.micro.rent.dbaccess.dao.SetUserRgroupDao;
import com.micro.rent.dbaccess.dao.UserGroupDao;
import com.micro.rent.dbaccess.entity.SetRoleGroup;
import com.micro.rent.dbaccess.entity.SetUser;
import com.micro.rent.dbaccess.entity.SetUserRgroup;
import com.micro.rent.dbaccess.entity.UserGroup;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("userGroupService")
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupDao userGroupDao;
    @Autowired
    private SetUserDao setUserDao;
    @Autowired
    private SetUserRgroupDao setUserRgroupDao;
    @Autowired
    private SetRoleGroupDao setRoleGroupDao;

    @Override
    public void queryPaged(Page<UserGroup> page) {

        // 分页结果集
        List<UserGroup> list = userGroupDao.queryPaged(page);
        for (UserGroup userGroup : list) {
            if (StringUtils.isBlank(userGroup.getOrgCode())) {
                userGroup.setOrgCode(StringUtils.EMPTY);
            } else {
                userGroup.setOrgCode(userGroup.getOrgCode() + "|" + StringUtils.defaultIfBlank(userGroup.getChineseName(), ""));
            }
        }

        page.setResults(list);
    }

    @Override
    public void create(SetUser user, List<SetUserRgroup> list) {
        setUserDao.insert(user);
        for (SetUserRgroup userRgroup : list) {
            setUserRgroupDao.insert(userRgroup);
        }
    }

    @Override
    public void update(SetUser user, List<SetUserRgroup> list) {
        setUserDao.updateByPrimaryKeySelective(user);
        String userId = list.get(0).getUserId();
        setUserRgroupDao.deleteByUserId(userId);
        for (SetUserRgroup userRgroup : list) {
            setUserRgroupDao.insert(userRgroup);
        }
    }

    @Override
    public void delete(String userId) {
        setUserDao.deleteByPrimaryKey(userId);
        setUserRgroupDao.deleteByUserId(userId);
    }

    @Override
    public String queryUserSeq() {
        return setUserDao.getSequences();
    }

    @Override
    public List<SetRoleGroup> queryAll() {
        return setRoleGroupDao.queryAll();
    }

    public SetUser queryById(String userId) {
        return setUserDao.selectByPrimaryKey(userId);
    }

    @Override
    public List<SetRoleGroup> queryCandidateResource(String userId) {
        return setRoleGroupDao.queryCandidateResource(userId);
    }

    @Override
    public List<SetRoleGroup> querySelectedResource(String userId) {
        return setRoleGroupDao.querySelectedResource(userId);
    }

    @Override
    public Integer queryUserExist(String userName) {
        return setUserDao.queryUserExist(userName);
    }

    @Override
    public SetUser queryUserByUserName(String userName) {
        return setUserDao.queryUserByUserName(userName);
    }

    @Override
    public void updateUserPasswd(String userName, String passwd) {
        HashMap map = new HashMap();
        map.put("userPassword", passwd);
        map.put("userName", userName);
        setUserDao.updateUserPasswd(map);

    }

}
