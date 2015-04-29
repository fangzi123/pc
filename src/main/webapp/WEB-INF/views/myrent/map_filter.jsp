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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房</title>
    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/myrent/map_filter.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=contextPath%>/mvcjs/myrent/map_filter.js"></script>
    <script src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<form action="filterProject" method="post" id="filter_form">
    <div id="div_content" class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <h6>租金上限（元/月）</h6>
                <table id="pricetd">
                    <tr>
                        <td class="on" data="2000">&le;2000</td>
                        <td data="3000">3000</td>
                        <td data="4000">4000</td>
                        <td data="5000">5000</td>
                        <td data="6000">6000</td>
                    </tr>
                    <tr>
                        <td data="7000">7000</td>
                        <td data="8000">8000</td>
                        <td data="9000">9000</td>
                        <td data="10000">10000</td>
                        <td data="10001">&gt;10000</td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <h6>户型</h6>
                <table id="layoutd">
                    <tr>
                        <td class="on" data="0">不限</td>
                        <td data="1">一居室</td>
                        <td data="2">两居室</td>
                        <td data="3">更多</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>

    <div id="div_footer" class="container-fluid woniu-box-end">
        <button type="button" class="btn btn-big font-big btn-block font-white" id="filter_submit">筛选</button>
    </div>
    <input type="hidden" name="longPrice" value="2000"/>
    <input type="hidden" name="layout"/>
    <input type="hidden" name="stackIndex" value="0"/>
    <input type="hidden" name="weixinId" value="${weixinId}"/>
</form>
</body>

</html>

