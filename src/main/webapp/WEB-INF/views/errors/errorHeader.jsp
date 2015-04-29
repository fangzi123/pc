<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%
    String contextPath = pageContext.getServletContext()
            .getContextPath();
%>
<link href="<%=contextPath%>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".nav-collapse"> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </a> <a class="brand" href="<%=pageContext.getServletContext().getContextPath()%>/default.html"><font
                color="#0044cc" face="微软雅黑">系统后台</font></a>
        </div>
    </div>
</div>
