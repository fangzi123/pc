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
    </style>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/resources/diyUpload/css/webuploader.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/resources/diyUpload/css/diyUpload.css">
    <script type="text/javascript" src="<%=contextPath%>/resources/diyUpload/js/jquery.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/diyUpload/js/webuploader.html5only.min.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/resources/diyUpload/js/diyUpload.js"></script>
</head>
<style>
    * {
        margin: 0;
        padding: 0;
    }

    #box {
        margin: 50px auto;
        width: 540px;
        min-height: 400px;
        background: #FF9
    }

    #demo {
        margin: 50px auto;
        width: 540px;
        min-height: 800px;
        background: #CF9
    }
</style>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container contain_width">
    <form>
        <fieldset>
            <div id="legend">
                <legend>
                    房屋ID：${houseId}<br/>
                    公寓名称：${brandName}<br/>
                    社区名称：${communityName}
                </legend>
            </div>
            第四步 导入房源图片(点击选择图片):<br/>
            <br/>
            继续添加 项目房源:<a
                href="<%=contextPath%>/upload/toimportHouse?projectId=${projectId}&brandName=${brandName}&communityName=${communityName}">房源录入</a><br/>

            <div id="box">
                <div id="test"></div>
            </div>
        </fieldset>
    </form>
    <input id="projectId" name="projectId" value="${projectId}" type="hidden"/>
    <input name="brandName" value="${brandName}" type="hidden"/>
    <input name="communityName" value="${communityName}" type="hidden"/>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>

<script type="text/javascript">
    /*
     * 服务器地址,成功返回,失败返回参数格式依照jquery.ajax习惯;
     * 其他参数同WebUploader
     */
    $('#test').diyUpload({
        url: 'imageupload?houseId=${houseId}',
        success: function (data) {
            alert("上传成功");
        },
        error: function (err) {
            alert("上传失败");
        }
    });

    // $('#as').diyUpload({
    // 	url:'server/fileupload.php',
    // 	success:function( data ) {
    // 		console.info( data );
    // 	},
    // 	error:function( err ) {
    // 		console.info( err );
    // 	},
    // 	buttonText : '选择文件',
    // 	chunked:true,
    // 	// 分片大小
    // 	chunkSize:512 * 1024,
    // 	//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
    // 	fileNumLimit:50,
    // 	fileSizeLimit:500000 * 1024,
    // 	fileSingleSizeLimit:50000 * 1024,
    // 	accept: {}
    // });
</script>
</html>
