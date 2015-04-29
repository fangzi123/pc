package com.micro.rent.common.web.controller.pagination;

/**
 * @author
 * @version 1.0
 * @Description 分页接口
 * @date 2012-11-15
 */
public interface Pageable {

    /**
     * @return
     * @Description 获取起始页索引
     * @author
     */
    Integer getPageStart();

    /**
     * @return
     * @Description 获取单页显示总量
     * @author
     */
    Integer getPageLimit();

    /**
     * @return
     * @Description 获取结果总数
     * @author
     */
    Integer getCount();


    /**
     * @param resultType 结果集类型
     * @return
     * @Description 获取结果集
     * @author
     */
    <RESULTTYPE> RESULTTYPE getResult(Class<RESULTTYPE> resultType);

}
