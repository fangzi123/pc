package com.micro.rent.pc.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.entity.HousePriceRange;
import com.micro.rent.pc.entity.vo.MatchResultVo;

public interface MatchService {
    /**
     * @Title: findHousesByAddressPaged
     * @Description: 条件搜索
     * @param page
     * @author: wff
     * @return: void
     */
    void findHousesByAddressPaged(Page<MatchResultVo> page) throws Exception;
    /**
     * @Title: findHousePriceRangeById
     * @Description: 查找价格上下限
     * @param id
     * @return
     * @throws Exception
    	* @author: wff
     * @return: HousePriceRange
     */
    HousePriceRange findHousePriceRangeById(Integer id) throws Exception;
}
