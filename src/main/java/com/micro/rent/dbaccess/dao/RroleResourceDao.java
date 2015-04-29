package com.micro.rent.dbaccess.dao;

import com.micro.rent.common.comm.nmybatis.Page;
import com.micro.rent.dbaccess.entity.RroleResource;

import java.util.List;
import java.util.Map;

/**
 * @author
 * @version 1.0
 * @Description 资源角色管理数据查询接口
 * @date 2013-03-01
 */

public interface RroleResourceDao {

    /**
     * @param page
     * @return
     * @Description 查询分页记录
     * @author
     */
    List<RroleResource> queryPaged(Page<RroleResource> page);


    /**
     * @param params
     * @return
     * @Description 查询记录总数
     * @author
     */
    int queryTotalCount(Map<String, Object> params);

}
