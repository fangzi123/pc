package com.micro.rent.pc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.micro.rent.pc.dao.MatchByMapDao;
import com.micro.rent.pc.entity.vo.HouseVo;
import com.micro.rent.pc.entity.vo.MapQueryVo;
import com.micro.rent.pc.entity.vo.MapResultVo;
import com.micro.rent.pc.entity.vo.Mark;
import com.micro.rent.pc.service.MatchByMapSdervice;
@Service
@Transactional
public class MatchByMapSderviceImpl implements MatchByMapSdervice {
    @Autowired
    private MatchByMapDao matchByMapDao;
    @Override
    public MapResultVo findAllCommunityByZoom(MapQueryVo mqv) throws Exception {
        List<Mark> markList=matchByMapDao.findAllCommunityByZoom(mqv);
        MapResultVo mrv=new MapResultVo();
        mrv.setMarkList(markList);
        mrv.setMarkFlg(mqv.getZoom());
        return mrv;
    }
    @Override
    public List<HouseVo> findHousesByCommunityId(Integer communityId) throws Exception{
        List<HouseVo> list=matchByMapDao.findHousesByCommunityId(communityId);
        return list;
    }
    @Override
    public MapResultVo findAllCommunityByFilter(MapQueryVo mqv)
            throws Exception {
        List<Mark> markList=new ArrayList<Mark>();
        markList=matchByMapDao.findAllCommunityByFilter(mqv);
        MapResultVo mrv=new MapResultVo();
        mrv.setMarkList(markList);
        mrv.setMarkFlg(mqv.getZoom());
        return mrv;
    }
    @Override
    public List<HouseVo> findHousesByHouseIds(String houseIds) throws Exception {
        List<HouseVo> list=matchByMapDao.findHousesByHouseIds(houseIds);
        return list;
    }

}
