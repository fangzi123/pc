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
    <script>
        var ctx = '<%=contextPath%>';
    </script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/select.js"></script>
</head>

<body>
<!--头部开始-->
<header id="header"><span class="hurl"></span>提供位置信息说明</header>
<!--头部结束-->

<!--主题部分开始-->
<section>
    <p>
        窝牛租房需要使用您的位置信息，需要在您的手机中进行设置，两步完成。<br>
        第一步：打开手机的定位服务<br>
        首先进入手机的设置菜单，然后进入隐私子菜单，打开定位服务，打开微信的定位服务。<br>
        第二步：打开窝牛租房的提供位置信息开关<br>
        首先进入微信的服务号窝牛租房，点击右上角图标，进入设置页面，打开提供位置信息开关。
    </p>
</section>

<!--主题部分结束-->
</body>
</html>
