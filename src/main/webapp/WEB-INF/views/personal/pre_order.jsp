<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房</title>

    <!-- Bootstrap -->
    <link href="<%=contextPath %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath %>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath %>/resources/css/layer.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/personal/pre_order.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=contextPath%>/commonJS/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=contextPath%>/commonJS/jquery/layer.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=contextPath %>/bootstrap/js/bootstrap.js"></script>

    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/select.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/mvcjs/personal/pre_order.js"></script>
</head>
<body>
<form id="addPreOrder" action="add_preOrder" method="post">
    <input type="hidden" name="weixinId" value="${weixinId}"/>
    <input type="hidden" name="projectId" value="${projectId}"/>
    <input type="hidden" name="houseId" value="${houseId}"/>
    <input type="hidden" name="ctxPath" value="<%=contextPath%>">

    <div class="container-fluid">
        <h6>看房时间</h6>

        <div class="row dropdown">
            <div class="col-xs-12 ">
                <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown">
                    <span>Dropdown</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                </ul>
            </div>
        </div>
        <input type="hidden" id="orderDate" name="orderDate"/>
        <h6>手机号码</h6>

        <div class="row">
            <div class="col-xs-12">
                <input type="text" class="form-control btn-big" name="orderTel" placeholder="请输入您的手机号"/>
            </div>
        </div>
        <div class="row">
            <div class="yanzhengma">
                <input type="text" class="form-control btn-big" name="verifyCode" placeholder="请输入验证码"/>
            </div>
            <div class="yanzhengma-btn">
                <a href="javascript:void(0);" id="btnGetVerifyCode"
                   class="btn btn-yellow btn-block btn-big woniu-box-center"
                   onclick="getVerifyCode();"><span>获取验证码</span></a>
            </div>
        </div>
    </div>

    <div id="div_footer" class="woniu-box-end">
        <div class="container-fluid">
            <div class="row" style="position: relative;">
                <div class="col-xs-12">
                    <img id="img-woniu" src="<%=contextPath%>/images/woniu.png">
                    <button type="button" class="btn btn-big font-big btn-block font-white"
                            id="btnPreOrder">确认预约
                    </button>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
