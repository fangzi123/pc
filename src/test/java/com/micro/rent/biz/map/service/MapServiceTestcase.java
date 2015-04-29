package com.micro.rent.biz.map.service;

import com.micro.rent.biz.enum_.ETranfficType;
import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.baidu.direction.RequestParam;
import com.micro.rent.biz.myrent.service.MyRentService;
import com.micro.rent.biz.myrent.vo.MatchResultWrap;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;

@WebAppConfiguration
public class MapServiceTestcase extends AbstractSpringContextTestSupport {

    @Resource
    private MapService baiduMapService;
    @Resource
    private MyRentService myRentService;

    @Test
    public void testdoLeastTimeBetweenTwoPoint() {
        RequestParam reqParam = new RequestParam();

        reqParam.setMode(ETranfficType.DRIVING.getCode());
        reqParam.setOrigin("31.135036,121.437546");
        reqParam.setDestination("31.3977,121.2474");
//		reqParam.setRegion("上海市");
        reqParam.setMode("1");
        reqParam.setOrigin_region("北京");
        reqParam.setDestination_region("北京");
        String bb = baiduMapService.doLeastTimeBetweenTwoPoint(reqParam);
        System.out.println(bb + "---------------");
    }

    @Test
    public void projectHousResult() {
        RequestParam reqParam = new RequestParam();


        MyRentQueryVo queryVo = new MyRentQueryVo();
        queryVo.setProjectId("11000001");
        queryVo.setStackIndex(0);
        queryVo.setLongitude(new BigDecimal("31.135036"));
        queryVo.setLatitude(new BigDecimal("121.437546"));
//		queryVo.setLongitude(longitude);
        MatchResultWrap bb = myRentService.findOneProjectByQueryVo(queryVo);
//		System.out.println(bb+"---------------");
    }

    @Test
    public void geocoder() {
        String address = "铂宫";
//		String address="百环家园";
        String city = "北京";
        MapPoint mp = baiduMapService.getPoint(address, city);
        System.out.println(mp.getLng() + " ------------ " + mp.getLat());
    }

}
