package com.micro.rent.pc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.FavoriteDao;
import com.micro.rent.pc.dao.HouseDao;
import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.service.FavoriteService;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    FavoriteDao favoriteDao;

    @Autowired
    HouseDao houseDao;

    @Override
    public Favorite addFavoriteHouse(Integer tenantId, Integer houseId) {
       // Favorite favorite = getFavoriteHouse(tenantId, houseId);
            Favorite  favorite = new Favorite();
            favorite.setTenantId(tenantId);
            favorite.setHouseId(houseId);
            favorite.setFlag("1");// 公寓房间不属于某个项目
            favoriteDao.saveFavorite(favorite);
        return favorite;
    }

    @Override
    public List<Integer> getFavoriteHouseId(Integer tenantId) {
        return favoriteDao.queryFavoriteHouseIdByTenantId(tenantId);
    }

    @Override
    public char changeFavoriteFlag(Integer id, String flag) {
        Favorite favorite = new Favorite();
        favorite.setId(id);
        char f = flag.charAt(0);
        if (f == '0') {
            f = '1';
        } else {
            f = '0';
        }
        favorite.setFlag(f + "");
        favoriteDao.updateFavoriteFlagById(favorite);
        return f;
    }

    @Override
    public Favorite getFavoriteHouse(Integer tenantId, Integer houseId) {
        // House house = houseDao.queryById(houseId);
        // if (house == null)
        // return null;

        Favorite favorite = new Favorite();
        favorite.setTenantId(tenantId);
        favorite.setHouseId(houseId);
        return favoriteDao.qurtyFavoriteByTenantAndHouseId(favorite);
    }
    
    /**
     * 描述: 检索个人收藏房源（分页）
     * @author zbb
     * @param page 分页信息
     * @throws Exception
     */
    @Override
    public void getFavoriteHouseForPage(Page<HouseVo> page)
            throws Exception {
        List<HouseVo> houseList = favoriteDao.selectFavouriteHouseInfoForPage(page);
        page.setResults(houseList);
    }
    
    /**
     * 描述: 删除个人收藏房源
     * @author zbb
     * @param paramMap 房源ID&用户账号
     * @throws Exception
     */
    @Override
    public void deleteFavouriteHouse(Map<String, String> paramMap) {
        favoriteDao.deleteFavouriteHouse(paramMap);
    }
}
