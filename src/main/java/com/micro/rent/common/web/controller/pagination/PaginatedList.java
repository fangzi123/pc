package com.micro.rent.common.web.controller.pagination;

import java.util.List;

public interface PaginatedList {

    List getList();


    int getPageNumber();


    int getObjectsPerPage();


    int getFullListSize();


    String getSortCriterion();


    int getSortDirection();


    String getSearchId();
}
