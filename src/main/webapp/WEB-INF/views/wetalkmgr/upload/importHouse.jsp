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

        select {
            height: 30px;
        }
    </style>
</head>
<body>
<jsp:include page="../../common/header.jsp"/>
<div class="container contain_width">
    <form id="form_house" action="importHouse" method="post">
        <fieldset>
            <div id="legend">
                <legend>
                    增加房屋(项目ID:${projectId}&nbsp;&nbsp;公寓名称:${brandName}&nbsp;&nbsp;社区名称:${communityName})
                </legend>
            </div>
            <div id="house">
                <c:if test="${updateFlag == true}">
                    <span>房屋编号:</span>
                    <input type="text" name="houseId" value="${houseId}"/><br/>
                </c:if>

                <span>使用面积:</span>
                <input type="text" name="useArea" value="${useArea}"/>&nbsp;平方米<br/>

                <span>月租金（长租）:</span>
                <input type="text" name="longPrice" value="${longPrice}"/>&nbsp;元<br/>

                <span>联系方式:</span>
                <input type="text" name="telephone" value="${telephone}"/><br/>

                <span>卧室数量:</span>
                <select name="layout">
                    <option value=""></option>
                    <option value="0">开间</option>
                    <option value="1">一室</option>
                    <option value="2">两室</option>
                    <option value="3">三室</option>
                    <option value="4">四室</option>
                    <option value="5">五室</option>
                </select>
                <br/>

                <span>客厅数量:</span>
                <input type="text" name="hallQuantity" value="${hallQuantity}"/><br/>

                <span>卫生间数量:</span>
                <input type="text" name="toiletQuantity" value="${toiletQuantity}"/><br/>

                <span>橱房数量:</span>
                <input type="text" name="kitchenQuantity" value="${kitchenQuantity}"/><br/>

                <span>房屋所在楼层:</span>
                <input type="text" name="floor" value="${floor}"/><br/>

                <span>公寓总层数:</span>
                <input type="text" name="totalFloor" value="${totalFloor}"/><br/>

                <span>朝向:</span>
                <select name="orientation">
                    <option value=""></option>
                    <option value="1">东</option>
                    <option value="2">南</option>
                    <option value="3">西</option>
                    <option value="4">北</option>
                    <option value="5">东南</option>
                    <option value="6">东北</option>
                    <option value="7">西南</option>
                    <option value="8">西北</option>
                </select>
                <br/>

                <span>收费方式:</span>
                <select name="paymentType">
                    <option value=""></option>
                    <option value="2">押一付一</option>
                    <option value="1">押一付三</option>
                    <option value="3">押一付六</option>
                </select>
                <br/>

                <span>装修:</span>
                <select name="renovation">
                    <option value=""></option>
                    <option value="1">毛坯</option>
                    <option value="2">普通装修</option>
                    <option value="3">中等装修</option>
                    <option value="4">精装修</option>
                    <option value="5">豪华装修</option>
                </select>
                <br/>
                <br/>

                <span>家具:</span>
                <input type="checkbox" name="furnitureCheckBox" value="沙发"/>沙发&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="furnitureCheckBox" value="餐桌"/>餐桌&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="furnitureCheckBox" value="床"/>床&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="furnitureCheckBox" value="椅子"/>椅子&nbsp;&nbsp;&nbsp;
                其它：<input type="text" name="furnitureOther" style="width:200px;"/>
                <br/>
                <br/>

                <span>家电:</span>
                <input type="checkbox" name="appliancesCheckBox" value="彩电"/>彩电&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="appliancesCheckBox" value="冰箱"/>冰箱&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="appliancesCheckBox" value="空调"/>空调&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="appliancesCheckBox" value="洗衣机"/>洗衣机&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="appliancesCheckBox" value="微波炉"/>微波炉&nbsp;&nbsp;&nbsp;
                其它：<input type="text" name="appliancesOther" style="width:200px;"/>
                <br/>
                <br/>

                <span>有线电视:</span>
                <select name="tv">
                    <option value=""></option>
                    <option value="1">有</option>
                </select>
                <br/>

                <span>宽带:</span>
                <select name="internet">
                    <option value=""></option>
                    <option value="1">有</option>
                </select>
                <br/>

                <span>水费单价:</span>
                <input type="text" name="waterPrice" value="5"/>&nbsp;元/吨<br/>

                <span>电费单价:</span>
                <input type="text" name="electricPrice" value="0.488"/>&nbsp;元/度<br/>

                <span>燃气单价:</span>
                <input type="text" name="gasPrice"/>&nbsp;元/立方米<br/>

                <span>租赁方式:</span>
                <select name="rentalType">
                    <option value="1">整租</option>
                </select>
                <br/>

                <span>是否推荐:</span>
                <select name="flag">
                    <option value="0" selected="selected">不推荐</option>
                    <option value="1">推荐</option>
                </select>
                <br/>
            </div>

            <div class="control-group">
                <div class="controls">
                    <input type="submit" class="btn btn-success" onclick="return editInfo();" value="确定">
                </div>
            </div>
        </fieldset>

        <input id="projectId" name="projectId" value="${projectId}" type="hidden"/>
        <input name="brandName" value="${brandName}" type="hidden"/>
        <input name="communityName" value="${communityName}" type="hidden"/>
        <input name="furniture" value="${furniture}" type="hidden"/>
        <input name="appliances" value="${appliances}" type="hidden"/>
        <input name="updateFlag" value="${updateFlag}" type="hidden"/>
        <input type="hidden" name="errorMessage" value="${ errorMessage }"/>
    </form>
</div>
<jsp:include page="../../common/footer.jsp"/>
</body>
<script>
    if ($("input[name=isError]").val()) {
        alert("导入失败，请重新导入！原因[" + $('input[name=errorMessage]').val() + "]");
        $("input[name=isError]").val("");
        $('input[name=errorMessage]').val("");
    }

    // 初始化页面数值
    // 家具
    var fur = $("input[name=furniture]").val();
    if (fur) {
        var furItemArray = fur.split("，");
        var furOther = "";
        $("input[name=furnitureCheckBox]").each(function () {
            for (var i = 0; i < furItemArray.length; i++) {
                if (furItemArray[i] == $(this).val()) {
                    $(this).attr("checked", "checked");
                    furItemArray[i] = '';
                    break;
                }
            }
        });

        // furnitureOther
        for (var i = 0; i < furItemArray.length; i++) {
            if (furItemArray[i]) {
                furOther += furItemArray[i];
                furOther += "，";
            }
        }

        if (furOther) {
            $("input[name=furnitureOther]").val(furOther.substring(0, furOther.length - 1));
        }
    }
    // 家电
    var appli = $("input[name=appliances]").val();
    if (appli) {
        var appliItemArray = appli.split("，");
        var appliOther = "";
        $("input[name=appliancesCheckBox]").each(function () {
            for (var i = 0; i < appliItemArray.length; i++) {
                if (appliItemArray[i] == $(this).val()) {
                    $(this).attr("checked", "checked");
                    appliItemArray[i] = '';
                    break;
                }
            }
        });

        // applicancesOther
        for (var i = 0; i < appliItemArray.length; i++) {
            if (appliItemArray[i]) {
                appliOther += appliItemArray[i];
                appliOther += "，";
            }
        }

        if (appliOther) {
            $("input[name=appliancesOther]").val(appliOther.substring(0, appliOther.length - 1));
        }
    }

    // 编辑信息并提交
    function editInfo() {
        var furniture = "";
        var appliances = "";
        $("input[name=furnitureCheckBox]").each(function () {
            if (($(this).attr("checked")) == 'checked') {
                furniture += $(this).val();
                furniture += "，";
            }
        });
        var furnitureOther = $("input[name=furnitureOther]").val();
        if (furnitureOther) {
            furniture += furnitureOther;
            furniture += "，";
        }

        $("input[name=appliancesCheckBox]").each(function () {
            if (($(this).attr("checked")) == 'checked') {
                appliances += $(this).val();
                appliances += "，";
            }
        });
        var appliancesOther = $("input[name=appliancesOther]").val();
        if (appliancesOther) {
            appliances += appliancesOther;
            appliances += "，";
        }

        if (furniture) {
            $("input[name=furniture]").val(furniture.substring(0, furniture.length - 1));
        }
        if (appliances) {
            $("input[name=appliances]").val(appliances.substring(0, appliances.length - 1));
        }

        return confirm("确认提交？");
    }

</script>
</html>
