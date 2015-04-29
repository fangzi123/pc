package com.micro.rent.pc;

import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.pc.util.JedisUtil;

/**
 * @author zheng
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class RedisTestcase extends AbstractSpringContextTestSupport {

    @Test
    public void testRedis() {
        JedisUtil.set("cade", "123");
    }
    
    @Test
    public void test() {
        String a="1,2,4";
        String c=",0,1,2,4";
        String d="1";
        String b=c.replaceAll(",?0", "");
        System.out.println(b);
    }

}
