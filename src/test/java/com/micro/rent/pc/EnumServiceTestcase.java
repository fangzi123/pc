package com.micro.rent.pc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.pc.service.MatchService;
import com.micro.rent.pc.service.comm.EnumService;

/**
 * @author wff
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class EnumServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private EnumService enumService;
    @Autowired
    private MatchService matchService;


    @Test
    public void findEnum() {
        List<String> wrap = enumService.findEnum("house_orientation_enum");
        assertNotNull(wrap);
    }



}
