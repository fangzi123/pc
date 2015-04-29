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
    <link href="<%=contextPath %>/resources/css/layer.css" rel="stylesheet">
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
    <script src="<%=contextPath%>/commonJS/jquery/layer.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/js/weixin_pay.js"></script>
    <title>窝牛租房微信支付</title>
    <style>
        div.div-house-inf {
            position: absolute;
            top: 36%;
            width: 90%;
            margin-left: 3%;
            margin-right: 3%;
            font: 16px "Microsoft Yahei";
        }

        div.row {
            margin-top: 3%;
            margin-bottom: 3%;
            font: 16px "Microsoft Yahei";
        }

        .bg-yellow {
            background-color: #F39600;
            color: #FFFFFF;
        }

        .input-height-lg {
            font-size: 18px;
            border-radius: 8px;
            height: 2.5em;
        }

        .bg {
            position: absolute;
            top: 0px;
            left: 0px;
            width: 100%;
        }

        .pay-msg {
            position: absolute;
            top: 15%;
            left: 0px;
            margin-left: 5%;
            margin-right: 5%;
            width: 90%;
        }
    </style>
</head>
<body>
<img class="bg" src="<%=contextPath %>/images/activity/1210shilihe/man-box-3.jpg">
<img class="pay-msg" src="<%=contextPath %>/images/activity/1210shilihe/confirm.png">

<form id="pay_form" action="confirmPay" method="post">
    <c:if test="${empty error}">
        <div class="div-house-inf">
            <div class="row">
                <input type="button" value="支付" id="btnPay" class="input-height-lg bg-yellow" style="width: 100%;"
                       onclick="callpay();"/>
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

    <input type="hidden" name="appId" value="${appId}"/>
    <input type="hidden" name="timeStamp" value="${timeStamp}"/>
    <input type="hidden" name="nonceStr" value="${nonceStr}"/>
    <input type="hidden" name="package" value="${requestScope['package']}"/>
    <input type="hidden" name="signType" value="${signType}"/>
    <input type="hidden" name="paySign" value="${paySign}"/>
    <input type="hidden" name="displayHouseName" value="${displayHouseName}"/>
    <input type="hidden" name="payFee" value="${payFee}"/>
</form>
</body>
</html>
