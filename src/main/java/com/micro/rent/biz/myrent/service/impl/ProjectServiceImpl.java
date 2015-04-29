package com.micro.rent.biz.myrent.service.impl;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.service.ProjectService;
import com.micro.rent.biz.myrent.vo.MapMatchWrap;
import com.micro.rent.biz.myrent.vo.ProjectMapVo;
import com.micro.rent.biz.myrent.vo.ProjectWrap;
import com.micro.rent.biz.myrent.vo.TrafficVo;
import com.micro.rent.common.support.Identities;
import com.micro.rent.dbaccess.dao.myrent.*;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.TProject;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import com.micro.rent.dbaccess.entity.myrent.TprojectPic;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author zheng
 * @version TODO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-11-6
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ThouseTrafficDao houseTrafficDao;

    @Autowired
    private TprojectPicDao projectPicDao;

    @Autowired
    private HouseInfoDao houseInfoDao;

    @Autowired
    private TprojectDao projectDao;

    @Autowired
    private ThousePicDao thousePicDao;
    @Autowired
    private MapService baiduMapService;

    @Override
    public List<TrafficVo> findTrafficByProjectId(String projectId) {
        return houseTrafficDao.selectHouseTrafficByProjectId(projectId);
    }

    @Override
    public List<TprojectPic> findProjectPicByProjectId(String projectId) {
        return projectPicDao.selectProjectPicByProjectId(projectId);
    }

    @Override
    public ProjectWrap findProject(HouseInfo houseInfo) {//String communityName
        //String projectId=houseInfo.getProjectId();
        String communityName = houseInfo.getCommunityName();
        String proPic = projectPicDao.findOneProPicByComName(communityName);
        String houseId = houseInfoDao.findTheMaxPriceHouseIdByComName(communityName);
        List<ThousePic> houPicList = thousePicDao.selectHousePicListByHouseId(houseId);
//		List<TrafficVo> trafficList = houseTrafficDao
//				.selectHouseTrafficByProjectId(projectId);

        ProjectWrap wrap = houseInfoDao.statHouseByProject(houseInfo);//

        //wrap.format(picList, trafficList,houPicList);
        wrap.setImg(proPic, houPicList);
        //wrap.setProjectId(projectId);
        return wrap;
    }

    @Override
    public MapMatchWrap findAllProjectByProvince(MapPoint mapPoint) {
        List<ProjectMapVo> pvoList = projectDao.selectAllProjectByProvince();
        String staticUrl = StringUtils.EMPTY;
        MapMatchWrap wrap = new MapMatchWrap(pvoList, mapPoint, staticUrl);
        return wrap;
    }

    @Override
    public MapMatchWrap findProjectBy(MapPoint mapPoint,
                                      Map<String, Object> conditions) {
        List<ProjectMapVo> pvoList = projectDao.selectProjectBy(conditions);
        String staticUrl = StringUtils.EMPTY;
        MapMatchWrap wrap = new MapMatchWrap(pvoList, mapPoint, staticUrl);
        return wrap;
    }

    /**
     * 增加公寓项目
     *
     * @param pro 公寓项目信息
     */
    @Override
    public void batchSavePro(TProject pro) throws Exception {
        pro.settProjectId(Identities.create32LenUUID());
        MapPoint mp = baiduMapService.getPoint(pro.getStreet(), "北京");
        // 设置项目ID
        pro.setProjectId(findTheMaxProId());
        // 设置经度
        pro.setLongitude(mp.getLng());
        // 设置纬度
        pro.setLatitude(mp.getLat());
        pro.setProvinceId("1100");
        pro.setCityCode("1100");
        // 设置公寓介绍
        pro.setProjectDesc(pro.getProjectDesc() != null ? pro.getProjectDesc().trim() : null);
        // 设置社区信息
        pro.setCompanyDesc(pro.getCompanyDesc() != null ? pro.getCompanyDesc().trim() : null);
        pro.setCategory("1");
        projectDao.batchSave(pro);
    }

    @Override
    public String findTheMaxProId() throws Exception {
        return projectDao.findTheMaxProId();
    }

}
