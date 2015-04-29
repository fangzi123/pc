package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.*;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 角色组管理Service层接口
 * @date 2013-03-01
 */
public interface RoleGroupSerivce {

    /**
     * @param page 分页对象
     * @return
     * @Description 按条件分页查询结算菜单
     * @author
     */
    public void queryPaged(Page<RoleGroup> page);

    /**
     * @param roleGroup 组对象
     * @param frList    功能角色组关系集合
     * @param rrList    资源角色组关系集合
     * @return
     * @Description 新增角色组以及角色组关系
     * @author
     */
    public void create(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList);

    /**
     * @param roleGroup 组对象
     * @param frList    功能角色组关系集合
     * @param rrList    资源角色组关系集合
     * @param rgroupId  角色组主键
     * @return
     * @Description 编辑角色组以及角色组关系
     * @author
     */
    public void update(SetRoleGroup roleGroup, List<SetRgroupFrole> frList, List<SetRgroupRrole> rrList, String rgroupId);

    /**
     * @param rgroupId 角色组主键
     * @return
     * @Description 删除角色组以及角色组关系
     * @author
     */
    public void delete(String rgroupId);

    /**
     * @return
     * @Description 得到角色主键
     * @author
     */
    public String queryGroupSeq();

    /**
     * @return
     * @Description 查询所有功能角色
     * @author
     */
    List<SetFuncRole> queryFrAll();

    /**
     * @return
     * @Description 查询所有资源角色
     * @author
     */
    List<SetResourceRole> queryRrAll();

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色组ID查询候选功能角色
     * @author
     */
    public List<SetFuncRole> queryFCandidateResource(String rgroupId);

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色组ID查询已选功能角色
     * @author
     */
    public List<SetFuncRole> queryFSelectedResource(String rgroupId);

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色组ID查询候选资源角色
     * @author
     */
    public List<SetResourceRole> queryRCandidateResource(String rgroupId);

    /**
     * @param rgroupId
     * @return
     * @Description 通过角色组ID查询已选资源角色
     * @author
     */
    public List<SetResourceRole> queryRSelectedResource(String rgroupId);

}
