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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房退订</title>
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
    </style>
</head>
<body>
<form>
    <c:if test="${empty error}">
        <div class="div-house-inf">
            <div class="row">
                <span>退款申请成功！</span>
            </div>
            <div class="row">
                <span>退款额：<fmt:formatNumber pattern="###,###.##" value="${refundFee/100.0}"/>元</span>
            </div>
            <div class="row">
                <span>注意：零钱支付的退款微信公众号支付接口文档20分钟内到账,银行卡支付的退款3个工作日后到账！</span>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty error}">
        <div class="div-house-inf">
            <div class="row">
                <span>${errorMessage}</span>
            </div>
        </div>
    </c:if>

    <input type="hidden" name="refundFee" value="${refundFee}"/>
</form>
</body>
</html>
