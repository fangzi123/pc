package com.micro.rent.pc.service.impl;

import com.micro.rent.pc.dao.shiro.UserDao;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.service.RealmService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RealmServiceImpl implements RealmService {

    @Autowired
    private UserDao userDao;
    // private String OROLE = "user,oRole[%s]";
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Map<String, String> findResourceMap() {
        // TODO
        Map<String, String> rsMap = new HashMap<String, String>();
        return rsMap;
    }

    @Override
    public Tenant findUserInfoByName(String username) {
        log.info("userName:" + username);
        return userDao.findByUserName(username);
    }

}
