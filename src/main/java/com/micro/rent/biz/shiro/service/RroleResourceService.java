package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.RroleResource;
import com.micro.rent.dbaccess.entity.SetResourceRole;
import com.micro.rent.dbaccess.entity.SetRroleResource;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @Description 资源角色管理Service层接口
 * @date 2013-03-01
 */
public interface RroleResourceService {

    /**
     * @param pageable 分页对象
     * @param menu     查询条件
     * @return
     * @Description 按条件分页查询结算资源
     * @author
     */
    void queryPaged(Page<RroleResource> page);

    /**
     * @param role 角色对象
     * @param list 角色资源关系集合
     * @return
     * @Description 新增角色以及用户资源关系
     * @author
     */
    public void create(SetResourceRole role, List<SetRroleResource> list);

    /**
     * @param role 角色对象
     * @param list 角色资源关系集合
     * @return
     * @Description 编辑角色以及用户资源关系
     * @author
     */
    public void update(SetResourceRole role, List<SetRroleResource> list);

    /**
     * @param roleId 角色对象主键
     * @return
     * @Description 删除角色以及用户资源关系
     * @author
     */
    public void delete(String roleId);

    /**
     * @return
     * @Description 得到角色主键
     * @author
     */
    public String queryRoleSeq();

}
