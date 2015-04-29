package com.micro.rent.pc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.pc.dao.shiro.UserDao;
import com.micro.rent.pc.entity.Brand;
import com.micro.rent.pc.entity.Tenant;
import com.micro.rent.pc.entity.vo.BrandVo;
import com.micro.rent.pc.service.BrandService;
import com.micro.rent.pc.service.RealmService;

/**
 * @author zheng
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class BrandServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private BrandService brandService;

    @Autowired
    private RealmService realmService;

    @Autowired
    private UserDao userDao;

    @Test
    public void queryRecommendBrandList() {
        String region = "110000";
        List<BrandVo> wrap = brandService.getRecommendBrandList(region);
        assertNotNull(wrap);
    }

    @Test
    public void queryBrand() {
        Integer id = 1;
        Brand wrap = brandService.getBrandById(id);
        assertNotNull(wrap);
    }

    @Test
    public void queryBrandByName() {
        String userName = "张飞";
        Tenant guest = realmService.findUserInfoByName(userName);

        assertNotNull(guest);
    }

}
