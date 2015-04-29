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
    <link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet"
          type="text/css">
    <script type="text/javascript"
            src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
</head>
<body>
<!--头部开始-->
<header id="header">个人信息</header>
<!--头部结束-->
<!--主题部分开始-->
<section>
    <div class="companyBox">
        <img src="<%=contextPath%>/images/com_pic2.jpg" class="company-pic"/>
        <img src="<%=contextPath%>/images/logo.jpg" class="company-logo"/>

        <div class="conpanyTest">
            <h1>玛蒂尔德</h1>

            <p>个人简介个人简介个人简介个人简介个人简介</p>
        </div>
    </div>
    <div class="plr10 companyIntro">
        <ul class="form-list2">
            <li><span>玛蒂尔德</span>昵称</li>
            <li><span>王晓</span>姓名</li>
            <li><span>13612345678</span>联系方式</li>
        </ul>
    </div>
</section>
<!--主题部分结束-->
</body>
</html>
