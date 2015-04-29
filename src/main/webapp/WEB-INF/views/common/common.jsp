<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page import="org.springframework.web.servlet.i18n.SessionLocaleResolver" %>
<%@page import="org.springframework.web.util.WebUtils" %>
<%@page import="java.util.Locale" %>


<%
    String contextPath = pageContext.getServletContext()
            .getContextPath();
%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store");
    response.setHeader("Expires", "-1");
%>
<%
    Locale locale = (Locale) WebUtils.getSessionAttribute(request,
            SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
    if (null == locale) {
        locale = request.getLocale();
    }
    String langue = locale.getLanguage();
%>

<!DOCTYPE html>

<link href="<%=contextPath%>/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>

<link href="<%=contextPath%>/bootstrap/plugin/datepicker/datepicker.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/bootstrap/plugin/tablecloth/bootstrap-tables.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/bootstrap/plugin/tablecloth/tablecloth.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/bootstrap/plugin/tablecloth/prettify.css" rel="stylesheet" type="text/css"/>
<link href="<%=contextPath%>/bootstrap/plugin/modal/bootstrap-modal.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">
    var langue = '<%=langue%>';
    var contextBasePath = '<%=contextPath%>';
</script>


<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery-1.8.3.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jquery/jquery.session.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqJson/jquery.json.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqJson/json2.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqValidation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqValidation/additional-methods.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqValidation/messages_zh_CN.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/jqBase64/jquery.base64.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/bootbox.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/tablecloth/jquery.tablecloth.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/modal/bootstrap-modal.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/modal/bootstrap-modalmanager.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/public.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/cascade.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/combo.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/csvexport.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/print.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/components/fold.js"></script>
<script type="text/javascript" src="<%=contextPath%>/bootstrap/plugin/activator.js"></script>
<script type="text/javascript" src="<%=contextPath%>/commonJS/biz/bizUtils.js"></script>


<style type="text/css">

</style>
<script type="text/javascript">
    var ctx = '<%=contextPath%>';
</script>
<noscript>
    <h1>本页需要浏览器支持（启用）JavaScript</h1>
</noscript>


