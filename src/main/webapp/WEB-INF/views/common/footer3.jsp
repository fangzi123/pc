<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${message != null}">
    <script>
        bootbox.dialog('<%=request.getAttribute("message")%>', [
            {
                "label": "确定",
                "class": "btn-primary"
            }
        ]);
    </script>
</c:if>
<c:if test='${param["success"] != null}'>
    <script>
        bootbox.dialog('操作成功', [
            {
                "label": "确定",
                "class": "btn-primary",
                "callback": function () {
                    window.location.href = "<%=pageContext.getServletContext().getContextPath()%>/promanagement/dis_pospservice.html";
                }
            }
        ]);
    </script>
</c:if>
<div style="height:47px;"></div>
<div class="navbar navbar-fixed-bottom" style="background-color: white;">
    <footer class="footer" style="width:100%;">
        <div class="container" style="width:100%;border-top:1px solid #BCBCBC">
            <p align="center" style="padding-top:10px;padding-bottom:5px;">Copyright 2014 All Rights Reserved
                XXXXXXXX公司 版权所有</p>
        </div>
    </footer>
</div>


