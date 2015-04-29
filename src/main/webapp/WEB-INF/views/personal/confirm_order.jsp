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
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>窝牛租房</title>

    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/personal/confirm_order.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>
    <script src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>


</head>
<body>
<div class="container-fluid">
    <div class="woniu-box-center" name="div_title">
        <img id="check-img" src="<%=contextPath%>/images/check-transparent.png">
        <strong>恭喜您，预约成功！</strong>
    </div>
    <div class="row">
        <strong class="col-xs-12 small">预约信息</strong>
    </div>
    <hr/>
    <div class="row">
        <div class="col-xs-12 ">${projectDesc}</div>
    </div>
    <div class="row">
        <div class="col-xs-3 font-gray small">地址</div>
        <div class="col-xs-9 small">${communityName}</div>
    </div>
    <div class="row">
        <div class="col-xs-3 font-gray small">客服电话</div>
        <div class="col-xs-9 small font-yellow phone_number">
            <u>${telephone}</u>
        </div>
    </div>
    <div class="row " name="house_time">
        <div class="col-xs-3 font-gray small">看房时间</div>
        <div class="col-xs-9 small">${orderDate}
        </div>
    </div>
</div>
</body>
</html>
