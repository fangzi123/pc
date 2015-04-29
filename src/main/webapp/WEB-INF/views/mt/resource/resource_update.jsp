<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>编辑资源</title>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<script type="text/javascript" src="<%=contextPath%>/mvcjs/mt/resource/resource_update.js"></script>
<div class="container" style="width: 90%">
    <form id="resource_create_form" class="form-horizontal"
          action="resource_read.html" method="POST">
        <input type="hidden" name="opt" value="s2004"/>
        <fieldset>
            <div id="legend">
                <legend>编辑资源</legend>
            </div>
            <!-- realName -->
            <div class="control-group">
                <label class="control-label" for="urlPattern">资源匹配模式</label>

                <div class="controls">
                    <div class="input-prepend">
                        <input type="text" id="urlPattern" name="urlPattern" value="${setResource.urlPattern}"
                               class="input-xlarge" maxlength="384"/>
                        <input name="resourceId" type="hidden" value="${setResource.resourceId}"/>
                    </div>
                </div>
            </div>

            <div class="control-group">
                <!-- Button -->
                <div class="controls">
                    <button type="submit" class="btn btn-success">提交</button>
                    <a class="btn" href="resource_read.html?opt=s2000">返回</a>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>
</html>
