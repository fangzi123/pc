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
</head>
<body>
<!--头部开始-->
<header id="header">优惠活动</header>
<!--头部结束-->

<!--主题部分开始-->
<section>
    <p><img src="<%=contextPath%>/images/adimg01.jpg"/></p>
    <a href="#" class="ad"><i></i>XXX活动，是指在城市一定区域内宅，配有成套的生活服务设施，如商业网点...</a>

    <p><img src="<%=contextPath%>/images/adimg01.jpg"/></p>
    <a href="#" class="ad"><i></i>XXX活动，是指在城市一定区域内宅，配有成套的生活服务设施，如商业网点...</a>
</section>

<!--主题部分结束-->
</body>
</html>
