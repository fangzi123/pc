package com.micro.rent.pc.dao;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.entity.vo.HouseVo;

public interface FavoriteDao {

    void saveFavorite(Favorite favorite);

    List<Integer> queryFavoriteHouseIdByTenantId(Integer tenantId);

    void updateFavoriteFlagById(Favorite favorite);

    Favorite qurtyFavoriteByTenantAndHouseId(Favorite favorite);
    
    /**
     * 功能: 检索个人收藏的房源
     * @mobile: 个人账号
     * @author zbb
     * @date 2015-3-4
     */
    List<HouseVo> selectFavouriteHouseInfoForPage(Page<HouseVo> page) 
            throws Exception;
    
    /**
     * 功能: 删除收藏房源
     * @paramMap 房源ID&用户账号
     * @author zbb
     * @date 2015-3-4
     */
    void deleteFavouriteHouse(Map<String, String> paramMap);
}
