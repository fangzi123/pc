package com.micro.rent.dbaccess.dao;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.RoleGroup;

import java.util.List;
import java.util.Map;


/**
 * @author
 * @version 1.0
 * @Description 角色组查询数据查询接口
 * @date 2013-03-01
 */

public interface RoleGroupDao {

    /**
     * @param params
     * @return
     * @Description 查询分页记录
     * @author
     */
    List<RoleGroup> queryPaged(Page<RoleGroup> page);


    /**
     * @param params
     * @return
     * @Description 查询记录总数
     * @author
     */
    int queryTotalCount(Map<String, Object> params);

}
