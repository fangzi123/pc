package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.dbaccess.entity.myrent.TprojectPic;

import java.util.List;

public interface TprojectPicDao {

    void batchSave(TprojectPic tprojectPic);

    List<TprojectPic> selectProjectPicByProjectId(String projectId);

    /**
     * 查找某小区的一张图片
     *
     * @return
     * @author wff
     */
    String findOneProPicByComName(String communityName);
}
