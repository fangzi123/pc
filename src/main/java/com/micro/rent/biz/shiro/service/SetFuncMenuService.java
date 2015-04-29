package com.micro.rent.biz.shiro.service;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.SetFuncMenu;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @Description 菜单Service层接口
 * @date 2013-02-28
 */
public interface SetFuncMenuService {

    /**
     * @param page 分页对象
     * @return
     * @Description 按条件分页查询菜单
     * @author
     */
    void queryPaged(Page<SetFuncMenu> page);

    /**
     * @param menu
     * @Description 创建菜单
     * @author
     */
    void create(SetFuncMenu menu);

    /**
     * @param menu
     * @Description 更新菜单
     * @author
     */
    void update(SetFuncMenu menu);

    /**
     * @Description 查询Sequences
     * @author
     */
    String querySeq();


    /**
     * @Description 查询所有菜单记录
     * @author
     */
    List<SetFuncMenu> queryAll();

    /**
     * @param menuId
     * @Description 根据主键查询菜单记录
     * @author
     */
    SetFuncMenu queryById(String menuId);

    /**
     * @param params
     * @Description 根据主键删除菜单记录
     * @author
     */
    void deleteMenu(Map<String, String> params);


    /**
     * @param roleId
     * @return
     * @Description 通过角色ID查询候选菜单
     * @author
     */
    public List<SetFuncMenu> queryCandidateResource(String roleId);

    /**
     * @param roleId
     * @return
     * @Description 通过角色ID查询已选菜单
     * @author
     */
    public List<SetFuncMenu> querySelectedResource(String roleId);

}
