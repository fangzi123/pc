package com.micro.rent.biz.personal.service.impl;

import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.biz.myrent.HouseInfoBuilder;
import com.micro.rent.biz.personal.service.FavoritesService;
import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.common.support.Identities;
import com.micro.rent.dbaccess.dao.myrent.ThousePicDao;
import com.micro.rent.dbaccess.dao.personal.FavoritesDao;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.myrent.ThousePic;
import com.micro.rent.dbaccess.entity.personal.Favorites;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author dell
 * @version 1.0
 * @Description:
 * @date 2014-8-28
 */
@Service
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FavoritesDao favoritesDao;

    @Autowired
    private ThousePicDao tHousePicDao;

    @Autowired
    private MapService baiduMapService;

    @Override
    public List<HouseInfo> queryFavoritesList(String weixinId) {
        List<HouseInfo> result = favoritesDao.queryFavoritesList(weixinId);

        if (result == null || result.isEmpty())
            return new ArrayList<HouseInfo>();

        for (HouseInfo houseInfo : result) {
            // reset floor
            HouseInfoBuilder.reSetFloor(houseInfo);
        }
        return result;
    }

    public Favorites queryOneFavoritesByHouseIdAndWeixinId(String houseId,
                                                           String weixinId) {
        Favorites favorites = null;
        List<Favorites> favList = favoritesDao
                .queryFavoritesByHouseIdAndWeixinId(houseId, weixinId);
        if (favList != null && favList.size() > 0) {
            favorites = favList.get(0);
        }
        return favorites;
    }

    @Override
    public void addCollect(Favorites favorites) {
        log.info("add collect begin.");
        String houseId = favorites.getHouseId();
        String weixinId = favorites.getWeixinId();

        if (queryOneFavoritesByHouseIdAndWeixinId(houseId, weixinId) == null) {
            favorites.settCollectionId(Identities.create32LenUUID());
            favoritesDao.addCollect(favorites);
        } else {
            log.warn("微信账号[{}]已收藏了此房源信息-houseId[{}]", new Object[]{weixinId,
                    houseId});
        }

        log.info("add collect end.");
    }

    @Override
    public void deleteCollect(String houseId, String weixinId) {
        favoritesDao.deleteCollect(houseId, weixinId);
    }

    @Override
    public List<HouseVo> queryCollectList(String weixinId) {
//		MapPoint mapPoint = (MapPoint) LocationEventMessageHandler.lrumap
//				.get(weixinId);
//
//		if (mapPoint == null) {
//			return null;
//		}

        List<HouseVo> houseList = favoritesDao.queryCollectList(weixinId);
        // BigDecimal lat=mapPoint.getLat();
        // BigDecimal lng=mapPoint.getLng();

        // List<MatchResultVo> voList = new ArrayList<MatchResultVo>();
        for (HouseVo house : houseList) {
            String picture = findHousePicByHouseId(house.getHouseId());
            house.setPicture(picture);
            //setDuration(mapPoint, house);
        }

        return houseList;
    }

    private String findHousePicByHouseId(String houseId) {
        List<ThousePic> picList = tHousePicDao
                .selectHousePicListByHouseId(houseId);
        int size = picList.size();
        String picture = "";
        if (size > 0) {
            size = new Random().nextInt(size);
            picture = picList.get(size).getPicture();
        }
        return picture;
    }

//	private void setDuration(MapPoint mapPoint, HouseVo hvo) {
//
//		BigDecimal lat = mapPoint.getLat();
//		BigDecimal lng = mapPoint.getLng();
//
//		BigDecimal wpLat = hvo.getLatitude();
//		BigDecimal wpLng = hvo.getLongitude();
//		RequestParam reqParam = new RequestParam();
//
//		reqParam.setMode(ETranfficType.DRIVING.getCode());
//		reqParam.setOrigin(lat + "," + lng);
//		reqParam.setDestination(wpLat + "," + wpLng);
//		// reqParam.setRegion("上海市");
//		reqParam.setMode("1");
//		reqParam.setOrigin_region("北京");
//		reqParam.setDestination_region("北京");
//
//		String duration = baiduMapService.doLeastTimeBetweenTwoPoint(reqParam);
//		// duration=String.valueOf(Math.ceil(Double
//		// .valueOf(duration) / 60));
//		duration = DateUtil.secondsToHourMinute(Long.parseLong(duration));
//		hvo.setDuration(duration);
//
//	}

    @Override
    public boolean hasCollected(String weixinId, String houseId) {
        boolean exist = false;
        Favorites fav = queryOneFavoritesByHouseIdAndWeixinId(houseId, weixinId);
        if (fav != null) {
            exist = true;
        }
        return exist;
    }

}
