package com.micro.rent.pc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import com.micro.rent.dbaccess.dao.base.RegionDao;

import support.AbstractSpringContextTestSupport;

@WebAppConfiguration
public class RegionTestcase extends AbstractSpringContextTestSupport {
    @Autowired
    private RegionDao regionDao;
    @Test
    public void testRegion() {
        regionDao.findReginsByParentId("1");
    }

}
