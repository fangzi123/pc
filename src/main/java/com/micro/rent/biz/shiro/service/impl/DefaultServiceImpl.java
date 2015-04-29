package com.micro.rent.biz.shiro.service.impl;

import com.micro.rent.biz.shiro.service.DefaultService;
import com.micro.rent.dbaccess.dao.DefaultDao;
import com.micro.rent.dbaccess.entity.SetFuncMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("defaultService")
public class DefaultServiceImpl implements DefaultService {

    @Autowired
    private DefaultDao defaultDao;

    @Override
    public List<SetFuncMenu> findAllMenu() {
        return defaultDao.findAllMenu();
    }

    @Override
    public List<SetFuncMenu> findMenuByUserName(String username) {
        return defaultDao.findMenuByUserName(username);
    }
}
