<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="upload_title"/></title>
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link rel="shortcut icon" href="<%=pageContext.getServletContext().getContextPath()%>/images/logo/favicon.ico"
          type="image/x-icon"/>
    <style type="text/css">
        span {
            background-color: #fc0;
            display: -moz-inline-box;
            display: inline-block;
            width: 150px;
        }

        input[type='text'] {
            width: 500px;
        }

        textarea {
            width: 500px;
        }
    </style>
</head>

<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container contain_width">
    <form id="form_project" action="importPro" method="post">
        <fieldset>
            <div id="legend">
                <legend>增加项目</legend>
            </div>

            <div id="project">
                <c:if test="${updateFlag == true}">
                    <span>公寓项目编号:</span><input type="text" name="projectId" value="${projectId}" readonly/><br/>
                </c:if>
                <span>公寓公司名称:</span> <input type="text" name="brandName" value="${brandName}"/><br/>
                <span>小区名称:</span> <input type="text" name="communityName" value="${communityName}"/><br/>
                <span>所在区:</span> <input type="text" name="districtCode" value="${districtCode}"/><br/>
                <span>街道（详细地址）:</span> <input type="text" name="street" value="${street}"/><br/>
                <span>房源数量:</span> <input type="text" name="quantity" value="${quantity}"/><br/>
                <span>公寓最高楼层数:</span> <input type="text" name="totalFloor" value="${totalFloor}"/><br/>
                <span>电梯数量:</span> <input type="text" name="elevatorQuantity" value="${elevatorQuantity}"/><br/>
                <span>公寓介绍:</span> <textarea cols="50" rows="5" name="projectDesc">${projectDesc}</textarea><br/>
                <span>社区信息:</span> <textarea cols="50" rows="5" name="companyDesc">${companyDesc}</textarea><br/>
            </div>
            <div class="control-group">
                <div class="controls">
                    <input type="submit" class="btn btn-success" onclick="return confirm('确认提交？');" value="确定">
                </div>
            </div>
        </fieldset>

        <input type="hidden" name="updateFlag" value="${ updateFlag }"/>
        <input type="hidden" name="isError" value="${ isError }"/>
        <input type="hidden" name="errorMessage" value="${ errorMessage }"/>
    </form>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>

<script type="text/javascript">
    if ($("input[name=isError]").val()) {
        alert("导入失败，请重新导入！原因[" + $('input[name=errorMessage]').val() + "]");
        $("input[name=isError]").val("");
        $('input[name=errorMessage]').val("");
    }
</script>
</html>
