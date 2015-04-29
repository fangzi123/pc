package com.micro.rent.biz.shiro.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.biz.shiro.service.ShiroDbService;
import com.micro.rent.dbaccess.dao.ShiroDbDao;
import com.micro.rent.dbaccess.entity.SFilter;
import com.micro.rent.dbaccess.entity.SetResource;
import com.micro.rent.dbaccess.entity.SetUser;

/**
 * @author George
 */
@Service("shiroDbService")
public class ShiroDbServiceImpl implements ShiroDbService {

    private transient Logger log = LoggerFactory
            .getLogger(ShiroDbServiceImpl.class);

    @Autowired
    private ShiroDbDao shiroDbDao;

    @Override
    public SetUser findByUserName(String userName) {
        log.info("userName[{}]", new Object[] { userName });
        return shiroDbDao.findByUserName(userName);
    }

    @Override
    public List<SetResource> findResourceRolesByUserName(String userName) {
        return shiroDbDao.findResourceRolesByUserName(userName);
    }

    @Override
    public List<SFilter> findAllFilters() {
        // return shiroDbDao.findAllFilters();
        //数据库后台 关于权限的表已删，不进行拦截，以后需切换回去
        return null;
    }
}
