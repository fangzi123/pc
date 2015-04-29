package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.RroleResourceService;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.dao.RroleResourceDao;
import com.micro.rent.dbaccess.dao.SetResourceRoleDao;
import com.micro.rent.dbaccess.dao.SetRroleResourceDao;
import com.micro.rent.dbaccess.entity.RroleResource;
import com.micro.rent.dbaccess.entity.SetResourceRole;
import com.micro.rent.dbaccess.entity.SetRroleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("rroleResourceService")
public class RroleResourceServiceImpl implements RroleResourceService {

    @Autowired
    private RroleResourceDao roleResourceDao;
    @Autowired
    private SetResourceRoleDao setResourceRoleDao;
    @Autowired
    private SetRroleResourceDao setRroleResourceDao;

    @Override
    public void queryPaged(Page<RroleResource> page) {

        // 分页结果集
        List<RroleResource> list = roleResourceDao.queryPaged(page);

        page.setResults(list);

    }

    @Override
    public void create(SetResourceRole role, List<SetRroleResource> list) {
        setResourceRoleDao.insert(role);
        for (SetRroleResource roleResource : list) {
            setRroleResourceDao.insert(roleResource);
        }
    }

    @Override
    public void update(SetResourceRole role, List<SetRroleResource> list) {
        setResourceRoleDao.updateByPrimaryKeySelective(role);
        String roleId = list.get(0).getResourceRoleId();
        setRroleResourceDao.deleteByRoleId(roleId);
        for (SetRroleResource roleResource : list) {
            setRroleResourceDao.insert(roleResource);
        }
    }

    @Override
    public void delete(String roleId) {
        setResourceRoleDao.deleteByPrimaryKey(roleId);
        setRroleResourceDao.deleteByRoleId(roleId);
    }

    @Override
    public String queryRoleSeq() {
        return setResourceRoleDao.getSequences();
    }

}
