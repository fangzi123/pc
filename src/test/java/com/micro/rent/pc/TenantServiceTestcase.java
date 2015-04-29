package com.micro.rent.pc;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.common.web.service.EmailService;
import com.micro.rent.pc.dao.AddressDao;
import com.micro.rent.pc.dao.AllianceDao;
import com.micro.rent.pc.dao.HouseDao;
import com.micro.rent.pc.entity.Alliance;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.TenantOrder;
import com.micro.rent.pc.entity.vo.AllianceVo;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.OrderVo;
import com.micro.rent.pc.service.AllianceService;
import com.micro.rent.pc.service.TenantService;

/**
 * @author zheng
 * @version TODO
 * @Description:
 */
@WebAppConfiguration
public class TenantServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private HouseService houseService;
    
    @Autowired
    private HouseDao houseDao;
    @Autowired
    private AllianceDao allianceDao;
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private AllianceService allianceService;

    @Test
    public void allianceServiceAdd() throws Exception {
        AllianceVo ae=new AllianceVo();
        ae.setContactPerson("wff");
        ae.setPhone("18910415779");
        ae.setBrandName("挑战者");
        ae.setAddress("呼家楼紫荆豪庭22C");
        allianceService.add(ae);
    }
    @Test
    public void findAddressByAddress() throws Exception {
        String a="北京市东城区西花市大街新景家园小3333区";
        Long b=addressDao.findAddressByAddress(a);
        System.out.println(b);
    }
    @Test
    public void AddAlliance() throws Exception {
        Alliance alliance=new Alliance();
        alliance.setContactPerson("wff");
        alliance.setPhone("18910415779");
        alliance.setBrandName("挑战者");
        allianceDao.add(alliance);
    }
    @Test
    public void searchHousesByhouseIds() throws Exception {
        Page<HouseVo> page = new Page<HouseVo>();
        String houseIds="1,2";
        page.getParams().put("houseIds", houseIds);
        houseDao.searchHousesByhouseIds(page);
        
    }
    @Test
    public void queryTenantByMobile() {
        String mobile = "15652979082";
        Tenant guest = tenantService.getTenantByMobile(mobile);

        assertNotNull(guest);
    }

    @Test
    public void saveTenant() {
        String mobile = "159191804689";
        String name = "zheng";
        Tenant guest = tenantService.registerTenant(mobile, name);

        assertNotNull(guest);
    }

    @Test
    public void saveTenantOrder() throws Exception{
        String orderTel = "15652979082";
        String name = "zheng";
        Integer houseId = 1;
        String orderDate = "20150122";
        //
        Integer tenantId = 2;
        OrderVo order = new OrderVo();
        order.setMobile(orderTel);
        order.setName(name);
        order.setHouseId(houseId);
        order.setOrderDate(orderDate);
        order.setTenantId(tenantId);
        TenantOrder tenantOrder = tenantService.addTenantOrder(order);
        assertNotNull(tenantOrder.getId());
    }

    @Test
    public void sendMail() {
        // String houseId="";
        String clientTel = "15919180468";
        String houseTel = "15652979082";
        String orderTime = "20150123";
        String brandName = "豪庭";
        String houseAddress = "团结湖";
        boolean send = emailService.sendOrderMessage(clientTel, orderTime,
                houseAddress, houseTel, brandName);
        assertNotNull(send);
    }

    @Test
    public void resetPassword() {
        // String houseId="";
        String mobile = "15919180468";
        String verifyCode = "aaa";
        tenantService.resetDynamicPasswordByMobile(mobile, verifyCode);
    }
    
    @Test
    public void getOrderedHouseForPage() throws Exception {
        Page<HouseVo> page = new Page<HouseVo>();
        page.getParams().put("mobile", "18910415771");
        tenantService.getOrderedHouseForPage(page);
    }
    
    @Test
    public void deleteOrderedHouse() throws Exception {
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("id", "23");
        paramMap.put("houseId", "5590");
        tenantService.deleteOrderedHouse(paramMap);
    }
    
    @Test
    public void updateTenantInfoByMobile() throws Exception {
        Tenant tenant = new Tenant();
        tenant.setNickname("nickname");
        tenant.setName("name");
        tenant.setEmail("email");
        tenant.setGender("男");
        tenant.setPassword("password");
        tenant.setBirthday("1989/03/16");
        tenant.setMobile("13522025509");
        tenantService.updateTenantInfoByMobile(tenant);
    }
    
    @Test
    public void deleteTenant() throws Exception {
        tenantService.deleteTenant("13522025509");
    }
}
