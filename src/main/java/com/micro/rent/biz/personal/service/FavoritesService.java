package com.micro.rent.biz.personal.service;

import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.personal.Favorites;

import java.util.List;

/**
 * @author dell
 * @version 1.0
 * @Description:
 * @date 2014-8-28
 */
public interface FavoritesService {

    List<HouseInfo> queryFavoritesList(String weixinId);

    Favorites queryOneFavoritesByHouseIdAndWeixinId(String houseId, String weixinId);

    void addCollect(Favorites favorites);

    void deleteCollect(String houseId, String weixinId);

    /**
     * 个人中心 收藏
     *
     * @param mapPoint
     * @param weixinId
     * @return
     */
    List<HouseVo> queryCollectList(String weixinId);//MapPoint mapPoint,

    boolean hasCollected(String weixinId, String houseId);

}
