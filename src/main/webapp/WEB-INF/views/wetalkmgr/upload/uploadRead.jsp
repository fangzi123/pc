<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="upload_title"/></title>
    <link rel="shortcut icon" href="<%=pageContext.getServletContext().getContextPath()%>/images/logo/favicon.ico"
          type="image/x-icon"/>
</head>

<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container contain_width">
    <div class="row row_width">
        <div class="span12">
            <form action="displayUpload.html" id="uploadForm" method="post" class="form-horizontal"
                  enctype="multipart/form-data">
                <fieldset>
                    <div id="legend">
                        <legend><fmt:message key="upload_title"/></legend>
                    </div>
                    <div class="row">
                        <div class="span8">
                            <div class="row">
                                <div class="control-group">
                                    <label class="control-label" for="fileType"><fmt:message
                                            key="upload_fileType"/></label>

                                    <div class="controls">
                                        <input type="hidden" name="fileTypeValue" value="${fileTypeValue}">
                                        <select name="fileType" style="height:24px;line-height: 22px;width:553px"
                                                id="fileType_Id">
                                            <c:forEach var="item" items="${lstFileType}">
                                                <option value="<c:out value='${item.key}'/>">${item.text}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="control-group">
                                    <label class="control-label" for="file"><fmt:message key="choose_file"/></label>

                                    <div class="controls">
                                        <input type="file" id="file" name="file"
                                               style="height:24px;line-height: 22px;width:553px"/>
                                    </div>
                                    <p id="no_choose_file" style="display:none;color: #b94a48;" class="help-block">
                                        <i class="icon-exclamation-sign"></i><fmt:message key="no_choose_file"/>
                                    </p>
                                </div>
                            </div>
                        </div>

                        <div style="float:right;margin-left:1px;">
                            <div style="padding-top:0px;">
                                <input type="hidden" name="uploadBtn" id="uploadBtn"/>
                                <img title="<fmt:message key="query" />" src="<%=contextPath%>/images/search.png"
                                     id="searchBtn" style="cursor: pointer;">
                            </div>
                        </div>
                        <div style="float:right">
                            <div style="padding-top:0px;">
                                <img title=
                                     <fmt:message key="submit"/> src="<%=contextPath%>/images/submit.png" id="submitBtn"
                                     style="cursor: pointer;">
                            </div>
                        </div>

                    </div>
                    <div>
                        <hr style="width:100%;border-top:1px solid #e5e5e5;MARGIN-top:4px;margin-bottom:0px; "/>
                    </div>

                    <div style="width:100%;min-height:350px;max-width:940px;overflow-x: auto;margin-bottom:15px;margin-top:2px;">
                        <table id="uploadTab">
                            <thead>
                            <tr>
                                <th><fmt:message key="upload_fileType"/></th>
                                <th><fmt:message key="upload_filename"/></th>
                                <th><fmt:message key="upload_time"/></th>
                                <th><fmt:message key="upload_oper"/></th>
                                <th><fmt:message key="upload_result"/></th>
                                <th><fmt:message key="upload_status"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="upload" items="${pageList.list}">
                                <tr>
                                    <td><c:out value="${upload.fileType}"/></td>
                                    <td><c:out value="${upload.fileName}"/></td>
                                    <td><fmt:formatDate value="${upload.crtTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><c:out value="${upload.oper}"/></td>
                                    <td><c:out value="${upload.result}"/></td>
                                    <td><c:out value="${upload.status}"/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <!-- 配合分页参数使用 -->
                    <div id="exPageData">
                        <input type="hidden" value="${pageList.fullListSize}"/> <input
                            name="pageSize" type="hidden" value="${pageList.objectsPerPage}"/> <input name="page"
                                                                                                      type="hidden"
                                                                                                      value='<c:choose><c:when test="${pageList.pageNumber > 0}">${pageList.pageNumber}</c:when><c:otherwise>1</c:otherwise></c:choose>'/>

                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>
<script type="text/javascript">
    $(document).ready(function () {

        Activator.activeValidation("uploadForm", {}, {})

        $("#uploadTab").tablecloth({
            formId: "uploadForm",
            theme: "default",
            striped: true,
            bordered: true,
            pagePosition: true,
            // 隐藏列,1或[1,3]的形式
            //hideColumns : [1,9],
            // 分页栏设置项
            paginationConfig: {
                // 分页工具栏
                paginationBar: true,
                // 绑定含有提交分页数据的div id
                bindSubmitElement: "exPageData"
            }
        });

        $("#submitBtn").bind('click', function () {

            if ($("#file").val() == "") {
                $("#no_choose_file").css("display", "block");
                return false;
            }
            var fileName = $("#file").val();

            if ($("#fileType_Id").val() == "houseinfo" && fileName.indexOf('houseinfo.xlsx') < 0) {
                $("#no_choose_file").css("display", "block");
                return false;
            }
            if ($("#fileType_Id").val() == "housepicture" && fileName.indexOf('project_house_pic.zip') < 0) {
                $("#no_choose_file").css("display", "block");
                return false;
            }

            $("#no_choose_file").css("display", "none");
            $("#uploadBtn").val('uploadBtn');
            $("input[name='page']").val("1");
            $("#uploadForm").submit();
        });

        $("#searchBtn").bind('click', function () {
            $("input[name='page']").val("1");
            $("#uploadForm").submit();
        });


        //回填select框值
        var fileTypeValue = $("input[name='fileTypeValue']").val();
        $("select[name='fileType'] option").each(function () {
            if ($(this).val() == fileTypeValue) {

                $(this).attr('selected', 'selected');
            }
        });
    });
</script>
</html>
