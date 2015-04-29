<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ include file="../common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="changePwd_title"/></title>
    <link rel="shortcut icon" href="<%=pageContext.getServletContext().getContextPath()%>/images/logo/favicon.ico"
          type="image/x-icon"/>
    <style type="text/css">

    </style>
</head>

<body>
<jsp:include page="../common/header.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/mvcjs/pwd/pwd.js"></script>
<div class="container" style="width: 90%">
    <fieldset>
        <div id="legend">
            <legend><fmt:message key="changePwd_title"/></legend>
        </div>
        <form action="updatePasswd.html" id="pwd_form" method="post" class="form-horizontal">
            <div class="row">

                <div class="row">
                    <div class="control-group span6">
                        <label class="control-label" for="oldPasswd"><fmt:message key="old_passwd"/></label>

                        <div class="controls">
                            <input type="password" id="oldPasswd" name="oldPasswd" value="" class="input-large"
                                   style="height:20px;"/>
                            <input type="hidden" id="username" name="username"
                                   value="<shiro:principal property="userName"/>"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="control-group span6">
                        <label class="control-label" for="newPasswd"><fmt:message key="new_passwd"/></label>

                        <div class="controls">
                            <input type="password" id="newPasswd" name="newPasswd" value="" class="input-large"
                                   style="height:20px;"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="control-group span6">
                        <label class="control-label" for="newPasswdAgain"><fmt:message key="new_passwd_again"/></label>

                        <div class="controls">
                            <input type="password" id="newPasswdAgain" name="newPasswdAgain" value=""
                                   class="input-large" style="height:20px;"/>
                        </div>
                    </div>
                </div>
                <div class="control-group span6">
                    <label class="control-label"></label>

                    <div class="controls">
                        <button type="button" class="btn" id="passwd_submit"><fmt:message key="submit"/></button>
                    </div>
                </div>
            </div>
        </form>
    </fieldset>
</div>
<div style="width:100%;min-height:280px;max-width:920px;overflow-x: auto;margin-bottom:15px;">
</div>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>
