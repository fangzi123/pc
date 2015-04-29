package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.SetResource;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 资源Service层接口
 * @date 2013-02-28
 */
public interface SetResourceService {

    /**
     * @param page 分页对象
     * @return
     * @Description 按条件分页查询资源
     * @author
     */
    public void queryPaged(Page<SetResource> page);

    /**
     * @param resource
     * @Description 创建资源
     * @author
     */
    void create(SetResource resource);

    /**
     * @param resource
     * @Description 更新资源
     * @author
     */
    void update(SetResource resource);

    /**
     * @Description 查询Sequences
     * @author
     */
    String querySeq();


    /**
     * @Description 查询所有资源记录
     * @author
     */
    List<SetResource> queryAll();

    /**
     * @param resourceId
     * @Description 根据主键查询资源记录
     * @author
     */
    SetResource queryById(String resourceId);

    /**
     * @param menuId
     * @Description 根据主键删除资源记录
     * @author
     */
    void deleteMenu(String resourceId);


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
