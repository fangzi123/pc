<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="btn-group" style="background-color:#efefef;margin-top:5px;">
    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#" id="top_a">
        <i class="icon-user"></i>
        <span class="language" style="color:#d3d3d3;font-weight:bold;"><fmt:message key="my_dia"/></span>
        <span class="caret"></span>
    </a>
    <ul class="nav nav-list dropdown-menu bs-docs-sidenav" id="top_ul" style="margin-top:5px;">

    </ul>
</div>

<!-- <ul class="nav nav-list bs-docs-sidenav" >

</ul> -->

<script type="text/javascript">

    $(document).ready(function () {
        var ctxPath = $("#HEAD_SERVLET_CTX_PATH").val();
        var menuData = eval($("#HEAD_MENU").val())[0].children;

        var startEl = $(".bs-docs-sidenav").get(0);

        $(menuData).each(function (i) {//iter lv1
            //render lv1

            $(startEl).append('<li class="signcls1"><a href="#">&nbsp;<font class="language_nav">' + BizUtils.getMenuTitle(this.text) + '</font></a></li>');
            if (this.children) {
                var lv1Node = $($("li[class=signcls1]").get(i));

                lv1Node.append('<ul class="nav nav-list signcls2"></ul>');
                var subIdx = -1;
                $(this.children).each(function (j) {//iter lv2
                    if (this.children) {
                        $(lv1Node.find("ul[class*=signcls2]").get(0)).append('<li class="signcls3"><a href="#"><i class="icon-list-alt"></i>&nbsp;<font class="language_nav">' + BizUtils.getMenuTitle(this.text) + '</font></a><ul class="nav nav-list signcls3"></ul></li>');
                        subIdx++;
                        $(this.children).each(function (k) {
                            $($(lv1Node.find("ul[class*=signcls2]").get(0)).find("li[class*=signcls3]").get(subIdx)).find("ul[class*=signcls3]")
                                    .append('<li><a href="' + ctxPath + this.url + '"><i class="icon-list-alt"></i>&nbsp;<font class="language_nav">' + BizUtils.getMenuTitle(this.text) + '</font></a></li>');
                        });
                    } else {
                        $(lv1Node.find("ul[class*=signcls2]").get(0)).append('<li class="signcls2"><a href="' + ctxPath + this.url + '"><i class="icon-list-alt"></i>&nbsp;<font class="language_nav">' + BizUtils.getMenuTitle(this.text) + '</font></a></li>');
                    }
                });
            }


        });


        $("#top_a").hover(function () {
            $("#top_a").click();
        }, function () {

        })
    });
</script>
