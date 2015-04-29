package com.micro.rent.pc.dao;

import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.TenantOrder;

public interface TenantDao {

    Tenant queryByKey(Tenant tenant);

    void saveTenant(Tenant tenant);

    void updatePasswordByMobile(Tenant tenant);

    // order
    void saveTenantOrder(TenantOrder order);

    TenantOrder queryByTenantIdAndHouseId(TenantOrder order);
    
    /**
     * 功能: 更新账户信息
     * @order: 账户信息
     * @author zbb
     * @date 2015-3-4
     */
    void updateTenantInfoByMobile(Tenant tenant);
    
    /**
     * 功能: 删除账户
     * @mobile: 个人账号
     * @author zbb
     * @date 2015-3-4
     */
    void deleteTenant(String mobile);
    
    /**
     * 功能: 检索账户信息
     * @param tenant 检索信息
     * @return 账户信息 
     * @author zbb
     * @date 2015-3-4
     */
    Tenant searchTenantInfo(Tenant tenant);
}
