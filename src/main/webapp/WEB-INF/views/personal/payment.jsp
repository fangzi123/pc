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
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/select.js"></script>
</head>
<body>
<!--头部开始-->
<header id="header">支付</header>
<!--头部结束-->

<!--主题部分开始-->
<section>
    <div class="plr10">
        <ul class="form-list">
            <li class="list">
                <div class="left">项目名称</div>
                <div class="right" align="right">${house.communityName}</div>
            </li>
            <li class="list">
                <div class="left">地址</div>
                <div class="right"
                     align="right">${house.communityName}${house.doorplate}号${house.buildingNo}楼${house.roomNumber}室
                </div>
            </li>
            <li class="list">
                <div class="left">价格</div>
                <div class="right" align="right">${house.longPrice}/月</div>
            </li>
            <li class="list">
                <div class="left" style="width: 100px;">租约时限</div>
                <div class="right" align="right">2年</div>
            </li>
            <li class="list">
                <div class="left">入住时间</div>
                <div class="right" align="right">2014年7月12日</div>
            </li>
            <li class="list">
                <div class="left">联系人</div>
                <div class="right" align="right">曹大达</div>
            </li>
            <li class="list">
                <div class="left">联系方式</div>
                <div class="right" align="right">13677889900</div>
            </li>

            <li class="list">
                <div class="left">订单</div>
                <div class="right">
                    <dl class="select">
                        <span></span>
                        <dt>证件类型</dt>
                        <dd>
                            <ul>
                                <li><a href="#">身份中</a></li>
                            </ul>
                        </dd>
                    </dl>
                </div>
            </li>
            <li class="list">
                <div class="left">证件号码</div>
                <div class="right">
                    <div class="inputbox">
                        <input type="text" class="input01"/>
                    </div>
                </div>
            </li>
            <li class="list">
                <div class="left">支付金额</div>
                <div class="right">
                    <div class="inputbox">
                        <input type="text" class="input01"/>
                    </div>
                </div>
            </li>
        </ul>
        <div class="ty">
            <input type="checkbox"/>同意 <a href="#">《须知》</a>
        </div>
        <div class="btnbox">
            <a href="#" class="btn01">确认支付</a>
        </div>
    </div>
</section>
<!--主题部分结束-->
</body>
</html>
