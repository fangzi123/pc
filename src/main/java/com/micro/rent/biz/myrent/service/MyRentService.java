package com.micro.rent.biz.myrent.service;

import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.myrent.vo.MatchResultWrap;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;

public interface MyRentService {

    /**
     * 智能匹配查询
     *
     * @param queryVo
     * @return
     */
    MatchResultWrap findAllProjectByQueryVo(MyRentQueryVo queryVo);

    MatchResultWrap findAllProject(MapPoint currPoint);

    MatchResultWrap findCoverProject(MapPoint currPoint, String distance);

    /**
     * 具体房源信息
     *
     * @param tHouseId
     * @param showMap
     * @return
     */
    HouseInfo findBaseHouseInfoBytHouseId(String tHouseId, Boolean showMap);

    /**
     * 房源搜索    价格检索
     *
     * @param queryVo
     * @return
     * @author wff
     */
    MatchResultWrap findAllHouseByQueryVo(MyRentQueryVo queryVo);

    /**
     * 房源搜索   某一套公寓
     *
     * @param queryVo
     * @return
     * @author wff
     */
    MatchResultWrap findOneProjectByQueryVo(MyRentQueryVo queryVo);
}
