package com.micro.rent.pc.service;

import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.TenantOrder;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.OrderVo;

public interface TenantService {

    Tenant getTenantByMobile(String mobile);

    Tenant registerTenant(String mobile, String password);

    void resetDynamicPasswordByMobile(String mobile, String verifyCode);

    // order
    TenantOrder addTenantOrder(OrderVo order) throws Exception;

    TenantOrder getTenantOrder(String telephone, Integer houseId);

    // void addFavoriteHouse(Integer tenantId, Integer houseId);
    
    /**
     * 描述: 检索个人预约房源（分页）
     * @param page 分页信息
     * @throws Exception
     */
    public void getOrderedHouseForPage(Page<HouseVo> page) throws Exception;
    
    /**
     * 描述: 删除个人预约房源
     * @param paramMap 房源ID&用户账号
     * @author zbb
     * @throws Exception
     */
    public void deleteOrderedHouse(Map<String, String> paramMap);
    
    /**
     * 描述: 更新个人资料
     * @param tenant 个人资料
     * @throws Exception
     */
    public void updateTenantInfoByMobile(Tenant tenant) throws Exception;

    /**
     * 描述: 注销个人账户
     * @param mobile 账号
     * @throws Exception
     */
    public void deleteTenant(String mobile) throws Exception;
    
    /**
     * 功能: 检索账户信息
     * @param tenant 检索信息
     * @return 账户信息 
     * @author zbb
     */
    Tenant searchTenantInfo(Tenant tenant);
}
