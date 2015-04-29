<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = pageContext.getServletContext()
            .getContextPath();
%>
<!doctype html>
<html class="js">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房</title>
    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/myrent/house_info.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet" type="text/css">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>
    <script src="<%=contextPath%>/mvcjs/myrent/house_info.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.event.drag-1.5.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.touchSlider.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/js/lunbotu.js"></script>
    <!-- 百度地图API  -->
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=108b50eb45abfdb30d056ec907130e22"></script>
</head>
<body>
<%-- <div class="container-fluid div_title woniu-box-center" id="nav">
    <strong>
        ${houseInfo.communityName}
        ${houseInfo.buildingNo}号楼
        ${houseInfo.renovation}
    </strong>
</div> --%>
<div class="div-house-picture" style="display:-webkit-box;-webkit-box-pack:center;">
    <div>
        <div class="banner">
            <div class="main_visual">
                <div class="flicking_con">
                    <div class="flicking_inner">
                        <div>
                            <c:forEach items="${houseInfo.imgList}" var="item" varStatus="status">
                                <a href="#">${ status.index+1}</a>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="main_image">
                    <ul>
                        <c:forEach items="${houseInfo.imgList}" var="item" varStatus="status">
                        <li><img src="${item.picture }"/>
                            </c:forEach>
                    </ul>
                    <a href="javascript:;" id="btn_prev0"></a>
                    <a href="javascript:;" id="btn_next0"></a>
                </div>
            </div>
        </div>
        <%-- <div class="div-price-picture">
            <div class="div-house-price">
                <span><fmt:formatNumber value="${houseInfo.longPrice}" pattern="#"
                            type="number" /></span><br/>
                <span>元/月	</span>
            </div>
            <img class="price-picture" src="<%=contextPath%>/images/hot.png">
        </div> --%>
        <div class="div-house-price">
				<span><fmt:formatNumber value="${houseInfo.longPrice}" pattern="#"
                                        type="number"/></span><br/>
            <span>元/月	</span>
        </div>
        <img class="price-picture" src="<%=contextPath%>/images/hot.png">
    </div>
</div>

<div id="div_detail" class="container-fluid">
    <div class="row">
        <div class="col-xs-3 font-gray">地　　址</div>
        <div class="col-xs-9">
            ${houseInfo.street} &nbsp;
            <a href="#allmap"><img class="woniu_site" src="<%=contextPath%>/images/map.png"></img></a>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-3 font-gray">入　　住</div>
        <div class="col-xs-9">随时</div>
    </div>
    <div class="row">
        <div class="col-xs-3 font-gray">概　　况</div>
        <div class="col-xs-9">
            <c:if test="${houseInfo.layout>0}">
                ${houseInfo.layout}室
                ${houseInfo.hallQuantity}厅
            </c:if>
            <c:if test="${houseInfo.layout==0}">
                开间
            </c:if>
            ${houseInfo.toiletQuantity}卫
            <fmt:formatNumber value="${houseInfo.useArea}" pattern="#"
                              type="number"/>㎡
            朝向${houseInfo.orientation}
        </div>
    </div>
    <c:if test="${!empty houseInfo.duration}">
        <div class="row">
            <div class="col-xs-3 font-gray">上班时间</div>
            <div class="col-xs-9">${houseInfo.duration}</div>
        </div>
    </c:if>
    <div class="row">
        <div class="col-xs-3 font-gray">支付方式</div>
        <div class="col-xs-9">${houseInfo.paymentType}</div>
    </div>

    <div class="row">
        <div class="col-xs-3 font-gray">最短租约</div>
        <div class="col-xs-9">
            <c:choose>
                <c:when test="${houseInfo.shortestPeriod>0}">
                    ${houseInfo.shortestPeriod}个月
                </c:when>
                <c:otherwise>
                    1个月
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="blank"></div>
<div class="container-fluid house_msg_1">
    <h6>公寓介绍</h6>
    <hr/>
    <p>${houseInfo.projectDesc}</p>
</div>
<div class="container-fluid house_msg_2">
    <h6>社区信息</h6>
    <hr/>
    <p>${houseInfo.companyDesc}</p>
</div>
<div class="container-fluid house_msg_3">
    <h6>地图</h6>
    <hr/>
    <div name="map" id="allmap" style="height:200px"></div>
</div>

<div class="container-fluid fujinfangyuan">
    <h6>附近房源</h6>
    <hr/>
    <div class="spn house-picture">
        <c:forEach items="${houseInfo.nearHouseList}" var="item">
            <div class="col-xs-6 col-sm-6" id="img-int"
                 onclick="location='<%=contextPath%>/rental/match/detail?houseId=${item.houseId}&weixinId=${weixinId}'">
                <div>
                    <div class="div-price">
                        <div>
                            &nbsp;
                            <fmt:formatNumber value="${item.longPrice}" pattern="#"
                                              type="number"/>
                            元/月
                        </div>
                    </div>
                    <img src="${item.picture}" style="height: 105px">

                    <div class="div-house-info">
                        <c:if test="${item.layout>0}">
                            ${item.layout}室${item.hallQuantity}厅｜
                        </c:if>
                        <c:if test="${item.layout==0}">
                            开间｜
                        </c:if>
                        <fmt:formatNumber value="${item.useArea}" pattern="#"
                                          type="number"/>㎡
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

</div>
<div class="blank1">
</div>
<div id="div_footer">
    <div>
        <button type="button" id="collect" class="btn btn-lg btn-yellow">收藏</button>
        <button type="button" id="order" class="btn btn-lg">预约</button>
    </div>
</div>
<input type="hidden" name="cur_lon" value="${houseInfo.longitude}">
<input type="hidden" name="cur_lat" value="${houseInfo.latitude}">
<input type="hidden" name="ctxPath" value="<%=contextPath%>">
<input type="hidden" name="weixinId" value="${weixinId}">
<input type="hidden" name="houseId" value="${houseInfo.houseId}">
<input type="hidden" name="projectId" value="${houseInfo.projectId}">
<input type="hidden" name="has_ordered" value="${hasOrdered}"
       id="has_ordered">
</body>

</html>
