<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<input id="HEAD_MENU" type="hidden" value="<shiro:principal property="menuInfo"/>"/>
<input id="HEAD_SERVLET_CTX_PATH" type="hidden" value="<%=pageContext.getServletContext().getContextPath()%>"/>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse"
               data-target=".nav-collapse"> <span class="icon-bar"></span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span>
            </a> <a class="brand" href="<%=pageContext.getServletContext().getContextPath()%>/default.html"><font
                color="#0044cc" face="微软雅黑">窝牛租房</font></a>

            <div class="nav-collapse">
                <ul class="nav">
                    <li class="divider-vertical"></li>

                </ul>
                <ul class="nav pull-right">
                    <shiro:authenticated>
                        <li class="divider-vertical"></li>
                        <li><a href="#"><i class="icon-user"></i><b class="text-error">&nbsp;<shiro:principal
                                property="userName"/></b></a></li>
                    </shiro:authenticated>

                    <li class="divider-vertical"></li>
                    <li>
                        <a href="<%=pageContext.getServletContext().getContextPath()%>/secure/authentication.html?opt=logout">
                            <i class="icon-off"></i>&nbsp;登出</a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        var ctxPath = $("#HEAD_SERVLET_CTX_PATH").val();
        var menuData = eval($("#HEAD_MENU").val())[0].children;
        var startEl = $("ul[class=nav]").get(0);
        $(menuData).each(function (i) {//iter lv1
            //render lv1
            $(startEl).append('<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-th-list"></i>&nbsp;' + this.text + '<b class="caret"></b></a></li>');

            if (this.children) {
                var lv1Node = $($("li[class=dropdown]").get(i));
                lv1Node.append('<ul class="dropdown-menu"></ul>');
                var subIdx = -1;
                $(this.children).each(function (j) {//iter lv2
                    if (this.children) {
                        $(lv1Node.find("ul[class=dropdown-menu]").get(0)).append('<li class="dropdown-submenu"><a href="#"><i class="icon-list-alt"></i>&nbsp;' + this.text + '</a><ul class="dropdown-menu"></ul></li>');
                        subIdx++;
                        $(this.children).each(function (k) {
                            $($(lv1Node.find("ul[class=dropdown-menu]").get(0)).find("li[class=dropdown-submenu]").get(subIdx)).find("ul[class=dropdown-menu]")
                                    .append('<li><a href="' + ctxPath + this.url + '"><i class="icon-list-alt"></i>&nbsp;' + this.text + '</a></li>');
                        });
                    } else {
                        $(lv1Node.find("ul[class=dropdown-menu]").get(0)).append('<li><a href="' + ctxPath + this.url + '"><i class="icon-list-alt"></i>&nbsp;' + this.text + '</a></li>');
                    }
                });
            }
        });
    });


</script>
