package com.micro.rent.biz.myrent.service;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.myrent.vo.MapMatchWrap;
import com.micro.rent.biz.myrent.vo.ProjectWrap;
import com.micro.rent.biz.myrent.vo.TrafficVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.TProject;
import com.micro.rent.dbaccess.entity.myrent.TprojectPic;

import java.util.List;
import java.util.Map;

/**
 * @author zheng
 * @version TODO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-11-6
 */
public interface ProjectService {

    /**
     * 楼盘项目图片
     *
     * @param projectId
     * @return
     */
    List<TprojectPic> findProjectPicByProjectId(String projectId);

    List<TrafficVo> findTrafficByProjectId(String projectId);

    /**
     * 地图标注点楼盘信息
     *
     * @param houseInfo
     * @return
     */
    ProjectWrap findProject(HouseInfo houseInfo);//String projectId

    /**
     * 地图搜索
     *
     * @param mapPoint
     * @return
     */
    MapMatchWrap findAllProjectByProvince(MapPoint mapPoint);//

    /**
     * 地图筛选
     *
     * @param mapPoint
     * @param conditions
     * @return
     */
    MapMatchWrap findProjectBy(MapPoint mapPoint, Map<String, Object> conditions);

    /**
     * 项目入库
     *
     * @author wff
     */
    void batchSavePro(TProject pro) throws Exception;

    /**
     * 查找tproject表里最大的proId
     *
     * @return
     * @throws Exception
     * @author wff
     */
    String findTheMaxProId() throws Exception;
}
