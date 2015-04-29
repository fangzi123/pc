<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = pageContext.getServletContext()
            .getContextPath();
%>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.touchSlider.js"></script>
<script type="text/javascript" src="<%=contextPath%>/resources/js/lunbotu.js"></script>
<link href="<%=contextPath%>/resources/css/global.css" rel="stylesheet" type="text/css">
<div class="banner">
    <div class="main_visual">
        <div class="flicking_con">
            <div class="flicking_inner">
                <a href="#">1</a>
                <a href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
            </div>
        </div>
        <div class="main_image">
            <ul>
                <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
                <li><img src="<%=contextPath%>/images/banner.jpg"/></li>
            </ul>
            <a href="javascript:;" id="btn_prev0"></a>
            <a href="javascript:;" id="btn_next0"></a>
        </div>
    </div>
</div>


