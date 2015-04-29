package com.micro.rent.pc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.pc.dao.MatchDao;
import com.micro.rent.pc.entity.HousePriceRange;
import com.micro.rent.pc.entity.vo.MatchResultVo;
import com.micro.rent.pc.service.MatchService;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;

    @Override
    public void findHousesByAddressPaged(Page<MatchResultVo> page)
            throws Exception {
        List<MatchResultVo> mrvList = matchDao.findHousesByAddressPaged(page);
        page.setResults(mrvList);
    }

    @Override
    public HousePriceRange findHousePriceRangeById(Integer id) throws Exception {
        return matchDao.findHousePriceRangeById(id);
    }

}
