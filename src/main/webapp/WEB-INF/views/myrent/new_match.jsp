<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String contextPath = pageContext.getServletContext().getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=2, maximum-scale=1 ">
    <title>窝牛租房</title>
    <!-- Bootstrap -->
    <link href="<%=contextPath%>/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/common.css" rel="stylesheet">
    <link href="<%=contextPath%>/resources/css/layer.css" rel="stylesheet">
    <link href="<%=contextPath%>/mvcjs/myrent/new_match.css" rel="stylesheet">
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="<%=contextPath%>/commonJS/jquery/jquery-1.11.1.min.js"></script>
    <script src="<%=contextPath%>/commonJS/jquery/layer.min.js"></script>
    <script src="<%=contextPath%>/mvcjs/myrent/new_match.js"></script>
    <!-- 百度地图API  -->
    <script type="text/javascript"
            src="http://api.map.baidu.com/api?v=1.5&ak=108b50eb45abfdb30d056ec907130e22"></script>
</head>
<body>
<form action="new_matchResult.html" method="post" id="match_form">
    <div id="div_head" class="container-fluid div-onlycontent back-yellow">
        <div class="row">
            <div class="col-xs-3 woniu-box-center">
                <div>
                    <div class="row">
                        <div class="col-xs-12 font-white">
                            <div class="font-white font-small">工作地点</div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12 font-white">
                            北京
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-xs-9 woniu-box-center">
                <input type="text" class="form-control" name="workPlace" id="workPlaceId" placeholder="我的位置"
                       value="${workPlace}"/>
            </div>
        </div>
    </div>

    <div id="div_content" class="container-fluid">
        <div class="row">
            <div class="col-xs-12">
                <h6>租金上限（元/月）</h6>
                <table name="price">
                    <tr>
                        <td class="on" data="2000">&le;2000</td>
                        <td data="3000">3000</td>
                        <td data="4000">4000</td>
                        <td data="5000">5000</td>
                        <td data="6000">6000</td>
                    </tr>
                    <tr>
                        <td data="7000">7000</td>
                        <td data="8000">8000</td>
                        <td data="9000">9000</td>
                        <td data="10000">10000</td>
                        <td data="10001">&gt;10000</td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <h6>户型</h6>
                <table name="huxing">
                    <tr>
                        <td class="on" data="0">不限</td>
                        <td data="1">一居室</td>
                        <td data="2">两居室</td>
                        <td data="3">更多</td>
                    </tr>
                </table>
            </div>
        </div>

        <div class="row">
            <div id="transf_method" class="col-xs-12">
                <h6>上班方式</h6>

                <div id="transf_method_bus" class="col-xs-4 div-border woniu-box-center btn-normal on" data="2">
                    <div id="icon_bus" class="on"></div>
                    &nbsp;&nbsp;公共
                </div>
                <div id="transf_method_car" class="col-xs-4 div-border woniu-box-center btn-normal" data="1">
                    <div id="icon_car" class="off"></div>
                    &nbsp;&nbsp;自驾
                </div>
                <div id="transf_method_walk" class="col-xs-4 div-border woniu-box-center btn-normal" data="3">
                    <div id="icon_walk" class="off"></div>
                    &nbsp;&nbsp;步行
                </div>
            </div>
        </div>

        <div id="div_footer" class="row">
            <button type="button" class="btn btn-big font-big btn-block font-white"
                    id="new_search_submit">搜索
            </button>
        </div>
    </div>
    <div class="container-fluid fujinfangyuan">
        <h6>推荐</h6>
        <hr/>
        <div class="spn house-picture">
            <c:forEach items="${houseList}" var="item">
                <div class="col-xs-6 col-sm-6" id="img-int"
                     onclick="location='<%=contextPath%>/rental/match/detail?houseId=${item.houseId}&weixinId=${weixinId}'">
                    <div class="circle-border">
                        <div class="div-price">
                            <div>
                                &nbsp;
                                <fmt:formatNumber value="${item.longPrice}" pattern="#" type="number"/>
                                元/月
                            </div>
                        </div>
                        <img class="house-picture" src="${item.picture}"/>

                        <div class="div-house-info">
                            <div class="row">
                                <div class="col-xs-12 brandName">
                                        ${item.brandName}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <c:if test="${item.layout>0}">
                                        ${item.layout}室
                                        ${item.hallQuantity}厅｜
                                    </c:if>
                                    <c:if test="${item.layout==0}">
                                        开间｜
                                    </c:if>
                                    <fmt:formatNumber value="${item.area}" pattern="#" type="number"/>㎡
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="container-fluid fujinfangyuan">
        <h6>猜你喜欢</h6>
        <hr/>
        <div class="spn house-picture">
            <c:forEach items="${nearHouseList}" var="item">
                <div class="col-xs-6 col-sm-6" id="img-int"
                     onclick="location='<%=contextPath%>/rental/match/detail?houseId=${item.houseId}&weixinId=${weixinId}'">
                    <div>
                        <div class="div-price">
                            <div>
                                &nbsp;
                                <fmt:formatNumber value="${item.longPrice}" pattern="#" type="number"/>
                                元/月
                            </div>
                        </div>
                        <img class="house-picture" src="${item.picture}"/>

                        <div class="div-house-info">
                            <div class="row">
                                <div class="col-xs-12 brandName">
                                        ${item.brandName}
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <c:if test="${item.layout>0}">
                                        ${item.layout}室
                                        ${item.hallQuantity}厅｜
                                    </c:if>
                                    <c:if test="${item.layout==0}">
                                        开间｜
                                    </c:if>
                                    <fmt:formatNumber value="${item.area}" pattern="#" type="number"/>㎡
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <div class="bottom"></div>
    <%-- <input type="hidden" id="workPlace" name="workPlace" value="${workPlace}"/> --%>
    <input type="hidden" name="test" value="${nearHouseList}"/>
    <input type="hidden" name="trafficType" value="${trafficType}"/>
    <input type="hidden" name="longPrice" value="${longPrice}"/>
    <input type="hidden" name="cityName" value="${cityName}"/>
    <input type="hidden" name="stackIndex" value="0"/>
    <input type="hidden" name="weixinId" value="${weixinId}"/>
    <input type="hidden" name="layout" value="${layout}"/>
    <input type="hidden" id="isPlace" value="${isPlace}"/>
    <input type="hidden" id="noneFlag" value="${noneFlag}"/>
</form>
</body>
</html>
