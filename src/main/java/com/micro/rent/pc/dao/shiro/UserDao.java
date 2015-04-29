package com.micro.rent.pc.dao.shiro;

import com.micro.rent.pc.entity.Tenant;

public interface UserDao {
    Tenant findByUserName(String userName);

    Tenant findPrice();
}
