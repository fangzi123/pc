<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房</title>
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/myrent/new_match_result.css" rel="stylesheet">
    <script src="<%=contextPath%>/commonJS/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=contextPath%>/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=contextPath%>/mvcjs/myrent/new_match_result.js"></script>
</head>
<body>
<div id="content">
    <c:forEach items="${houseList}" var="item" varStatus="cur">
        <div class="container-fluid">
            <div class="row"
                 onclick="location='<%=contextPath%>/rental/match/detail?houseId=${item.houseId}&weixinId=${weixinId}&duration=${item.duration}'">
                <div class="div-img">
                    <img src="/img/${item.houPicture}"/>
                </div>
                <div class="house-detail font-small">
                    <div class="house-address">
                        <strong name="address">
                                ${item.communityName}
                                <%-- 						${item.buildingNO}号楼  --%>
                                <%-- 						${item.doorplate}单元  --%>
                                <%-- 						${item.roomNumber}房间   --%>
                            <c:if test="${item.layout==0}">开间</c:if>
                            <c:if test="${item.layout>0}">${item.layout}室</c:if>
                                <%-- 						${item.hallQuantity}厅 出租 --%>
                        </strong>
                    </div>
                    <div class="house-info">
                        <div class="row">
                            <div class="col-xs-8 font-gray">
                                随时入住 <fmt:formatNumber value="${item.useArea}" pattern="#" type="number"/>㎡
                            </div>
                            <div class="col-xs-4 price font-big font-yellow">
                                <strong><fmt:formatNumber value="${item.longPrice}" pattern="#" type="number"/></strong>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 font-gray">
                                <c:if test="${not empty item.duration}">上班  ${item.duration}</c:if>
                            </div>
                            <div class="col-xs-4 unit font-yellow">
                                <strong>元/月</strong>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr/>
    </c:forEach>
</div>
<div id="template" class="hide">
    <div class="container-fluid">
        <div class="row">
            <div class="div-img">
                <img name="img" src=""/>
            </div>
            <div class="house-detail font-small">
                <div class="house-address">
                    <strong name="address">
                        <span name="communityName"></span>
                        <!-- 						<span name="buildingNO"></span>号楼  -->
                        <!-- 						<span name="doorplate"></span>单元  -->
                        <!-- 						<span name="roomNumber"></span>房间  -->
                        <span name="layout"></span>
                        <!-- 						<span name="hallQuantity"></span>厅 出租 -->
                    </strong>
                </div>
                <div class="house-info">
                    <div class="row">
                        <div class="col-xs-8 font-gray">
                            随时入住 <span name="useArea"></span>㎡
                        </div>
                        <div class="col-xs-4 price font-big font-yellow">
                            <strong><span name="longPrice"></span></strong>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-8 font-gray">
                            <span name="duration"></span>
                        </div>
                        <div class="col-xs-4 unit font-yellow">
                            <strong class="font_small font-yellow">元/月</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr/>
</div>
<div id="lodaing" style="<c:if test="${fn:length(houseList) <10}">display:none;</c:if>"
     class="woniu-box-center font-big">
    正在加载中
    <div class="point">·</div>
    <div class="point1">·</div>
    <div class="point2">·</div>
</div>
<%-- <div  id="lodaing"  style="width:100%;height:80px;<c:if test="${fn:length(houseList) <10}">display:none;</c:if>" class="woniu-box-center">加载更多...</div> --%>
<input type="hidden" name="stackIndex" value="${queryVo.stackIndex}"/>
<input type="hidden" name="cityName" value="${queryVo.cityName}"/>
<input type="hidden" name="workPlace" value="${queryVo.workPlace}" id="workPlaceId">
<input type="hidden" name="trafficType" value="${queryVo.trafficType}"/>
<input type="hidden" name="longPrice" value="${queryVo.longPrice}"/>
<input type="hidden" name="weixinId" value="${weixinId}"/>
<input type="hidden" name="layout" value="${queryVo.layout}"/>
<input type="hidden" name="communityName" value="${queryVo.communityName}"/>
</body>
</html>
