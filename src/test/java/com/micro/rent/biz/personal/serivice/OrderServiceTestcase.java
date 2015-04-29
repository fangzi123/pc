package com.micro.rent.biz.personal.serivice;

import com.micro.rent.biz.personal.service.OrderService;
import com.micro.rent.biz.personal.vo.HouseVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author dell
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class OrderServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private OrderService orderService;

    @Test
    public void queryOrderList() {
        String weixinId = "o00MJj9TY-UZSyqVfC5cZ5yy882E";
        List<HouseVo> wrap = orderService.queryOrderHouseList(weixinId);
        assertNotNull(wrap);
    }

}
