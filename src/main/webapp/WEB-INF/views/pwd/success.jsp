<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="changePwd_title"/></title>
</head>

<body>
<jsp:include page="../common/header.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/mvcjs/pwd/pwd.js"></script>
<div class="container-fluid" style="width: 90%">
    <div class="row-fluid">
        <div class="span2 bs-docs-sidebar">
            <jsp:include page="../common/leftnav.jsp"/>
        </div>
        <div class="span10">
            <fieldset>
                <div id="legend">
                    <legend><fmt:message key="changePwd_title"/></legend>
                </div>
                <fmt:message key="changePwd_success"/>
            </fieldset>
        </div>
    </div>

</div>
<jsp:include page="../common/footer.jsp"/>
<script type="text/javascript">
    $(document).ready(function () {
        //10秒钟之后跳转到指定的页面
        setTimeout(window.location.href = '<%=pageContext.getServletContext().getContextPath()%>/secure/authentication.html?opt=logout', 5000);
    });
</script>
</body>
</html>
