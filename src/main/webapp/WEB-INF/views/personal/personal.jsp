<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>窝牛租房</title>

    <script type="text/javascript"
            src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/personal/personal.css"
          rel="stylesheet">
    <script src="<%=contextPath%>/mvcjs/personal/personal.js"></script>
</head>
<body>

<c:forEach items="${resultList}" var="item">
    <div class="container-fluid">
        <div class="row"
             onclick="location='<%=contextPath%>/rental/match/detail?houseId=${item.houseId}&weixinId=${weixinId}&duration=${item.duration}'">
            <div class="div-img">
                <img src="/img/${item.picture}"/>
            </div>
            <div class="house-detail font-small">
                <div class="house-address">
                    <strong name="address"> ${item.communityName}</strong>
                </div>
                <div class="house-info">
                    <div class="row">
                        <div class="col-xs-8 font-gray">
                            随时入住
                            <fmt:formatNumber value="${item.useArea}" pattern="#" type="number"/>㎡
                        </div>
                        <div class="col-xs-4 price font-big font-yellow">
                            <strong><fmt:formatNumber value="${item.longPrice}" pattern="#" type="number"/></strong>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-8 font-gray">
                                <%-- <c:if test="${not empty item.duration}">上班  ${item.duration}</c:if> --%>
                        </div>
                        <div class="col-xs-4 unit font-yellow">
                            <strong>元/月</strong>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr/>
</c:forEach>

<div class="blank">
</div>

<div id="div_footer" class="btn-group">
    <button type="button" class="btn btn-default btn-big font-big" id="collectBtn"
            onclick="location='<%=contextPath%>/rental/favorites/myList?weixinId=${weixinId}'">我的收藏
    </button>
    <button type="button" class="btn btn-default btn-lg btn-big font-big" id="orderBtn"
            onclick="location='<%=contextPath%>/rental/order/myList?weixinId=${weixinId}'">我的预约
    </button>
</div>

<input type="hidden" name="type" value="${type}" id="type">
</body>
</html>
