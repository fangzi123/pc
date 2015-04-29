package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.SetResourceService;
import com.micro.rent.common.comm.Constants;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.dao.SetResourceDao;
import com.micro.rent.dbaccess.entity.SetResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("setResourceService")
public class SetResourceServiceImpl implements SetResourceService {

    @Autowired
    private SetResourceDao resourceDao;

    public void queryPaged(Page<SetResource> page) {

        List<SetResource> list = resourceDao.queryPaged(page);

        page.setResults(list);
    }

    @Override
    public void create(SetResource resource) {
        resource.setSecurityFilter(Constants._securityFilter);
        resourceDao.insert(resource);
    }

    @Override
    public void update(SetResource resource) {
        resourceDao.updateByPrimaryKeySelective(resource);
    }

    @Override
    public String querySeq() {
        return resourceDao.getSequences();
    }

    @Override
    public List<SetResource> queryAll() {
        return resourceDao.queryAll();
    }

    @Override
    public SetResource queryById(String resourceId) {
        return resourceDao.selectByPrimaryKey(resourceId);
    }

    @Override
    public void deleteMenu(String resourceId) {
        resourceDao.deleteByPrimaryKey(resourceId);
    }

    @Override
    public List<SetResource> queryCandidateResource(String resourceRoleId) {
        return resourceDao.queryCandidateResource(resourceRoleId);
    }

    @Override
    public List<SetResource> querySelectedResource(String resourceRoleId) {
        return resourceDao.querySelectedResource(resourceRoleId);
    }

}
