$(function () {
    // 输入地址是否正确
    var isPlace = $("#isPlace").val();
    var noneFlag = $("#noneFlag").val();
    if (typeof $("#isPlace") != "undefined" && isPlace === "false") {
        if (noneFlag == 1) {
            layer.msg('此租金或者户型范围内无房源，请重新选择', 1, -1);
        } else {
            layer.msg('地点不存在,请重新输入', 1, -1);
        }
        $("#isPlace").val("true");
    }

    // 页面内容初始化
    init();

    // focus地址输入框时给其添加自动联想功能
    var focusFlg = false;
    $("#workPlaceId").on("focus", function () {
        if (!focusFlg) {
            focusFlg = true;
            new BMap.Autocomplete(
                {
                    "input": "workPlaceId"
                    , "location": "北京市"
                });
        }
    });

    // 上班方式颜色变换
    var transfMethod = $("#transf_method > div");
    var trafficType = $("input[name=trafficType]");
    $("#transf_method > div").on("touchstart", function () {
        transfMethod.removeClass("on").addClass("off");
        transfMethod.find("[id *= 'icon_']").removeClass("on").addClass("off");

        $(this).removeClass("off").addClass("on");
        $(this).find("[id *= 'icon_']").removeClass("off").addClass("on");
        trafficType.val($(this).attr("data"));
    });

    // 租金上限颜色变换
    var price = $("[name='price'] td");
    var longPrice = $("input[name=longPrice]");
    $("[name='price'] td").on("touchstart", function () {
        price.removeClass("on").addClass("off");
        $(this).removeClass("off").addClass("on");
        longPrice.val($(this).attr("data"));
    });

    // 户型上限颜色变换
    var huxing = $("[name='huxing'] td");
    var layout = $("input[name=layout]");
    $("[name='huxing'] td").on("touchstart", function () {
        huxing.removeClass("on").addClass("off");
        $(this).removeClass("off").addClass("on");
        layout.val($(this).attr("data"));
    });

    /*************wff***********/
    $("#new_search_submit").click(function () {
        if (!validate())
            return;
        var form = $("#match_form");
        form.submit();
    });

    function validate() {
        if ($("#workPlaceId").val() == '' || $("#workPlaceId").val() == undefined) {
//			   $("#workPlaceId_error").html("请输入详细地址");
//	           $("#workPlaceId_error").addClass("wrong_box clearfix");
//	           $("#workPlaceId_error").css('display','inline-block');
            layer.msg('请输入详细地址', 1, -1);
            return false;
        }
        return true;
    }

    function init() {
        var longPrice = $("input[name=longPrice]").val();
        var trafficType = $("input[name=trafficType]").val();
        var layout = $("input[name=layout]").val();

        if (longPrice) {
            $("[name='price'] td").removeClass("on").addClass("off");
            $("td[data='" + longPrice + "']").removeClass("off").addClass("on");
        } else {
            $("input[name=longPrice]").val("2000");
        }
        if (trafficType) {
            $("#transf_method div").removeClass("on").addClass("off");
            $("#transf_method div[data='" + trafficType + "']").removeClass("off").addClass("on");
            $("#transf_method div[data='" + trafficType + "']").find("[id *= 'icon_']").removeClass("off").addClass("on");
        } else {
            // 设定默认上班方式为“公共交通”
            $("input[name=trafficType]").val("2");
        }
        if (layout) {
            $("[name='huxing'] td").removeClass("on").addClass("off");
            $("[name='huxing'] td[data='" + layout + "']").removeClass("off").addClass("on");
        } else {
            $("input[name=layout]").val("0");
        }
    }

});
