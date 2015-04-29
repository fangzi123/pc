package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.biz.myrent.vo.TrafficVo;
import com.micro.rent.dbaccess.entity.myrent.ThouseTraffic;

import java.util.List;

public interface ThouseTrafficDao {

    void batchSave(ThouseTraffic tra);

    List<TrafficVo> selectHouseTrafficByProjectId(String projectId);

}
