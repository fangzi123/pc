$(document).ready(
    function () {
        lunbotu();
        // collect
        var ctxPath = $("input[name='ctxPath']").val();
        var houseId = $("input[name='houseId']").val();

        $("#collect").bind('click', function () {
            var projectId = $("input[name='projectId']").val();
            $.ajax({
                url: ctxPath + "/rental/favorites/addCollect.json",
                type: 'POST',
                data: {
                    weixinId: $("input[name='weixinId']").val(),
                    houseId: houseId,
                    projectId: projectId
                },
                dataType: 'json',
                success: function (data) {
                    // alert("收藏成功");
                    collected();
                }
            });

        })

        $("#order").on(
            "click",
            function () {
                var projectId = $("input[name='projectId']").val();
                var weixinId = $("input[name='weixinId']").val();
                location = ctxPath
                + '/rental/order/preOrder.html?houseId='
                + houseId + '&projectId=' + projectId
                + '&weixinId=' + weixinId;
            });

        $("#house_picture").find("img").eq(0).addClass("active");

        $("#house_picture").find("ul li").eq(0).addClass("active");

        hasCollect();

        var haso = $("#has_ordered").val();
        if (haso == "true") {
            ordered();
        }

        function collected() {
            $("#collect").removeClass("btn-yellow").addClass("btn-default")
                .attr("disabled", "disabled").text("已收藏").css("color",
                "#DA7E33");
        }

        function ordered() {
            $("#order").addClass("btn-default")
                .attr("disabled", "disabled").text("已预约");
        }

        function hasCollect() {

            $.ajax({
                url: ctxPath + "/rental/favorites/hasCollected.json",
                type: 'POST',
                data: {
                    houseId: houseId,
                },
                dataType: 'json',
                success: function (data) {
                    // alert("收藏成功");
                    if (data) {
                        collected();
                    }
                }
            });
        }

        // 百度地图API功能
        var map = new BMap.Map("allmap");
        var lon = $("input[name='cur_lon']").val();
        var lat = $("input[name='cur_lat']").val();
        var new_point = new BMap.Point(lon, lat);
        map.centerAndZoom(new_point, 16);
        map.enableScrollWheelZoom(true);

        // 用经纬度设置地图中心点

        map.clearOverlays();
        var marker = new BMap.Marker(new_point); // 创建标注
        map.addOverlay(marker); // 将标注添加到地图中
        map.panTo(new_point);

    });
