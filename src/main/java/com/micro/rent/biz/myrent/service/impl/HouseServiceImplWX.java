package com.micro.rent.biz.myrent.service.impl;


import com.micro.rent.biz.enum_.EOrientation;
import com.micro.rent.biz.enum_.EPaymentType;
import com.micro.rent.biz.enum_.ERenovation;
import com.micro.rent.biz.myrent.service.HouseService;
import com.micro.rent.biz.myrent.vo.HouseWrapVo;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.common.support.Identities;
import com.micro.rent.common.support.MathUtils;
import com.micro.rent.dbaccess.dao.myrent.HouseInfoDao;
import com.micro.rent.dbaccess.dao.myrent.ThousePicDao;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author dell
 * @version TODO
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2014-9-9
 */
@Service
public class HouseServiceImplWX implements HouseService {
    String pre = "/img/";
    @Autowired
    private HouseInfoDao houseInfoDao;

//	@Autowired
//	private MapService baiduMapService;
    @Autowired
    private ThousePicDao tHousePicDao;

    // @Autowired
    // private ThouseTrafficDao houseTrafficDao;
    @Transactional
    public HouseInfo findBaseHouseInfoByHouseId(String houseId) {
        return houseInfoDao.findBaseHouseInfoByHouseId(houseId);
    }

    @Override
    public List<ThousePic> findHousePicListByHouseId(String houseId) {
        List<ThousePic> picList = tHousePicDao
                .selectHousePicListByHouseId(houseId);
        for (ThousePic pic : picList) {
            pic.setPicture(pre + pic.getPicture());
        }
        return picList;
    }

    @Override
    public HouseWrapVo findHouseInfoByHouseId(String houseId) {
        HouseWrapVo hWrap = houseInfoDao.findHouseInfoByHouseId(houseId);
        if (hWrap == null) {
            return null;
        }
        hWrap.setOrientation(EOrientation.getNameByCode(hWrap.getOrientation()));
        hWrap.setPaymentType(EPaymentType.getNameByCode(hWrap.getPaymentType()));
        hWrap.setRenovation(ERenovation.getNameByCode(hWrap.getRenovation()));

        List<ThousePic> picList = findHousePicListByHouseId(houseId);
        hWrap.setImgList(picList);

        List<HouseVo> nearHouseList = getNearHouseList(hWrap);
        hWrap.setNearHouseList(nearHouseList);

        for (HouseVo hvo : nearHouseList) {
            picList = findHousePicListByHouseId(hvo.getHouseId());
            if (picList != null && picList.size() > 0) {
                hvo.setPicture(picList.get(0).getPicture());
            } else {
                hvo.setPicture("");
            }
        }

        return hWrap;
    }

    private List<HouseVo> getNearHouseList(HouseWrapVo hWrap) {
        return getNearList(hWrap, 1000);
    }

    private List<HouseVo> getNearList(HouseWrapVo hWrap, double dis) {
        double lng = hWrap.getLongitude().doubleValue();
        double lat = hWrap.getLatitude().doubleValue();
        String houseId = hWrap.getHouseId();
        Map<String, Object> map = MathUtils.square(lng, lat, dis);
        map.put("houseId", houseId);
        List<HouseVo> nearHouseList = houseInfoDao.findNearHouseList(map);
        int size = nearHouseList.size();
        while (size < 2) {
            dis = dis * 1.3;
            map = MathUtils.square(lng, lat, dis);
            map.put("houseId", houseId);
            nearHouseList = houseInfoDao.findNearHouseList(map);
            size = nearHouseList.size();
        }
        List<HouseVo> twoHouse = new ArrayList<HouseVo>();

        Random r = new Random();
        int[] rs;
        if (size != 2) {
            int n = r.nextInt(size);
            int o = r.nextInt(size);
            while (n == o) {
                o = r.nextInt(size);
            }
            rs = new int[]{n, o};
        } else {
            rs = new int[]{0, 1};
        }

        twoHouse.add(nearHouseList.get(rs[0]));
        twoHouse.add(nearHouseList.get(rs[1]));

        return twoHouse;
    }


//	public String getDuration(BigDecimal lat, BigDecimal lon,BigDecimal wpLat, BigDecimal wpLon) {
//	   	RequestParam reqParam = new RequestParam();
//		reqParam.setMode(ETranfficType.DRIVING.getCode());
//		reqParam.setOrigin(lat+","+lon);
//		reqParam.setDestination(wpLat+","+wpLon);
////		reqParam.setRegion("上海市");
//		reqParam.setMode("1");
//		reqParam.setOrigin_region("北京");
//		reqParam.setDestination_region("北京");
//
//		String duration = baiduMapService.doLeastTimeBetweenTwoPoint(reqParam);
////		duration=String.valueOf(Math.ceil(Double
////				.valueOf(duration) / 60));
//		duration = DateUtil.secondsToHourMinute(Long.parseLong(duration));
//		return duration;
//	}

    @Override
    public List<HouseWrapVo> findHousesInOneProject(String projectId) {
        List<HouseWrapVo> hList = houseInfoDao.findHousesInOneProject(projectId);
        List<HouseWrapVo> newList = new ArrayList<HouseWrapVo>();

        String HID = "";
        int count = 0;
        int MAX = 2;
        for (HouseWrapVo hv : hList) {
            String tempId = hv.getHouseId();
            if (HID.equals(tempId)) {
                if (count < MAX) {
                    newList.add(hv);
                    count++;
                }
            } else {
                HID = tempId;
                newList.add(hv);
                count = 1;
            }

        }

        return newList;
    }

    @Override
    public void batchSaveHouse(HouseInfo houseInfo) throws Exception {
        houseInfo.settHouseId(Identities.create32LenUUID());
        // HouseID
        houseInfo.setHouseId(getNextHouseId(houseInfo.getProjectId()));
        // tv
        if ("1".equals(houseInfo.getTv())) {
            houseInfo.setTv("有");
        }
        // internet
        if ("1".equals(houseInfo.getInternet())) {
            houseInfo.setInternet("有");
        }
        houseInfo.setArea(houseInfo.getUseArea());
        houseInfo.setStatus("1");
        houseInfo.setProvinceId("1100");
        houseInfo.setCityCode("1100");
        houseInfo.setDistrictCode("110101");
        houseInfoDao.batchSave(houseInfo);
    }

    /**
     * @return 推荐房源列表
     * @throws Exception
     * @deprecated: 检索推荐房源
     * @date 2014.12.6
     * @author zbb
     */
    public List<HouseInfo> searchRecommendHouseList() throws Exception {
        // 房源（重复）
        List<HouseVo> houseVos = houseInfoDao.searchRecommendHouseList();
        // 房源（不重复）
        List<HouseInfo> houses = new ArrayList<HouseInfo>();

        String houseId = "";
        for (HouseVo houseVo : houseVos) {
            if (!houseId.equals(houseVo.getHouseId())) {
                houseId = houseVo.getHouseId();
                HouseInfo house = new HouseInfo();
                house.setHouseId(houseId);
                house.setLongPrice(houseVo.getLongPrice());
                house.setLayout(houseVo.getLayout());
                house.setHallQuantity(houseVo.getHallQuantity());
                house.setArea(houseVo.getArea());
                house.setBrandName(houseVo.getBrandName());
                house.setPicture("/img/" + houseVo.getPicture());
                houses.add(house);
            }
        }
        return houses;
    }

    ;

    /**
     * @param lat: 纬度
     * @param lng: 经度
     * @return 附近房源列表
     * @throws Exception
     * @deprecated: 检索指定位置附近两套房源
     * @date 2014.12.6
     * @author zbb
     */
    public List<HouseInfo> searchNearbyHouses(double lat, double lng) throws Exception {
        // 搜索范围
        double distance = 0;

        // 房源（不重复）
        List<HouseInfo> houses = new ArrayList<HouseInfo>();
        // 房源（重复）
        List<HouseVo> nearHouseList = new ArrayList<HouseVo>();
        while (houses.size() < 2) {
            // 如果不重复房源没有两个，接着搜索
            distance += 1000;
            Map<String, Object> map = MathUtils.square(lng, lat, distance);
            nearHouseList = houseInfoDao.searchNearHousesUnitProject(map);

            String houseId = "";
            for (HouseVo houseVo : nearHouseList) {
                if (!houseId.equals(houseVo.getHouseId())) {
                    if (houses.size() > 2) {
                        // 如果已有两套房源不再进行导入
                        break;
                    }
                    houseId = houseVo.getHouseId();
                    HouseInfo house = new HouseInfo();
                    house.setHouseId(houseId);
                    house.setLongPrice(houseVo.getLongPrice());
                    house.setHallQuantity(houseVo.getHallQuantity());
                    house.setLayout(houseVo.getLayout());
                    house.setArea(houseVo.getArea());
                    house.setBrandName(houseVo.getBrandName());
                    house.setPicture("/img/" + houseVo.getPicture());
                    houses.add(house);
                }
            }
        }
        if (houses.size() > 2) {
            List<HouseInfo> housesTemp = new ArrayList<HouseInfo>();
            housesTemp.add(houses.get(0));
            housesTemp.add(houses.get(1));
            houses = housesTemp;
        }

        return houses;
    }

    ;

    /**
     * 取得下一个可用房屋编号
     *
     * @param projectId 项目ID
     * @return 可用房屋编号
     */
    private String getNextHouseId(String projectId) {
        String maxHouseId = houseInfoDao.selectMaxHouseIdByProjectId(projectId);
        return maxHouseId == null ? (projectId + "0001") :
                String.valueOf((NumberUtils.toLong(maxHouseId) + 1));
    }
}
