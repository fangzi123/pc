package com.micro.rent.pc.service;

import java.util.List;
import java.util.Map;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.entity.vo.HouseVo;

public interface FavoriteService {

    Favorite addFavoriteHouse(Integer tenantId, Integer houseId);

    Favorite getFavoriteHouse(Integer tenantId, Integer houseId);

    List<Integer> getFavoriteHouseId(Integer tenantId);

    char changeFavoriteFlag(Integer id, String flag);
    
    /**
     * 描述: 检索个人收藏房源（分页）
     * @param page 分页信息
     * @throws Exception
     */
    public void getFavoriteHouseForPage(Page<HouseVo> page) throws Exception;
    
    /**
     * 描述: 删除个人收藏房源
     * @param paramMap 房源ID&用户账号
     * @author zbb
     * @throws Exception
     */
    public void deleteFavouriteHouse(Map<String, String> paramMap);
}
