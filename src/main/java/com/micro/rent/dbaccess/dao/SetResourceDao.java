package com.micro.rent.dbaccess.dao;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.SetResource;

import java.util.List;
import java.util.Map;


/**
 * @author
 * @version 1.0
 * @Description 资源数据访问接口
 * @date 2013-02-28
 */

public interface SetResourceDao {

    /**
     * @param resource
     * @Description 插入新记录
     * @author
     */
    void insert(SetResource resource);

    /**
     * @param resource
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetResource resource);

    /**
     * @param
     * @Description 查询Sequences
     * @author
     */
    String getSequences();


    /**
     * @param params
     * @return
     * @Description 查询分页记录
     * @author
     */
    //List<SetResource> queryPaged(Map<String, Object> params);

    List<SetResource> queryPaged(Page<SetResource> page);


    /**
     * @param params
     * @return
     * @Description 查询记录总数
     * @author
     */
    int queryTotalCount(Map<String, Object> params);

    /**
     * @return
     * @Description 查询所有记录
     * @author
     */
    List<SetResource> queryAll();

    /**
     * @param resourceId
     * @return
     * @Description 根据主键查询记录
     * @author
     */
    SetResource selectByPrimaryKey(String resourceId);

    /**
     * @param resourceId
     * @return
     * @Description 根据主键删除记录
     * @author
     */
    void deleteByPrimaryKey(String resourceId);

    /**
     * @param resourceRoleId
     * @return
     * @Description 通过资源角色ID查询候选资源
     * @author caobin
     */
    List<SetResource> queryCandidateResource(String resourceRoleId);

    /**
     * @param resourceRoleId
     * @return
     * @Description 通过资源角色ID查询已选资源
     * @author caobin
     */
    List<SetResource> querySelectedResource(String resourceRoleId);

}
