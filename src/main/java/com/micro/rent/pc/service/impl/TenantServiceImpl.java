package com.micro.rent.pc.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.biz.enum_.OrderStatus;
import com.micro.rent.biz.enum_.OrderType;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.TenantDao;
import com.micro.rent.pc.dao.TenantOrderDao;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.TenantOrder;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.OrderVo;
import com.micro.rent.pc.service.TenantService;

@Service
public class TenantServiceImpl implements TenantService {
    @Autowired
    TenantDao tenantDao;

    @Autowired
    TenantOrderDao tenantOrderDao;
    @Override
    public Tenant getTenantByMobile(String mobile) {
        Tenant tenant = new Tenant();
        tenant.setMobile(mobile);
        return tenantDao.queryByKey(tenant);
    }

    @Override
    public Tenant registerTenant(String mobile, String password) {
        Tenant tenant = new Tenant();
        /**
         * flag 第二位为1表示是手机注册，密码为空，需登录修改
         */
        tenant.setFlag("11");
        tenant.setMobile(mobile);
        tenant.setPassword(password);
        tenantDao.saveTenant(tenant);
        return tenant;
    }

    @Override
    public TenantOrder addTenantOrder(OrderVo vo) throws Exception{
        TenantOrder tenantOrder = new TenantOrder();
        BeanUtils.copyProperties(tenantOrder, vo);
        tenantOrder.setStatus(OrderStatus.NORMAL.value());
        tenantOrder.setType(OrderType.PREORDER.value());
        tenantDao.saveTenantOrder(tenantOrder);
        return tenantOrder;
    }

    @Override
    public TenantOrder getTenantOrder(String telephone, Integer houseId) {
        TenantOrder order = new TenantOrder();
        order.setHouseId(houseId);
        order.setMobile(telephone);
        return tenantDao.queryByTenantIdAndHouseId(order);
    }

    @Override
    public void resetDynamicPasswordByMobile(String mobile, String verifyCode) {
        Tenant tenant = new Tenant();
        tenant.setMobile(mobile);
        tenant.setPassword(verifyCode);
        tenantDao.updatePasswordByMobile(tenant);
    }
    
    /**
     * 描述: 检索个人预约房源（分页）
     * @author zbb
     * @param page 分页信息f
     * @throws Exception
     */
    @Override
    public void getOrderedHouseForPage(Page<HouseVo> page)
            throws Exception {
        List<HouseVo> houseList = tenantOrderDao.selectOrderedHouseInfo(page);
        page.setResults(houseList);
    }
    
    /**
     * 描述: 删除个人预约房源
     * @author zbb
     * @param paramMap 房源ID&用户账号
     * @throws Exception
     */
    @Override
    public void deleteOrderedHouse(Map<String, String> paramMap) {
        tenantOrderDao.deleteOrderedHouse(paramMap);
    }

    /**
     * 描述: 更新个人资料
     * @param tenant 个人资料
     * @throws Exception
     */
    public void updateTenantInfoByMobile(Tenant tenant) throws Exception {
        tenantDao.updateTenantInfoByMobile(tenant);
    };
    
    /**
     * 描述: 注销个人账户
     * @param mobile 账号
     * @throws Exception
     */
    public void deleteTenant(String mobile) throws Exception {
        tenantDao.deleteTenant(mobile);
    };
    
    /**
     * 功能: 检索账户信息
     * @param tenant 检索信息
     * @return 账户信息 
     * @author zbb
     * @date 2015-3-4
     */
    public Tenant searchTenantInfo(Tenant tenant) {
        return tenantDao.searchTenantInfo(tenant);
    };
}
