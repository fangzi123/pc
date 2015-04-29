package com.micro.rent.pc.dao;

import java.util.List;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.HousePriceRange;
import com.micro.rent.pc.entity.vo.MatchResultVo;

public interface MatchDao {
    /**
     * @Title: findHousesByAddressPaged
     * @Description: 条件查询
     * @param page
     * @return
     * @throws Exception
     * @author: wff
     * @return: List<MatchResultVo>
     */
    List<MatchResultVo> findHousesByAddressPaged(Page<MatchResultVo> page)
            throws Exception;

    HousePriceRange findHousePriceRangeById(Integer id) throws Exception;

}
