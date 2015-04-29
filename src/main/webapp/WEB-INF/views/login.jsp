<%@page import="com.micro.rent.biz.shiro.ShiroDbRealm.ShiroUser" %>
<%@page import="org.apache.shiro.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>系统后台-用户登录</title>
</head>
<body>
<shiro:authenticated>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <img alt="" src="images/logo/vrent_ex.jpg"
                     style="height: 45px; padding-top: 5px; padding-bottom: 5px;">
            </div>
        </div>
        <!-- /navbar-inner -->
    </div>
    <h3 style="text-align:center;margin-top:10%;">
        <span class="text-success">用户[<span
                class="text-error"><%=((ShiroUser) SecurityUtils.getSubject().getPrincipal()).getUserName()%></span>]已登录</span>
    </h3>

    <p align="center" style="margin-top:5%">
        <button type="button" class="btn"
                onclick="javascript:location.href='<%=pageContext.getServletContext().getContextPath()%>/default.html'">
            返回首页
        </button>
    </p>
</shiro:authenticated>
<shiro:notAuthenticated>
    <script type="text/javascript" src="mvcjs/login.js"></script>
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container">
                <img alt="" src="images/logo/vrent_ex.jpg"
                     style="height: 45px; padding-top: 5px; padding-bottom: 5px;">
            </div>
        </div>
        <!-- /navbar-inner -->
    </div>
    <div class="container" style="width:620px; margin-top: 50px;">
        <div class="well">
            <form id="login_form" class="form-horizontal"
                  action="secure/authentication.html" method="POST">
                <input type="hidden" name="opt" value="authenticate"/>
                <fieldset>
                    <div id="legend">
                        <legend>用户登录</legend>
                    </div>
                </fieldset>
                <div class="control-group" style="margin-top: 20px;">
                    <label class="control-label" for="username">用户名</label>

                    <div class="controls">
                        <input
                                type="text" id="username" name="username" class="input-xlarge"
                                maxlength="32"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="password">密码</label>

                    <div class="controls">
                        <input
                                type="password" id="password" name="password"
                                class="input-xlarge" maxlength="32"/>
                    </div>
                </div>

                <div class="control-group">
                    <label class="control-label" for="captcha">验证码</label>

                    <div class="controls">
                        <div class="input-append">
                            <input id="captcha" type="text" name="captcha" class="input-large" style="width:152px;"
                                   maxlength="32"/>
                            <img id="captcha-img" class="add-on" style="padding: 0px 0px;height:28px;" src="">
                            <input id="captcha-hidden" type="hidden"
                                   value="<%=pageContext.getServletContext().getContextPath()%>"/>
                        </div>
                    </div>
                </div>

                <div class="control-group">
                    <!-- Button -->
                    <div class="controls" style="padding-left:10%;">
                        <button type="submit" class="btn btn-primary">登&nbsp;&nbsp;录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</shiro:notAuthenticated>
<jsp:include page="common/footer.jsp"/>
</body>
</html>
