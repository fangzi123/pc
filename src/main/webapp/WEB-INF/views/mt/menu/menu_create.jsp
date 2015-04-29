<%@page import="com.micro.rent.common.support.JsonUtils" %>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建菜单</title>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/mvcjs/mt/menu/menu_create.js"></script>
<div class="container" style="width:90%">
    <form id="menu_create_form" class="form-horizontal" action="menu_read.html" method="POST">
        <input type="hidden" name="opt" value="s3002"/>
        <fieldset>
            <div id="legend">
                <legend>创建菜单</legend>
            </div>
            <!-- realName -->
            <div class="control-group">
                <label class="control-label" for="menuName">菜单名称</label>

                <div class="controls">
                    <div class="input-prepend">
                        <input type="text" id="menuName" name="menuName"
                               class="input-xlarge" maxlength="128"/>
                    </div>
                </div>
            </div>
            <!-- gender -->
            <div class="control-group">
                <label class="control-label" for="lv">父菜单层级</label>

                <div class="controls">
                    <div class="input-prepend">
                        <select name="lv" id="lv" style="width:284px;">
                            <option></option>
                            <option value="1">一级</option>
                            <option value="2">二级</option>
                        </select>
                    </div>
                </div>
            </div>
            <!-- gender -->
            <div class="control-group">
                <label class="control-label" for="pmenuId">父菜单名称</label>

                <div class="controls">
                    <div class="input-prepend">
                        <select name="pmenuId" id="pmenuId" style="width:284px;" disabled>
                            <option value="-1"></option>
                        </select>
                        <input name="menus" type="hidden"
                               value='<%=JsonUtils.list2JsonString((List)(request.getAttribute("menus")))%>'/>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="displayOrder">菜单显示顺序</label>

                <div class="controls">
                    <div class="input-prepend">
                        <input type="text" id="displayOrder" name="displayOrder"
                               class="input-xlarge" maxlength="15"/>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="isLeaf">是否叶子节点</label>

                <div class="controls">
                    <div class="btn-group" data-toggle="buttons-radio">
                        <button disabled="disabled" type="button" value="1" class="btn active">是</button>
                        <button disabled="disabled" type="button" value="0" class="btn">否</button>
                        <input name="isLeaf" type="hidden" value="0"/>
                    </div>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="url">菜单路径</label>

                <div class="controls">
                    <div class="input-prepend">
                        <input type="text" id="url" name="url"
                               class="input-xlarge" maxlength="256"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <!-- Button -->
                <div class="controls">
                    <button type="submit" class="btn btn-success">创建</button>
                    <a class="btn" href="menu_read.html?opt=s3000">返回</a>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
