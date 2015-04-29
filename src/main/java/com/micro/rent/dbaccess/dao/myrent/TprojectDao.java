package com.micro.rent.dbaccess.dao.myrent;

import com.micro.rent.biz.myrent.vo.ProjectMapVo;
import com.micro.rent.dbaccess.entity.myrent.TProject;

import java.util.List;
import java.util.Map;

public interface TprojectDao {
    /**
     * 批量保存
     *
     * @param project
     * @author wff
     */
    void batchSave(TProject project);

    List<ProjectMapVo> selectAllProjectByProvince();

    List<ProjectMapVo> selectProjectBy(Map<String, Object> conditions);

    String findTheMaxProId();
}
