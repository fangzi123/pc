package com.micro.rent.common.web.controller;


import com.micro.rent.common.web.controller.pagination.CustomPaginatedList;
import com.micro.rent.common.web.controller.pagination.Pageable;
import com.micro.rent.common.web.controller.pagination.PaginatedList;
import com.micro.rent.common.web.controller.pagination.impl.PageRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import java.util.List;


/**
 * @author
 * @version 1.0
 * @Description 控制器基类
 * @date 2012-11-15
 */
public abstract class BaseController {

    /**
     * 分页请求起始参数
     */
    protected final String PAGE_PARAM_START = "page";
    /**
     * 每页显示数量
     */
    protected final String PAGE_PARAM_LIMIT = "pageSize";
    protected transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected MessageSourceAccessor msa;

    /**
     * @param start 起始页
     * @param limit 每页数量
     * @return
     * @Description 获取分页信息
     * @author
     */
    protected Pageable findPage(int start, int limit) {
        //限制pageSize <= 150
        if (limit > 150) {
            log.warn("pageSize must be less than 150");
            limit = 150;
        }
        ;
        return new PageRequest(
                start == 0 ? 1 : start,
                limit
        );
    }

    /**
     * @param pageable 分页结果
     * @param start
     * @param limit
     * @return
     * @Description 执行分页处理
     * @author
     */
    protected PaginatedList doPaging(Pageable pageable, int start, int limit) {
        // 分页获取记录
        int pageNumber = start;

        CustomPaginatedList pageList = new CustomPaginatedList();

        // 设置当前页数
        pageList.setPageNumber(pageNumber);
        // 设置当前页列表
        pageList.setList(pageable.getResult(List.class));
        // 设置page size
        pageList.setObjectsPerPage(limit);
        // 设置总页数
        pageList.setFullListSize(pageable.getCount());

        return pageList;
    }

    protected String splitError(String errorMsg) {
        if (StringUtils.isBlank(errorMsg))
            return errorMsg;
        String[] errors = errorMsg.split("[|]");
        if (errors != null && errors.length > 1)
            return errors[1];
        else
            return errorMsg;
    }

}
