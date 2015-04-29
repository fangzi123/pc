package com.micro.rent.dbaccess.dao.personal;

import com.micro.rent.biz.personal.vo.HouseVo;
import com.micro.rent.dbaccess.entity.myrent.HouseInfo;
import com.micro.rent.dbaccess.entity.personal.Favorites;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author dell
 * @version 1.0
 * @Description: 收藏DAO接口类
 * @date 2014-8-28
 */
public interface FavoritesDao {

    List<Favorites> queryFavoritesByHouseIdAndWeixinId(@Param("houseId") String houseId, @Param("weixinId") String weixinId);

    List<HouseInfo> queryFavoritesList(String weixinId);

    void addCollect(Favorites favorites);

    void deleteCollect(@Param("houseId") String houseId, @Param("weixinId") String weixinId);

    List<HouseVo> queryCollectList(String weixinId);
}
