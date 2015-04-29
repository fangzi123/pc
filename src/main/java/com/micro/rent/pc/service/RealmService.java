package com.micro.rent.pc.service;

import com.micro.rent.pc.entity.Tenant;

import java.util.Map;

public interface RealmService {

    /**
     * 查找所有url资源
     *
     * @return
     */
    Map<String, String> findResourceMap();

    /**
     * 根据用户名查找用户信息
     *
     * @param name
     * @return
     */
    Tenant findUserInfoByName(String name);

}
