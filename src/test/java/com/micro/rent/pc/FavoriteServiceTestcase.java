package com.micro.rent.pc;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import support.AbstractSpringContextTestSupport;

import com.micro.rent.pc.entity.Favorite;
import com.micro.rent.pc.service.FavoriteService;

/**
 * @author zheng
 * @version TODO
 * @Description:
 */
@WebAppConfiguration
public class FavoriteServiceTestcase extends AbstractSpringContextTestSupport {

    @Autowired
    private FavoriteService favoriteService;

    @Test
    public void addHouse() {
        Integer tenantId = 1;
        Integer houseId = 1;
        Favorite favorite = favoriteService.addFavoriteHouse(tenantId, houseId);
        assertNotNull(favorite);
    }

    @Test
    public void getFavoriteHouseId() {
        Integer tenantId = 1;

        List<Integer> ids = favoriteService.getFavoriteHouseId(tenantId);
        assertNotNull(ids);
    }

    @Test
    public void updateFlag() {
        Integer tenantId = 1;
        Integer houseId = 4;
        Favorite favorite = favoriteService.getFavoriteHouse(tenantId, houseId);
        if (favorite != null) {
            Integer id = favorite.getId();
            String flag = favorite.getFlag();
            char f = favoriteService.changeFavoriteFlag(id, flag);
            assertNotNull(f);
        }
    }
}
