<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>窝牛租房</title>
    <!-- Bootstrap -->
    <link href="<%=contextPath %>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath %>/resources/css/aboutus.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid div_title woniu-box-center">

    <div class="span12">
        <img alt="" src="<%=contextPath%>/images/aboutus/title.png" width="55%"/>
        <span class="spn-txt">窝牛简介</span>
    </div>
    <!-- 第一屏 end-->
    <div class="span12" id="sec-02">
        <img alt="" src="<%=contextPath%>/images/aboutus/bg02.png" width="100%"/>
    </div>
    <!-- 第二屏 end-->
    <div class="span12" id="sec-03">
        <p class="p-txt">
            在北京生活，租房是件太过寻常的事情，平均每三人中就有一个是租客。随着消费水平的提高，租客对生活品质的要求也相应上涨。近年来，市场应运出现了一批专业的公寓公司，致力于提供一站式的租住服务，大大提升了租客体验。</p>

        <p class="p-txt">
            这些都让租房真正的成为了一个行业，大而全的传统网站已经很难满足快速发展的需求。而窝牛租房，作为一家专注于长租公寓这个垂直领域的B2C平台，能够真正解决租客与公寓公司双方信息不对称的问题，为每一位租客找到最适合自己的房源。</p>
    </div>
    <div class="span12">
        <img alt="" src="<%=contextPath%>/images/aboutus/btn.png" width="100%"/>
    </div>
    <!-- 第三屏 end-->
    <div class="span12" id="sec-04">
        <img alt="" src="<%=contextPath%>/images/aboutus/title.png" width="55%"/>
        <span class="spn-txt">窝牛团队</span>

        <p id="p-top">有连续创业者
            <span class="spn-br p-txt">有地产基金VP</span>
            <span class="spn-br p-txt">有国内最大酒店管理系统开发公司负责人</span>
            <span class="spn-br p-txt">有十年经验的行业专家</span>
            <span class="spn-br p-txt">有国内前三电商公司的技术经理</span>
            <span class="spn-br p-txt">有资深营销策划</span>
            <span class="spn-br p-txt">…… </span>

        </p>
    </div>
    <!-- 第四屏 end-->
    <div class="span12">
        <img alt="" src="<%=contextPath%>/images/aboutus/footer.png" width="100%"/>
        <span class="spn-br spn-right">窝牛租房｜www.woniuzufang.com</span>
        <span class="spn-br spn-right" id="spn_r">北京捷住网络科技有限公司</span>
    </div>
</div>
</body>
</html>
