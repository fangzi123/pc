package com.micro.rent.biz.myrent.service.impl;

import com.micro.rent.biz.enum_.ETranfficType;
import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.baidu.direction.RequestParam;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.service.MyRentService;
import com.micro.rent.biz.myrent.vo.MatchResultVo;
import com.micro.rent.biz.myrent.vo.MatchResultWrap;
import com.micro.rent.biz.myrent.vo.MyRentQueryVo;
import com.micro.rent.common.comm.StringUtil;
import com.micro.rent.common.comm.aio.AsyncClientHttpExchangeFutureCallback;
import com.micro.rent.common.support.MathUtils;
import com.micro.rent.dbaccess.dao.myrent.HouseInfoDao;
import com.micro.rent.dbaccess.dao.myrent.MyRentalDao;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class MyRentServiceImpl implements MyRentService {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MyRentalDao myRentalDao;
    @Autowired
    private HouseInfoDao houseInfoDao;
    @Resource
    private MapService baiduMapService;

    @Override
    public MatchResultWrap findAllProjectByQueryVo(MyRentQueryVo queryVo) {
        log.info("findAllProjectByQueryVo begin.");
        // 根据查询条件搜索项目列表-前台校验必输项

        List<MatchResultVo> projectList = myRentalDao
                .selectProjectByQueryVo(queryVo);

        if (projectList == null || projectList.isEmpty()) {
            return new MatchResultWrap();
        }

        // 工作地点
        String workPlace = queryVo.getWorkPlace();

        // 获取工作地点的经纬度
        MapPoint currPoint = baiduMapService.getPoint(workPlace,
                queryVo.getCityName());
        double wpLon = currPoint.getLng().doubleValue();
        double wpLat = currPoint.getLat().doubleValue();
        log.info("lon:[{}],lat[{}]", new Object[]{wpLon, wpLat});
        // 搜索五公里以内的房源信息
        List<MatchResultVo> selectedProjectList = extractMatchResult(
                projectList, wpLon, wpLat);

        String staticUrl = StringUtils.EMPTY;
        if (selectedProjectList == null || selectedProjectList.isEmpty()) {
            return new MatchResultWrap();
        } else {
            // 构造百度url
            List<MapPoint> lstMarks = transformPoint(selectedProjectList);
            staticUrl = baiduMapService.crtStaticMapUrl(lstMarks, currPoint);

            // 交通路线查询
            for (MatchResultVo item : selectedProjectList) {
                BigDecimal lat = item.getLatitude();
                BigDecimal lon = item.getLongitude();
                RequestParam reqParam = new RequestParam();
                reqParam.setDestination(lat.toString().concat(",")
                        .concat(lon.toString()));
                reqParam.setOrigin(String.valueOf(wpLat).concat(",")
                        .concat(String.valueOf(wpLon)));
                reqParam.setMode(queryVo.getTrafficType());

                switch (ETranfficType.getSelfByCode(queryVo.getTrafficType())) {
                    case DRIVING:
                        reqParam.setOrigin_region(queryVo.getCityName());
                        reqParam.setDestination_region(queryVo.getCityName());
                        break;
                    case TRANSIT:
                    case WALKING:
                        reqParam.setRegion(queryVo.getCityName());
                        break;
                }

                String duration = baiduMapService
                        .doLeastTimeBetweenTwoPoint(reqParam);
                if (!StringUtils.isBlank(duration)) {
                    log.debug(String.valueOf(Math.ceil(Double.valueOf(duration) / 60)));
                    item.setDuration(String.valueOf(Math.ceil(Double
                            .valueOf(duration) / 60)));
                }
            }

            Collections.sort(selectedProjectList, new MatchResultVoCompator());
        }

        log.info("findAllProjectByQueryVo end.");
        return new MatchResultWrap(selectedProjectList, currPoint, staticUrl);

    }

    public MatchResultWrap findCoverProject(MapPoint currPoint, String distance) {
        double lng = currPoint.getLng().doubleValue();
        double lat = currPoint.getLat().doubleValue();
        double dis = 5000;
        if (distance != null) {
            try {
                dis = Double.parseDouble(distance);
            } catch (NumberFormatException e) {

            }
        }
        Map<String, Object> map = MathUtils.square(lng, lat, dis);
        List<MatchResultVo> projectList = myRentalDao.selectCoverProject(map);
        List<MatchResultVo> selectedProjectList = extractMatchResult(projectList);

        if (selectedProjectList == null || selectedProjectList.isEmpty()) {
            return new MatchResultWrap();
        }
        String staticUrl = StringUtils.EMPTY;
        if (selectedProjectList == null || selectedProjectList.isEmpty()) {
            return new MatchResultWrap();
        } else {
            return new MatchResultWrap(selectedProjectList, currPoint,
                    staticUrl);
        }

    }

    public MatchResultWrap findAllProject(MapPoint currPoint) {

        List<MatchResultVo> projectList = myRentalDao.selectAllProject();
        if (projectList == null || projectList.isEmpty()) {
            return new MatchResultWrap();
        }
        double wpLon = currPoint.getLng().doubleValue();
        double wpLat = currPoint.getLat().doubleValue();

        // 搜索五公里以内的房源信息
        List<MatchResultVo> selectedProjectList = extractMatchResult(
                projectList, wpLon, wpLat);

        String staticUrl = StringUtils.EMPTY;
        if (selectedProjectList == null || selectedProjectList.isEmpty()) {
            return new MatchResultWrap();
        } else {
            return new MatchResultWrap(selectedProjectList, currPoint,
                    staticUrl);
        }
    }

    private List<MatchResultVo> extractMatchResult(
            List<MatchResultVo> candicateResult, double wpLon, double wpLat) {
        List<MatchResultVo> selectedProjectList = new ArrayList<MatchResultVo>();
        //
        String firstProjectId = candicateResult.get(0).getProjectId();
        for (int i = 0; i < candicateResult.size(); i++) {
            MatchResultVo result = candicateResult.get(i);
            // 如果在一个楼盘里满足查询条件的房源多于1个，则按面积大优先、楼高优先、价格低优先的原则，推出代表该项目的一条房源记录。
            if (i > 0 && firstProjectId.equals(result.getProjectId())) {
                continue;
            } else {
                firstProjectId = result.getProjectId();
            }
            // 没有填写经纬度的，过滤掉
            if (result.getLongitude() == null || result.getLatitude() == null) {
                continue;
            }

            double lon = result.getLongitude().doubleValue();
            double lat = result.getLatitude().doubleValue();

            if (MathUtils.getDistanceOfMeter(wpLat, wpLon, lat, lon) <= 50000) {
                selectedProjectList.add(result);
            }
        }

        return selectedProjectList;
    }

    private List<MatchResultVo> extractMatchResult(
            List<MatchResultVo> candicateResult) {
        List<MatchResultVo> selectedProjectList = new ArrayList<MatchResultVo>();
        //
        String firstProjectId = candicateResult.get(0).getProjectId();
        for (int i = 0; i < candicateResult.size(); i++) {
            MatchResultVo result = candicateResult.get(i);
            // 如果在一个楼盘里满足查询条件的房源多于1个，则按面积大优先、楼高优先、价格低优先的原则，推出代表该项目的一条房源记录。
            if (i > 0 && firstProjectId.equals(result.getProjectId())) {
                continue;
            } else {
                firstProjectId = result.getProjectId();
            }
            // 没有填写经纬度的，过滤掉
            if (result.getLongitude() == null || result.getLatitude() == null) {
                continue;
            }

            selectedProjectList.add(result);

        }

        return selectedProjectList;
    }

    private List<MapPoint> transformPoint(
            List<MatchResultVo> selectedProjectList) {
        List<MapPoint> lstMark = new ArrayList<MapPoint>();
        for (MatchResultVo result : selectedProjectList) {
            MapPoint point = new MapPoint(result.getLongitude(),
                    result.getLatitude());

            lstMark.add(point);
        }

        return lstMark;
    }

    public HouseInfo findBaseHouseInfoBytHouseId(String tHouseId,
                                                 Boolean showMap) {
        HouseInfo houseInfo = houseInfoDao
                .findBaseHouseInfoBytHouseId(tHouseId);
        if (showMap) {
            MapPoint currPoint = new MapPoint(houseInfo.getLongitude(),
                    houseInfo.getLatitude());
            List<MapPoint> lstMark = new ArrayList<MapPoint>();
            lstMark.add(currPoint);
            houseInfo.setMapImg(baiduMapService.crtStaticMapUrl(lstMark,
                    currPoint));
        }

        return houseInfo;
    }

    @Override
    public MatchResultWrap findAllHouseByQueryVo(MyRentQueryVo queryVo) {
        long start = System.currentTimeMillis();
        List<MatchResultVo> listDis = myRentalDao.findHouseByQueryVo(queryVo);//所有符合条件的房源
        // 获取我的位置的经纬度
        String workPlace = queryVo.getWorkPlace();
        MapPoint currPoint =
                baiduMapService.getPoint(workPlace, queryVo.getCityName());
        double wpLon = currPoint.getLng().doubleValue();
        double wpLat = currPoint.getLat().doubleValue();
        log.info("lon:[{}],lat[{}]", new Object[]{wpLon, wpLat});

        Map<String, Double> mrMap = new HashMap<String, Double>();
        //计算房源到我的位置的直线距离
        for (MatchResultVo mr : listDis) {
            double mrDis = StringUtil.distanceSimplify(
                    wpLat, wpLon, mr.getLatitude().doubleValue(),
                    mr.getLongitude().doubleValue());
            mrMap.put(mr.getHouseId(), mrDis);
        }
        // 按距离远近进行排序
        ArrayList<Map.Entry<String, Double>> listEntries = StringUtil.sortMap(mrMap);

        int stackIndex = queryVo.getStackIndex();
        int MAX = 10;
        MatchResultWrap map = new MatchResultWrap();
        if (stackIndex < listEntries.size()) {
            // 本次请求显示房屋的房屋ID列表
            List<String> housIdList = new ArrayList<String>();
            int endIndex = (stackIndex < (listEntries.size() - (listEntries.size() % MAX))) ? stackIndex + MAX : listEntries.size();
            for (int i = stackIndex; i < endIndex; i++) {
                housIdList.add(listEntries.get(i).getKey());
            }

            // 取得房屋具体信息
            List<MatchResultVo> list = myRentalDao.findHousesByHouseIds(housIdList);
            // 计算通勤时间
            try {
                new AsyncClientHttpExchangeFutureCallback(list, queryVo, wpLon, wpLat).httpAsync();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 按通勤时间排序
            sortListByTime(list);

            map.setLstResult(list);
            log.info(System.currentTimeMillis() - start + "----------主線程時間-------ms----");
        }
        return map;
    }

    @Override
    public MatchResultWrap findOneProjectByQueryVo(MyRentQueryVo queryVo) {
        List<MatchResultVo> list = myRentalDao
                .selectOneProjectByQueryVo(queryVo);
        // 我的位置
        // String workPlace = queryVo.getWorkPlace();
        // 获取我的位置的经纬度
        // MapPoint currPoint =
        // baiduMapService.getPoint(workPlace,queryVo.getCityName());

        // 获取坐标
//		MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
//				.get(queryVo.getWeixinId());
//
//		double wpLon = mapPoint.getLng().doubleValue();
//		double wpLat = mapPoint.getLat().doubleValue();
//		log.info("lon:[{}],lat[{}]", new Object[] { wpLon, wpLat });

        // 交通路线查询
        // if(list.size()>0){
        // MatchResultVo item=list.get(0);
        // BigDecimal lat = item.getLatitude();
        // BigDecimal lon = item.getLongitude();
        // RequestParam reqParam = new RequestParam();
        // reqParam.setDestination(lat.toString().concat(",")
        // .concat(lon.toString()));
        // reqParam.setOrigin(String.valueOf(wpLat).concat(",")
        // .concat(String.valueOf(wpLon)));
        // // queryVo.setTrafficType("1");
        // reqParam.setMode(queryVo.getTrafficType());
        // queryVo.setCityName("北京");
        // switch (ETranfficType.getSelfByCode(queryVo.getTrafficType())) {
        // case DRIVING:
        // reqParam.setOrigin_region(queryVo.getCityName());
        // reqParam.setDestination_region(queryVo.getCityName());
        // break;
        // case TRANSIT:
        // case WALKING:
        // reqParam.setRegion(queryVo.getCityName());
        // break;
        // }
        //
        // String duration = baiduMapService
        // .doLeastTimeBetweenTwoPoint(reqParam);
        // if (!StringUtils.isBlank(duration)) {
        // log.debug(String.valueOf(Math.ceil(Double.valueOf(duration) / 60)));
        // String d=String.valueOf(Math.ceil(Double.valueOf(duration) / 60));
        // for(MatchResultVo mrv:list){
        // mrv.setDuration(d);
        // }

        // }
        // }

        MatchResultWrap map = new MatchResultWrap();
        map.setLstResult(list);
        return map;
    }

    public void sortListByTime(List<MatchResultVo> list) {
        Collections.sort(list, new Comparator<MatchResultVo>() {
            @Override
            public int compare(MatchResultVo o1, MatchResultVo o2) {
                return (int) (Double.valueOf(o1.getDuration()) - Double.valueOf(o2.getDuration()));
            }
        });
        for (MatchResultVo mr : list) {
            String duration = StringUtil.secondToHm(Double.valueOf(mr.getDuration())
                    .intValue());
            mr.setDuration(duration);
            log.info("上班时间：---" + mr.getDuration() + "      价格---" + mr.getLongPrice());
        }
    }
}
