package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetRoleGroup;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 角色组数据访问接口
 * @date 2013-03-01
 */

public interface SetRoleGroupDao {

    /**
     * @return
     * @Description 查询所有组
     * @author
     */
    List<SetRoleGroup> queryAll();

    /**
     * @param
     * @Description 查询Sequences
     * @author
     */
    String getSequences();

    /**
     * @param setRoleGroup
     * @Description 插入新记录
     * @author
     */
    void insert(SetRoleGroup setRoleGroup);

    /**
     * @param roleId
     * @return
     * @Description 根据主键删除记录
     * @author
     */
    void deleteByPrimaryKey(String rgroupId);

    /**
     * @param setRoleGroup
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetRoleGroup setRoleGroup);


    /**
     * @param userId
     * @return
     * @Description 通过ID查询候选记录
     * @author
     */
    List<SetRoleGroup> queryCandidateResource(String userId);

    /**
     * @param userId
     * @return
     * @Description 通过ID查询已选记录
     * @author
     */
    List<SetRoleGroup> querySelectedResource(String userId);

}
