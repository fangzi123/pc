package com.micro.rent.common.web.controller.pagination.impl;

import com.micro.rent.common.web.controller.pagination.Pageable;

/**
 * @author
 * @version 1.0
 * @Description 分页实现-响应
 * @date 2012-11-15
 */
public class PageResponse implements Pageable {

    //结果集
    private Object result;
    //结果集总数
    private Integer count;

    public PageResponse(Object result, Integer count) {
        this.result = result;
        this.count = count;
    }

    @Override
    public Integer getPageStart() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getPageLimit() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Integer getCount() {
        return count;
    }

    @Override
    public <RESULTTYPE> RESULTTYPE getResult(Class<RESULTTYPE> resultType) {
        return resultType.cast(result);
    }
}
