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
    <link rel="stylesheet" href="<%=contextPath%>/resources/css/jquery.combobox.css"/>
    <script>
        var ctx = '<%=contextPath%>';
    </script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/select.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.bgiframe.pack.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.combobox.js"></script>
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=2.0&ak=A122a2bb3e8ff58a9490406760e978f9"></script>
    <script type="text/javascript" src="<%=contextPath%>/mvcjs/myrent/match.js"></script>

    <style type="text/css">
        .e9 {
            color: #878787;
            *display: inline-block;
            *line-height: 15px;
        }

        .wrong_box {
            background-color: #0;
            border: 0px solid #ffb8b8;
            color: #f40000;
            display: inline-block;
            height: 15px;
            line-height: 15px;
            padding: 0 0px;
            _padding-top: 0px;
            _height: 15px;
        }

        .clearfix:after {
            clear: both;
            content: " ";
            display: block;
            font-size: 0;
            height: 0;
            visibility: hidden;
        }

        .clearfix {
            zoom: 1;
        }
    </style>
</head>

<body>
<!--头部开始-->
<header id="header">智能匹配</header>
<!--头部结束-->
<!--主题部分开始-->
<form action="matchResult.html" method="post" id="match_form">
    <input name='cityCode' type='hidden' value="${cityCode}"/>
    <input name='cityName' type='hidden' value="${cityName}"/>
    <input name='trafficType' type='hidden'/>

    <input name='rentalType' type='hidden'/>
    <input name='weixinId' type='hidden' value="${weixinId}" id="weixinId"/>
    <section>
        <div class="plr10">
            <ul class="form-list">
                <li class="list">
                    <div class="left">工作地点</div>
                    <div class="right">
                        <dl class="select" id="select_city_id">
                            <span></span>
                            <dt code="${cityCode}" id="cityId">${cityName}</dt>
                            <dd>
                                <ul>
                                    <li><a href="#" code="110100" regionId="">北京</a></li>
                                    <li><a href="#" code="310100" regionId="">上海</a></li>
                                </ul>
                            </dd>
                        </dl>
                        <dl>
                            <span id="cityId_error" class="e9" style="display: none; top-margin: -5px;"></span>
                        </dl>
                    </div>
                    <img src="/img/20141106/20141106131044hou_pic/H_110000010001_2_1.JPG"/>

                    <div class="left">详细地址</div>
                    <div class="right">
                        <div class="inputbox">
                            <input type="text" class="input01" name="workPlace"
                                   id="workPlaceId" value="${workPlace}"/>
                        </div>
                    </div>
                    <dl class="right">
                        <span id="workPlaceId_error" class="e9" style="display: none; top-margin: -5px;"></span>
                    </dl>
                </li>


                <li class="list">
                    <div class="left">交通方式</div>
                    <div class="right">
                        <dl class="select">
                            <span></span>
                            <dt code="${trafficType}" id="trafficTypeId">${trafficName}</dt>
                            <dd>
                                <ul>
                                    <li><a href="#" code="3">步行</a></li>
                                    <li><a href="#" code="2">公共交通</a></li>
                                    <li><a href="#" code="1">自驾</a></li>
                                </ul>
                            </dd>
                        </dl>
                    </div>
                </li>

                <li class="list">
                    <div class="left">租金上限</div>
                    <div class="right">
                        <div class="inputbox">
                            <input type="text" class="input01 combo" name="longPrice" id="longPrice"
                                   value="${longPrice}"/>
                        </div>
                    </div>
                    <div class="right">
                        <div id="longPrice_error" class="e9" style="display: none; top-margin: -5px;"></div>
                    </div>
                </li>

                <li class="list">
                    <div class="left">租凭方式</div>
                    <div class="right">
                        <dl class="select">
                            <span></span>
                            <dt code="${rentalType}" id="rentalTypeId">${rentalName}</dt>
                            <dd>
                                <ul>
                                    <li><a href="#" code="1">整租</a></li>
                                    <li><a href="#" code="2">合租</a></li>
                                    <li><a href="#" code="3">单间</a></li>
                                </ul>
                            </dd>
                        </dl>
                    </div>
                </li>
            </ul>
            <div class="btnbox"><a href="#" class="btn01" id="search_submit">搜索</a></div>
        </div>
    </section>
</form>
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
                    <a href="<%=contextPath%>/rental/map/displayMap.html?weixinId=${weixinId}"
                       class=""><span>租房地图</span></a>
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
