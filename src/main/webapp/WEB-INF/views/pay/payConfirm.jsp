<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>窝牛租房微信支付确认</title>
    <style>
        div.div-house-inf {
            position: absolute;
            top: 15%;
            width: 90%;
            margin-left: 5%;
            margin-right: 5%;
            font: 16px "Microsoft Yahei";
        }

        div.row {
            margin-top: 3%;
            margin-bottom: 3%;
            font: 16px "Microsoft Yahei";
        }

        .bg {
            position: absolute;
            top: 0px;
            left: 0px;
            width: 100%;
        }
    </style>
</head>
<body>
<img class="bg" src="<%=contextPath %>/images/activity/1210shilihe/paySuccess.png">
</body>
</html>
