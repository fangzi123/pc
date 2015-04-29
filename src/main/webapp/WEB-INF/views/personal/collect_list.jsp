<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>窝牛租房</title>
    <link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet" type="text/css">
    <link href="<%=contextPath%>/resources/css/menu.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
</head>
<body>
<!--头部开始-->
<header id="header">我的收藏</header>
<!--头部结束-->

<!--主题部分开始-->
<section>
    <ul class="address-list2">
        <c:forEach items="${resultList}" var="item">
            <li>
                <a href="<%=contextPath%>/rental/match/detail?tHouseId=${item.tHouseId}&weixinId=${weixinId}"><i></i>${item.communityName}
                    <div class="msg">
                        <em>${item.layout}室${item.hallQuantity}厅${item.toiletQuantity}卫</em>/<em>${item.longPrice}元/月</em>/<em>${item.floor}楼
                        (共${item.totalFloor}楼)${item.buildingNo}号${item.roomNumber}</em></div>
                </a>
            </li>
        </c:forEach>
    </ul>

</section>
<!--主题部分结束-->
<!-- 菜单 -->
<div class="nav4">
    <nav>
        <div id="nav4_ul" class="nav_4">
            <ul class="box">
                <li>
                    <a href="#" class=""><span>个人中心</span></a>
                    <dl>
                        <dd>
                            <a href="<%=contextPath%>/rental/favorites/queryList.html"><span>我的收藏</span></a>
                        </dd>
                        <dd>
                            <a href="#"><span>我的订单</span></a>
                        </dd>
                        <dd>
                            <a href="<%=contextPath%>/rental/person/queryPerson.html"><span>个人信息</span></a>
                        </dd>
                    </dl>
                </li>
                <li>
                    <a href="#" class=""><span></span></a>
                </li>

            </ul>
        </div>
    </nav>
    <div id="nav4_masklayer" class="masklayer_div on"></div>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/biz/nav4.js"></script>
    <script type="text/javascript">
        nav4.bindClick(document.getElementById("nav4_ul").querySelectorAll("li>a"), document.getElementById("nav4_masklayer"));
    </script>
</div>
</body>
</html>
