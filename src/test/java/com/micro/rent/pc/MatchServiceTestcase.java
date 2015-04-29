package com.micro.rent.pc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.pc.entity.HousePriceRange;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.service.MatchByMapSdervice;
import com.micro.rent.pc.service.MatchService;

/**
 * @author wff
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class MatchServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private MatchByMapSdervice matchByMapSdervice;
    @Autowired
    private MatchService matchService;


    @Test
    public void findEnum() throws Exception {
        List<HouseVo> wrap = matchByMapSdervice.findHousesByCommunityId(1);
        assertNotNull(wrap);
    }
    @Test
    public void housePriceRange() throws Exception {
        HousePriceRange hpr= matchService.findHousePriceRangeById(1);
    }
    @Test
    public void findHousesByHouseIds() throws Exception {
        String ids="4760,4763";
        List<HouseVo> list = matchByMapSdervice.findHousesByHouseIds(ids);
    }


}
