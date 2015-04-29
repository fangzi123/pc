<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = pageContext.getServletContext()
            .getContextPath();
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>窝牛租房</title>

    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/select.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.event.drag-1.5.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.touchSlider.js"></script>

    <script type="text/javascript">
        $(function () {
            $(".top-search").click(function () {
                if ($("#search-popup").css("display") == "none") {
                    $("#search-popup").slideDown();
                } else {
                    $("#search-popup").slideUp();
                }
            })
        })
    </script>
    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            hidden;
            margin: 0;
        }

        .close-img {
            position: absolute;
            right: 15px;
            top: 12px;
            cursor: pointer;
            width: 20px;
            height: 20px;
        }
    </style>

    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=A122a2bb3e8ff58a9490406760e978f9"></script>
    <script type="text/javascript" src="<%=contextPath%>/mvcjs/myrent/match_result.js"></script>
    <link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet" type="text/css">
</head>
<body>
<!--头部开始-->
<header id="header"><span class="top-search"></span>智能匹配
    <div id="close-map" style="display:none;">
        <img src="<%=contextPath%>/images/delete-white.png" id="close-img"/>
    </div>
    <div id="search-popup">
        <span class="icon"></span>

        <div class="plr10">
            <ul class="form-list">
                <li class="list">
                    <div class="left">房型</div>
                    <div class="right">
                        <dl class="select">
                            <span></span>
                            <dt>不限</dt>
                            <dd>
                                <ul>
                                    <li><a href="#">不限</a></li>
                                </ul>
                            </dd>
                        </dl>
                    </div>
                </li>
                <li class="list">
                    <div class="left">装修</div>
                    <div class="right">
                        <dl class="select">
                            <span></span>
                            <dt>简装</dt>
                            <dd>
                                <ul>
                                    <li><a href="#">毛坯</a></li>
                                    <li><a href="#">精装</a></li>
                                </ul>
                            </dd>
                        </dl>
                    </div>
                </li>
            </ul>
            <div class="btnbox"><a href="#" class="btn01">筛选</a></div>
        </div>
    </div>
</header>
<!--头部结束-->
<!--主题部分开始-->
<div id="allmap" style="display:none"></div>

<section>
    <input type="hidden" value='${lstPoint}' id="points"/>

    <!-- img id="mapImg" src="${mapImg}" / -->


    <ul class="address-list1">
        <c:forEach items="${resultList}" var="item" varStatus="cur">

            <div class="banner">
                <div class="main_visual">
                    <div class="flicking_con">
                        <div class="flicking_inner">
                            <a href="#">1</a>
                            <a href="#">2</a>
                            <a href="#">3</a>
                            <a href="#">4</a>
                            <a href="#">5</a>
                        </div>
                    </div>
                    <div class="main_image">
                        <ul>
                            <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                            <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                            <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                            <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                            <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                        </ul>
                        <a href="javascript:;" id="btn_prev${cur.index}"></a>
                        <a href="javascript:;" id="btn_next${cur.index}"></a>
                    </div>
                    <div class="houseText">
                        <p>${item.longPrice}元/月</p>
                    </div>
                </div>
            </div>


            <li>
                <a href="<%=contextPath%>/rental/match/detail?tHouseId=${item.tHouseId}&duration=${item.duration}&weixinId=${weixinId}"><i></i><span>${item.duration}分钟</span>${item.communityName} ${item.buildingNO}号${item.roomNumber}
                </a></li>
        </c:forEach>
    </ul>

</section>
<!--主题部分结束-->
</body>

</html>
