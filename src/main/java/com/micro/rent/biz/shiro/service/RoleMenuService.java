package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.RoleMenu;
import com.micro.rent.dbaccess.entity.SetFroleFmenu;
import com.micro.rent.dbaccess.entity.SetFuncRole;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 功能角色管理Service层接口
 * @date 2013-03-01
 */
public interface RoleMenuService {

    /**
     * @param page 分页对象
     * @return
     * @Description 按条件分页查询结算菜单
     * @author
     */
    void queryPaged(Page<RoleMenu> page);

    /**
     * @param role 角色对象
     * @param list 角色菜单关系集合
     * @return
     * @Description 新增角色以及用户菜单关系
     * @author
     */
    public void create(SetFuncRole role, List<SetFroleFmenu> list);

    /**
     * @return
     * @Description 得到角色主键
     * @author
     */
    public String queryRoleSeq();

    /**
     * @param role 角色对象
     * @param list 角色菜单关系集合
     * @return
     * @Description 编辑角色以及用户菜单关系
     * @author
     */
    public void update(SetFuncRole role, List<SetFroleFmenu> list);

    /**
     * @param roleId 角色主键
     * @return
     * @Description 删除角色以及用户菜单关系
     * @author
     */
    public void delete(String roleId);

}
