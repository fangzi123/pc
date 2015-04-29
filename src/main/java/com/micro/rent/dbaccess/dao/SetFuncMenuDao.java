package com.micro.rent.dbaccess.dao;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.SetFuncMenu;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author
 * @version 1.0
 * @Description 菜单数据访问接口
 * @date 2013-02-28
 */

public interface SetFuncMenuDao {

    /**
     * @param menu
     * @Description 插入新记录
     * @author
     */
    void insert(SetFuncMenu menu);

    /**
     * @param menu
     * @Description 更新记录
     * @author
     */
    void updateByPrimaryKeySelective(SetFuncMenu menu);

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
    List<SetFuncMenu> queryPaged(Page<SetFuncMenu> page);


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
    List<SetFuncMenu> queryAll();

    /**
     * @param menuId
     * @return
     * @Description 根据主键查询记录
     * @author
     */
    SetFuncMenu selectByPrimaryKey(String menuId);

    /**
     * @param menuId
     * @return
     * @Description 根据主键删除记录
     * @author
     */
    void deleteByPrimaryKey(BigDecimal menuId);

    /**
     * @param params
     * @return
     * @Description 根据菜单代码删除记录
     * @author
     */
    void deleteByMenuCode(Map<String, String> params);


    /**
     * @param funcRoleId
     * @return
     * @Description 通过ID查询候选记录
     * @author
     */
    List<SetFuncMenu> queryCandidateResource(String funcRoleId);

    /**
     * @param funcRoleId
     * @return
     * @Description 通过ID查询已选记录
     * @author
     */
    List<SetFuncMenu> querySelectedResource(String funcRoleId);

}
