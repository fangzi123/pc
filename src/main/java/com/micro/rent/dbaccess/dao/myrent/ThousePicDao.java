package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.dbaccess.entity.myrent.ThousePic;

import java.util.List;


public interface ThousePicDao {

    void batchSave(ThousePic thousePic);

    List<ThousePic> selectHousePicListByHouseId(String houseId);

}
