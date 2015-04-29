package com.micro.rent.common.comm.nmybatis;

import com.micro.rent.common.web.controller.pagination.PaginatedList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements PaginatedList {
    private int pageStart = 1;//页码，默认是第一页
    private int pageLimit = 20;//每页显示的记录数，默认是20
    private int totalRecord;//总记录数
    private int totalPage;//总页数
    private List<T> results;//对应的当前页记录
    private Map<String, Object> params = new HashMap<String, Object>();//其他的参数我们把它分装成一个Map对象

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
        int totalPage = totalRecord % pageLimit == 0 ? totalRecord / pageLimit : totalRecord / pageLimit + 1;
        this.setTotalPage(totalPage);
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(pageStart).append(", pageSize=")
                .append(pageLimit).append(", results=").append(results)
                .append(", totalPage=").append(totalPage)
                .append(", totalRecord=").append(totalRecord).append("]");
        return builder.toString();
    }

    @Override
    public List getList() {

        return getResults();
    }

    @Override
    public int getPageNumber() {

        return getPageStart();
    }

    @Override
    public int getObjectsPerPage() {
        return getPageLimit();
    }

    @Override
    public int getFullListSize() {

        return getTotalRecord();
    }

    @Override
    public String getSortCriterion() {

        return null;
    }

    @Override
    public int getSortDirection() {

        return 0;
    }

    @Override
    public String getSearchId() {

        return null;
    }
}
