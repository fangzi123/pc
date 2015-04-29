package com.micro.rent.common.web.controller.pagination.impl;

import com.micro.rent.common.web.controller.pagination.Pageable;

/**
 * @author
 * @version 1.0
 * @Description 分页实现-请求
 * @date 2012-11-15
 */
public class PageRequest implements Pageable {

    //起始页
    private Integer start;
    //单页总量
    private Integer limit;

    public PageRequest(Integer start, Integer limit) {
        this.start = start;
        this.limit = limit;
    }

    @Override
    public Integer getPageStart() {
        return start;
    }

    @Override
    public Integer getPageLimit() {
        return limit;
    }

    @Override
    public Integer getCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <RESULTTYPE> RESULTTYPE getResult(Class<RESULTTYPE> resultType) {
        throw new UnsupportedOperationException();
    }

}
