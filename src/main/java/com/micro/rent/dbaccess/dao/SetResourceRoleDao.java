package com.micro.rent.dbaccess.dao;

import com.micro.rent.dbaccess.entity.SetResourceRole;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @Description 资源角色数据访问接口
 * @date 2013-03-01
 */

public interface SetResourceRoleDao {

    /**
     * @param
     * @Description 查询Sequences
     * @author
     */
    String getSequences();

    /**
     * @param role
     * @Description 插入新记录
     * @author
     */
    void insert(SetResourceRole role);

    /**
     * @param roleId
     * @return
     * @Description 根据主键删除记录
     * @author
     */
    void deleteByPrimaryKey(String roleId);

    /**
     * @param role
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetResourceRole role);

    /**
     * @return
     * @Description 查询所有记录
     * @author
     */
    List<SetResourceRole> queryAll();


    /**
     * @param rgroupId
     * @return
     * @Description 通过ID查询候选记录
     * @author
     */
    List<SetResourceRole> queryCandidateResource(String rgroupId);

    /**
     * @param rgroupId
     * @return
     * @Description 通过ID查询已选记录
     * @author
     */
    List<SetResourceRole> querySelectedResource(String rgroupId);

}
