<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<%
    String rootDir = request.getScheme() + "://" + request.getServerName() + ":" +
            request.getServerPort();
%>
<!DOCTYPE html>
<html class="js">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">

    <!-- jQuery -->
    <script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="http://libs.baidu.com/bootstrap/2.0.4/js/bootstrap.min.js"></script>
    <link href="http://libs.baidu.com/bootstrap/2.0.4/css/bootstrap.min.css" rel="stylesheet">
    <!-- 共通文件  -->
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet" type="text/css">
    <link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet" type="text/css">
    <!-- 轮播图 -->
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.event.drag-1.5.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.touchSlider.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/js/lunbotu.js"></script>
    <!-- rentMap  -->
    <link href="<%=contextPath%>/mvcjs/myrent/rentmap.css" rel="stylesheet" type="text/css">
    <script src="<%=contextPath%>/mvcjs/myrent/rentmap.js" type="text/javascript"></script>
    <!-- 百度地图API  -->
    <!-- <script type="text/javascript" src="http://api.map.baidu.com/api?type=quick&ak=108b50eb45abfdb30d056ec907130e22&v=1.0"></script> -->
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=1.9&ak=108b50eb45abfdb30d056ec907130e22"></script>
    <!-- 富标签 -->
    <%-- <script type="text/javascript" src="<%=contextPath%>/resources/js/RichMarker_min.js"></script> --%>
    <!-- 点聚合 -->
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/TextIconOverlay/1.2/src/TextIconOverlay_min.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/library/MarkerClusterer/1.2/src/MarkerClusterer_min.js"></script>
    <title>窝牛租房</title>
</head>
<body>
<div id="map_area">
    <a href="<%=contextPath%>/rental/map/filterMap?weixinId=${weixinId}"><img id="change"
                                                                              src="<%=contextPath%>/images/change.png"></img></a>

    <div id="l-map"></div>
</div>
<div id="div_pictupre">
    <div class="spinner" class="hide">
        <div class="bounce1"></div>
        <div class="bounce2"></div>
        <div class="bounce3"></div>
    </div>
    <div id="house_detail" class="hide">
        <div class="banner">
            <div class="main_visual">
                <div class="flicking_con">
                    <div class="flicking_inner">
                        <div></div>
                    </div>
                </div>
                <div class="main_image">
                    <ul></ul>
                    <a href="javascript:;" id="btn_prev0"></a>
                    <a href="javascript:;" id="btn_next0"></a>
                </div>
            </div>
        </div>
        <div id="div_detail" class="container-fluid font-white">
            <div class="row hide">
                <div class="col-xs-12">
                    <span id="project_amount" class="font-big"></span>&nbsp;套待租&nbsp;&nbsp;
                    <span id="project_price" class="font-big"></span>&nbsp;元
                </div>
            </div>
            <div class="row hide">
                <div class="col-xs-12">
                    <span id="project_address"></span>&nbsp;&nbsp;
                    <span id="project_name"></span>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="templete" class="main_image hide">
    <ul></ul>
    <a href="javascript:;" id="btn_prev0"></a>
    <a href="javascript:;" id="btn_next0"></a>
</div>

<!-- 后台数据 -->
<form id="form_map" method="post">
    <div class=".hide">
        <input type="hidden" id="lng" name="longitude" value="${center.lng}">
        <input type="hidden" id="lat" name="latitude" value="${center.lat}">
        <input type="hidden" value='${lstRt}' id="points"/>
        <input type="hidden" value='<%=contextPath%>' id="ctx"/>
        <!-- 微信ID  -->
        <input type="hidden" name="weixinId" value='${weixinId}' id="weixinId"/>
        <!-- 公寓编号  -->
        <input type="hidden" id="projectId" name="projectId" value=""/>
        <!-- 小区  -->
        <input type="hidden" id="communityName" name="communityName" value=""/>
        <!-- 价格  -->
        <input type="hidden" name="longprice" value="${price}" id="longprice"/>
        <!-- 户型  -->
        <input type="hidden" name="layout" value="${layout}" id="layout"/>
        <!-- 公寓ID  -->
        <input type="hidden" name="houseId" value="${houseId}" id="houseId"/>
        <!-- 项目路径 -->
        <input type="hidden" id="rootDir" value='<%=rootDir%>'/>
    </div>
</form>
</body>
</html>
<script>
    var curLng = $("input[name='longitude']").val();
    var curLat = $("input[name='latitude']").val();
    var map = new BMap.Map("l-map");
    map.centerAndZoom(new BMap.Point(curLng, curLat), 14);
    map.enableScrollWheelZoom();
</script>
