package com.micro.rent.biz.myrent.service;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

/**
 * @author dell
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class RentServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private MyRentService myrentService;

    @Test
    public void findCoverProject() {
        MapPoint currPoint = new MapPoint("116.5", "39.9");
        String distance = "15000";
        distance = null;
        myrentService.findCoverProject(currPoint, distance);

    }

    @Test
    public void findAllProject() {
        MapPoint currPoint = new MapPoint("116.5", "39.9");
        myrentService.findAllProject(currPoint);
    }

    @Test
    public void findOneProjectByQueryVo() {
        MyRentQueryVo queryVo = new MyRentQueryVo();
        queryVo.setCommunityName("点击未来");
        myrentService.findOneProjectByQueryVo(queryVo);
    }


}

