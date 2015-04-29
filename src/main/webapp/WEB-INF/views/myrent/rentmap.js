$(function () {
    var ctx = $("#ctx").val();
    var arrPoint = $.parseJSON($("#points").val());
    var weixinId = $("#weixinId").val();
    var rootDir = $("#rootDir").val();
    // 但地图移动或者放大缩小时搜索数据库
//	$.ajax({
//		type: "GET",
//		url: "/rental/rental/map/displayProject",
//		data: {},
//		dataType: "json",
//	});

    // 标注当前位置
    var marker = new BMap.Marker(new BMap.Point(curLng, curLat));
    var currentPoIcon = new BMap.Icon(rootDir + ctx + "/images/my_place.png",
        new BMap.Size(19, 25),
        {anchor: new BMap.Size(8, 25)});
    marker.setIcon(currentPoIcon);
    map.addOverlay(marker);
    // 创建标注
    /************************************ 普通点label模式 **********************************/
    /*$.each(arrPoint, function(index) {
     var marker = new BMap.Marker(new BMap.Point(this.longitude, this.latitude));
     var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(20,-10)});
     marker.setLabel(label);
     });*/
    /************************************ 普通点label模式 **********************************/

    /************************************ 聚合点模式***************************************/
    var markers = [];
    var yellowIcon = new BMap.Icon(rootDir + ctx + "/images/marker_org_sprite.png",
        new BMap.Size(19, 25),
        {anchor: new BMap.Size(8, 25)});
    var hotIcon = new BMap.Icon(rootDir + ctx + "/images/marker_red_sprite.png",
        new BMap.Size(19, 25),
        {anchor: new BMap.Size(8, 25)});

    $.each(arrPoint, function (index) {
        var marker = new BMap.Marker(new BMap.Point(this.longitude, this.latitude));
        marker.setIcon(hotIcon);
        marker.addEventListener("click", getFlatsPicture); // 绑定点击事件
        marker.weixinId = weixinId;
        marker.projectId = this.projectId;
        marker.communityName = this.communityName;
        markers.push(marker);
    });
    var markerClusterer = new BMapLib.MarkerClusterer(map, {markers: markers});
//	markerClusterer.setGridSize(0.5);
//	markerClusterer.setMinClusterSize(2);
    /************************************ 聚合点模式 ***************************************/

    /************************************ 富标签 ***************************************/
    /*var markerUnclick =
     '<div style="position: absolute;' +
     'margin: 0pt;' +
     'padding: 0pt;' +
     'width: 64px;' +
     'height: 32px;' +
     'left: -10px;' +
     'top: -35px;' +
     'background-image: url(' + rootDir + ctx + '/images/house-uncheck.png);' +
     'overflow: hidden;">'  +
     '</div>' +
     '<div class="BMapLabel" unselectable="on"' +
     'style="position: absolute;' +
     '-moz-user-select: none;' +
     'display: inline;' +
     'cursor: inherit;' +
     'border: 0px none;' +
     'padding: 2px 1px 1px;' +
     'white-space: nowrap;' +
     'font: 12px arial,simsun;' +
     'z-index: 80; color: #FFFFFF;' +
     'display: -webkit-box;' +
     '-webkit-box-pack: center;' +
     'width: 60px;' +
     'left: -8px; top: -33px;">';
     var markerClick =
     '<div style="position: absolute;' +
     'margin: 0pt;' +
     'padding: 0pt;' +
     'width: 64px;' +
     'height: 32px;' +
     'left: -10px;' +
     'top: -35px;' +
     'background-image: url(' + rootDir + ctx + '/images/house-check.png);' +
     'overflow: hidden;">'  +
     '</div>' +
     '<div class="BMapLabel" unselectable="on"' +
     'style="position: absolute;' +
     '-moz-user-select: none;' +
     'display: inline;' +
     'cursor: inherit;' +
     'border: 0px none;' +
     'padding: 2px 1px 1px;' +
     'white-space: nowrap;' +
     'font: 12px arial,simsun;' +
     'z-index: 80; color: #FFFFFF;' +
     'display: -webkit-box;' +
     '-webkit-box-pack: center;' +
     'width: 60px;' +
     'left: -8px; top: -33px;">';

     var markerStyleEnd = "套</div>";
     $.each(arrPoint, function(index) {
     var marker = new BMapLib.RichMarker(markerUnclick + this.houses + markerStyleEnd,
     new BMap.Point(this.longitude, this.latitude),
     {
     "anchor" : new BMap.Size(-18, -27),
     "enableDragging" : false
     });
     marker.addEventListener("click", getFlatsPicture); // 绑定点击事件
     marker.weixinId = weixinId;
     marker.projectId = this.projectId;
     map.addOverlay(marker);

     marker.addEventListener("click", function(e) {
     $("div.BMapLabel").prev().css("background-image", "url(" + rootDir + ctx + '/images/house-uncheck.png)');
     this.K.innerHTML = markerClick + this.K.innerText + "</div>";
     });
     });*/
    /************************************ 富标签 ***************************************/

    // 点击标注事件
    var preMarker = null;

    function getFlatsPicture(e) {
        var marker = e.target;
        // 设定小区名称
        $("#communityName").val(marker.communityName);
        // 被点击点切换样式
        if (preMarker != null) {
            preMarker.setIcon(hotIcon);
        }
        preMarker = marker;
        marker.setIcon(yellowIcon);
        // 动画显示图片区
        if ($("#div_pictupre").css("bottom") != "0px" && event.target.tagName == "SPAN") {
            $("#div_pictupre")[0].style.webkitAnimation = "move-up 0.3s";
            $("#div_pictupre")[0].style.webkitAnimationFillMode = "forwards";
        }
        // 显示图片加载中动画
        $(".spinner").removeClass("hide");
        // 隐藏房源详情
        $("#house_detail").addClass("hide");
        $("#div_detail").addClass("hide");
        // AJAX请求参数
        var layout = $("#layout").val();
        var longprice = $("#longprice").val();
        // 请求房源图片
        $.ajax({
            type: "GET",
            url: "/rental/rental/map/displayProject",
            data: {
                projectId: marker.projectId,
                layout: layout,
                longPrice: longprice,
                communityName: marker.communityName
            },
            dataType: "json",
            success: function (data) {
                // 隐藏图片加载中动画
                $(".spinner").addClass("hide");
                // 显示房源详情区
                $("#house_detail").removeClass("hide");
                $("#div_detail").removeClass("hide");
                // 清空上次搜索结果的照片
                $(".main_image").eq(0).remove();
                $(".flicking_inner>div").empty();
                // 添加当前房源的图片
                var templete = $("#templete").clone();
                templete.removeClass("hide");
                $("#div_detail .row").removeClass("hide");
                if (data.oneProOneHousesPic && data.oneProOneHousesPic.length > 0) {
                    $.each(data.oneProOneHousesPic, function (index, json) {
                        var imgIndex = "<a href='#'>" + (index + 1) + "</a>"
                        var imgpath = "<li><img src='" + json + "' /></li>"
                        $(".flicking_inner>div").append(imgIndex);
                        templete.append(imgpath);
                    });
                    $(".main_visual").append(templete);
                }
                lunbotu();
                // 添加房源信息
                $("#project_amount").text(data.count);
                var hprice;
                if (data.count == "1" || data.maxPrice == data.minPrice) {
                    hprice = data.maxPrice;
                    $("#houseId").val(data.houseId);
                } else {
                    hprice = data.minPrice + "-" + data.maxPrice;
                }
                $("#project_price").text(hprice);
                $("#project_address").text(data.address);
                $("#project_name").text(data.communityName);

                // 公寓编号
                $("#projectId").val(data.projectId);
                $("#lat").val(marker.point.lat);
                $("#lng").val(marker.point.lng);
                $("#communityName").val(data.communityName);
            }
        });
    }

    // 点击地图区 图片区缩回
    $("#map_area").on("touchstart touchend touchmove", function (event) {
        if ($("#div_pictupre").css("bottom") == "0px" && event.target.tagName != "SPAN") {
            $("#div_pictupre")[0].style.webkitAnimation = "move-down 0.3s";
            $("#div_pictupre")[0].style.webkitAnimationFillMode = "forwards";
        }
    });

    // 搜索房源详细信息
    $('body').delegate('img', 'click', function () {
        // 直接跳到下个页面
        var action = "/rental/rental/match/projectHousResult"
        if ($("#project_amount").text() == "1") {
            action = "/rental/rental/match/detail"
        }
        $("#form_map").attr("action", action);
        $("#form_map").submit();
    });
});
