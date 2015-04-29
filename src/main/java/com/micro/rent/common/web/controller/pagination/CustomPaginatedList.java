package com.micro.rent.common.web.controller.pagination;

import java.util.List;

/**
 * @author
 * @version 1.0
 * @Description Displaytag 数据库级分页扩展
 * @date 2012-12-10
 */
public class CustomPaginatedList implements PaginatedList {

    /**
     * 每页的列表
     */
    private List list;
    /**
     * 当前页码
     */
    private int pageNumber = 1;
    /**
     * 每页记录数 page size
     */
    private int objectsPerPage = 15;
    /**
     * 总记录数
     */
    private int fullListSize = 0;

    private String sortCriterion;

    private int sortDirection;

    private String searchId;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    public void setObjectsPerPage(int objectsPerPage) {
        this.objectsPerPage = objectsPerPage;
    }

    public int getFullListSize() {
        return fullListSize;
    }

    public void setFullListSize(int fullListSize) {
        this.fullListSize = fullListSize;
    }

    public String getSortCriterion() {
        return sortCriterion;
    }

    public void setSortCriterion(String sortCriterion) {
        this.sortCriterion = sortCriterion;
    }


    /**
     * @return the sortDirection
     */
    public int getSortDirection() {
        return sortDirection;
    }

    /**
     * @param sortDirection the sortDirection to set
     */
    public void setSortDirection(int sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

}
