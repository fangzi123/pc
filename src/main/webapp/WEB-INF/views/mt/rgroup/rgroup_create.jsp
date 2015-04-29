<%@page import="com.micro.rent.common.support.JsonUtils" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建角色组</title>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/mvcjs/mt/rgroup/rgroup_create.js"></script>
<form id="group_create_form" method="POST" action="rgroup_read.html">
    <!-- groupName -->
    <div class="container" style="width:90%">
        <div class="control-group span2.2">
            <label class="control-label" for="group_name">角色组名称</label>

            <div class="controls">
                <div class="input-prepend">
                    <input type="text" id="group_name" name="group_name" maxlength="128"/>
                    <input name="rRoles" type="hidden"
                           value='<%=JsonUtils.list2JsonString((List)(request.getAttribute("rRoles")))%>'/>
                    <input name="fRoles" type="hidden"
                           value='<%=JsonUtils.list2JsonString((List)(request.getAttribute("fRoles")))%>'/>
                </div>
            </div>
        </div>
    </div>

    <div class="container" style="width:90%">
        <div class="control-group span2.2">
            <input type="hidden" name="opt" value="s5002"/>
            <label style="text-align:center;">分配资源角色</label>
            <select size="10" style="height:200px;"></select>
            <input type="hidden" id="groupName" name="groupName"/>
            <input type="hidden" id="rids" name="rids"/>
            <input type="hidden" id="fids" name="fids"/>
        </div>
        <div class="span0.5">
            <label style="text-align:center;">&nbsp;</label>

            <div style="padding-top:60px;">
                <button type="button" class="btn"><i class="icon-chevron-left"></i></button>
                <br/><br/>
                <button type="button" class="btn"><i class="icon-chevron-right"></i></button>
            </div>
        </div>
        <div class="span2.1">
            <label style="text-align:center;">资源角色菜单</label>
            <select size="10" style="height:200px;">
            </select>
        </div>
    </div>
    <div class="container" style="width:90%">
        <div class="span2.2">
            <label style="text-align:center;">分配功能角色</label>
            <select size="10" style="height:200px;"></select>
        </div>
        <div class="span0.5">
            <label style="text-align:center;">&nbsp;</label>

            <div style="padding-top:60px;">
                <button type="button" class="btn"><i class="icon-chevron-left"></i></button>
                <br/><br/>
                <button type="button" class="btn"><i class="icon-chevron-right"></i></button>
            </div>
        </div>
        <div class="span2.1">
            <label style="text-align:center;">功能角色菜单</label>
            <select size="10" style="height:200px;">
            </select>
        </div>
    </div>

    <!-- Button -->
    <div class="container" style="width:90%">
        <div class="span2.2">
            <button name="group_create_submit" type="submit" class="btn btn-success">创建</button>
            <a class="btn" href="rgroup_read.html?opt=s5000">返回</a>
        </div>
    </div>
</form>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
