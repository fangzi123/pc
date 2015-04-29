package com.micro.rent.biz.myrent.service;

import com.micro.rent.biz.enum_.ETranfficType;
import com.micro.rent.biz.map.baidu.direction.RequestParam;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.common.support.DateUtil;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * @author dell
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class HouseServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private HouseService houseService;

    @Resource(name = "baiduMapService")
    private MapService mapService;

    @Test
    public void showHouseInfo() {
        String houseId = "110000900004";
        HouseInfo info = houseService.findBaseHouseInfoByHouseId(houseId);
        assertNotNull(info);
    }

    @Test
    public void pic() {
        String houseId = "110000900004";
        List<ThousePic> info = houseService.findHousePicListByHouseId(houseId);
        assertNotNull(info);
    }

    @Test
    public void detail() {
        String houseId = "110000900004";
        HouseWrapVo info = houseService.findHouseInfoByHouseId(houseId);
        assertNotNull(info);
    }

    @Test
    public void findHousesInOneProject() {
        String proId = "11000001";
        List<HouseWrapVo> info = houseService.findHousesInOneProject(proId);
        System.out.println("done");
        assertNotNull(info);
    }


    @Test
    public void getDuration() {
        BigDecimal lat = new BigDecimal("39.8");
        BigDecimal lon = new BigDecimal("116.5");
        BigDecimal wpLat = new BigDecimal("39.5");
        BigDecimal wpLon = new BigDecimal("116.758");

        RequestParam reqParam = new RequestParam();
        reqParam.setMode(ETranfficType.DRIVING.getCode());
        reqParam.setOrigin(lat + "," + lon);
        reqParam.setDestination(wpLat + "," + wpLon);
//		reqParam.setRegion("上海市");
        reqParam.setMode("1");
        reqParam.setOrigin_region("北京");
        reqParam.setDestination_region("北京");

        String duration = mapService.doLeastTimeBetweenTwoPoint(reqParam);
//		duration=String.valueOf(Math.ceil(Double
//				.valueOf(duration) / 60));
        duration = DateUtil.secondsToHourMinute(Long.parseLong(duration));
        //return duration;
    }

}

