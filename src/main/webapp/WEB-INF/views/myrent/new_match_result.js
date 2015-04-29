$(document).ready(function () {
    var range = 50; //距下边界长度/单位px
    var elemt = 5000; //插入元素高度/单位px
    var maxnum = 100; //设置加载最多次数
    var num = 1;
    var totalheight = 0;
    var main = $("#content"); //主体元素
    var stackIndex = 0; //加载一块间隔
    var workPlace = "";
    if ($("input[name=workPlace]").val()) {
        workPlace = $("input[name=workPlace]").val();
    }
    var trafficType = "";
    if ($("input[name=trafficType]").val()) {
        trafficType = parseInt($("input[name=trafficType]").val());
    }
    var longPrice = "";
    if ($("input[name=longPrice]").val()) {
        longPrice = parseFloat($("input[name=longPrice]").val());
    }
    var cityName = "";
    if ($("input[name=cityName]").val()) {
        cityName = $("input[name=cityName]").val();
    }
    var layout = "";
    if ($("input[name=layout]").val()) {
        layout = $("input[name=layout]").val();
    }
    var communityName = "";
    if ($("input[name=communityName]").val()) {
        communityName = $("input[name=communityName]").val();
    }

    // 可发送异步请求标志
    var flag = true;
    $(window).scroll(function () {
        var srollPos = $(window).scrollTop();    //滚动条距顶部距离(页面超出窗口的高度)
        totalheight = parseFloat($(window).height()) + parseFloat(srollPos);
        if (($(document).height() - range) <= totalheight && num != maxnum && flag == true) {
            // 正在发送异步请求，设置“可发送异步请求”标志为不可发送
            flag = false;
            stackIndex += 10;
            $.ajax({
                type: "post",
                dataType: 'json',
                data: {
                    "stackIndex": stackIndex,
                    "workPlace": workPlace,
                    "longPrice": longPrice,
                    "trafficType": trafficType,
                    "cityName": cityName,
                    "layout": layout,
                    "communityName": communityName
                },
                url: "/rental/rental/match/new_matchResultAjax",
                async: true,
                success: function (data) {
                    if (data.houseList && data.houseList.length > 0) {
                        $.each(data.houseList, function (index, json) {
                            var div = $("#template").clone();//克隆一份模板
                            div.find("img[name='img']").attr("src", "/img/" + json.houPicture);
                            var location = "detail?houseId=" +
                                json.houseId + "&weixinId=" +
                                $("input[name=weixinId]").val() + "'";
                            div.attr("onclick", "location='" + location);
                            div.find("span[name='communityName']").text(json.communityName);
                            div.find("span[name='buildingNO']").text(json.buildingNO);
                            div.find("span[name='doorplate']").text(json.doorplate);
                            div.find("span[name='roomNumber']").text(json.roomNumber);
                            if (json.layout == 0) {
                                div.find("span[name='layout']").text("开间");
                            } else {
                                div.find("span[name='layout']").text(json.layout + "室");
                                div.find("span[name='hallQuantity']").text(json.hallQuantity);
                            }
                            div.find("span[name='useArea']").text(json.useArea);
                            if (!communityName && (json.duration != '' || json.duration != null || json.duration != undefined)) {
                                div.find("span[name='duration']").text("上班 " + json.duration);
                            }
                            div.find("span[name='longPrice']").html(json.longPrice);
                            div.removeClass("hide");
                            div.attr("id", "");//改变行的Id
                            div.appendTo("#content");//添加到模板的容器中
                        });

                        // 本次异步请求处理完毕，设置“可发送异步请求”标志为可发送
                        flag = true;
                    } else {
                        $("#lodaing").css("display", "none");
                    }
                },
                error: function () {
                }
            });
            num++;
        }
    });
});
