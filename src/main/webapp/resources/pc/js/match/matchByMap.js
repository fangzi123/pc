 $(function(){
    var ctxPath = $("input[name='ctxPath']").val();
    // 城市选取特效
    $("#model-sel").hover(
        function() {
            $(this).find("[class*='dz-citys']").show();
        },
        function() {
            $(this).find("[class*='dz-citys']").hide();
        }
    );
    $("#model-sel a").on("click", function(){
        $("#model-sel span").text($(this).text());
        $("#model-sel").find("[class*='dz-citys']").hide();
    });
    // 输入框回车事件
    $("#input-address").keydown(function(event) {  
        if (event.keyCode == 13) {  
            $("#btn-address").click();
        }  
    });
    // 下拉列表
    $(".sel").on("click", function(event){
        event.stopPropagation();
        $(".list").hide();
        $(this).find(".list").show();
    });
    $("body").on("click", function(){
        $(".list").hide();
    });
//    $(".sel").hover(
//            function(){
//                $(this).find(".list").show();
//            },
//            function(){
//                $(".list").hide();
//            }
//    );
    $(".sel").on("click", function(){
        $(".list").hide();
        $(this).find(".list").show();
    });
    $(".list div, #btn-address").on("click", function(event){
        event.stopPropagation();
        $(this).parents(".sel").find("span").text($(this).text());
        $(this).parents(".list").hide();
        // 检索
        searchForResult(event);
    });
    $(".list").hover(
        null,
        function(){
            $(this).hide();
        }
    );
    var mapResult=$.parseJSON($("#mapResult").val());
    mapResult.houseListUrl = "community.json";
    mapResult.houseInfoUrl = ctxPath+"/house/findHouseDetail";
    // 地图初始化
    var map = $("#map").baiduMap("init", mapResult).baiduMap("getMap");
    // 初始化地图当地图移动或者放大缩小时向后台发送ajax请求
    map.addEventListener("moveend", searchForResult);
    map.addEventListener("zoomend", searchForResult);

    // 房源详情列表的高度随窗口的整体高度而变化
    $(window).on("resize", function(){
        $(".info").css("height", $(window).height() - $(".header").height() - 128);
    });
    $(".info").css("height", $(window).height() - $(".header").height() - 128);
});

var conditions = {};
var searchForResult = function (event) {
    conditions.minPrice = $("#span-min-price").text();
    conditions.maxPrice = $("#span-max-price").text();
    conditions.layout = $("#span-house-type").text();
    conditions.address = $("#input-address").val();
    $("#map").baiduMap("searchForResult", event, conditions);
}
