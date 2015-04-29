package com.micro.rent.biz.myrent.service;

import com.micro.rent.biz.enum_.ERenovation;
import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.myrent.vo.MapMatchWrap;
import com.micro.rent.biz.myrent.vo.ProjectWrap;
import com.micro.rent.biz.myrent.vo.TrafficVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.TprojectPic;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * @author dell
 * @version TODO
 * @Description:
 * @date 2014-8-28
 */
@WebAppConfiguration
public class ProjectServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private ProjectService projectService;

    @Test
    public void findProjectTraffic() {
        String projectId = "11000001";
        List<TrafficVo> hTraffic = projectService.findTrafficByProjectId(projectId);
        //HouseVoWrap wrap=houseService.findTrafficByProjectId(projectId);
        assertNotNull(hTraffic);
    }

    @Test
    public void findProjectPic() {
        String projectId = "11000001";
        List<TprojectPic> hTraffic = projectService.findProjectPicByProjectId(projectId);
        //HouseVoWrap wrap=houseService.findTrafficByProjectId(projectId);
        assertNotNull(hTraffic);
    }

    @Test
    public void statHouse() {
        String projectId = "11000001";
        HouseInfo hinfo = new HouseInfo();
        hinfo.setProjectId(projectId);
        hinfo.setLongPrice(new BigDecimal("2000"));
        hinfo.setLayout("3");
        ProjectWrap wrap = projectService.findProject(hinfo);
        //HouseVoWrap wrap=houseService.findTrafficByProjectId(projectId);
        assertNotNull(wrap);
    }


    @Test
    public void findAllProject() {
        MapPoint mPoint = new MapPoint();
        MapMatchWrap wrap = projectService.findAllProjectByProvince(mPoint);
        //HouseVoWrap wrap=houseService.findTrafficByProjectId(projectId);
        assertNotNull(wrap);
    }


    @Test
    public void findProjectBy() {
        String projectId = "11000001";
        MapPoint mPoint = new MapPoint();
        Map conditions = new HashMap();
        conditions.put("layout", ERenovation.BLANK.getCode());
        MapMatchWrap wrap = projectService.findProjectBy(mPoint, conditions);
        //HouseVoWrap wrap=houseService.findTrafficByProjectId(projectId);
        assertNotNull(wrap);
    }


}

