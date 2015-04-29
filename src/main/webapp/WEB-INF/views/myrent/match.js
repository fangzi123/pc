$(document).ready(function () {

    //init
    $("#cityId").attr("code", $("input[name='cityCode']").val());
    $("#cityId").html($("input[name='cityName']").val());

    //租金上线
    $('.combo').combobox(['3000', '5000', '8000']);

    $('.combo').on('afterClick', function () {
        var newForm = '<form action="displayMatch2.html" method="post" id="match2_form">'
            + '<input name="cityCode" type="hidden" value="' + $("#cityId").attr("code") + '"/>'
            + '<input name="cityName" type="hidden" value="' + $("#cityId").html() + '"/>'
            + '<input name="workPlace" type="hidden" value="' + $("#workPlaceId").val() + '"/>'
            + '<input name="longPrice" type="hidden" value="' + $("#longPrice").val() + '"/>'
            + '<input name="weixinId" type="hidden" value="' + $("#weixinId").val() + '"/>'
            + '<input name="trafficType" type="hidden" value="' + $("#trafficTypeId").attr("code") + '"/>'
            + '<input name="trafficName" type="hidden" value="' + $("#trafficTypeId").html() + '"/>'
            + '<input name="rentalType" type="hidden" value="' + $("#rentalTypeId").attr("code") + '"/>'
            + '<input name="rentalName" type="hidden" value="' + $("#rentalTypeId").html() + '"/>'
            + '</form>'

        $(newForm).appendTo('body');

        $("#match2_form").submit();
    })

    $("#search_submit").click(function () {
        var form = $("#match_form");
        var cityCode = '';
        cityCode = $("#cityId").attr("code");

        workPlcae = $("#workPlaceId").val();


        if (!validateCity()) return;

        if (workPlcae == '') {
            $("#workPlaceId_error").html("请输入详细地址");
            $("#workPlaceId_error").addClass("wrong_box clearfix");
            $("#workPlaceId_error").css('display', 'inline-block');
            return false;
        }

        $("input[name='cityCode']").val(cityCode);
        $("input[name='cityName']").val($("#cityId").html());

        //validate  longPrice
        //var longPrice = $("input[name='longPrice']").val();
        var longPrice = $("#longPrice").val();
        //	alert("longPrice="+longPrice);
        if (longPrice == '') {
            $("#longPrice_error").html("请输入租金");
            $("#longPrice_error").addClass("wrong_box clearfix");
            $("#longPrice_error").css('display', 'inline-block');
            return false;
        }
        if (!$.isNumeric(longPrice)) {
            $("#longPrice_error").html("请输入正确的租金数值");
            $("#longPrice_error").addClass("wrong_box clearfix");
            $("#longPrice_error").css('display', 'inline-block');
            return false;
        }

        var trafficType, rentalType;
        trafficType = $("#trafficTypeId").attr("code");

        rentalType = $("#rentalTypeId").attr("code");

        $("input[name='trafficType']").val(trafficType);

        $("input[name='rentalType']").val(rentalType);

        $("#match_form").submit();

    });

    $("#workPlaceId").blur(function () {
        if ($("#workPlaceId").val() != '') {
            $("#workPlaceId_error").css('display', 'none');

            if (!validateCity()) return;

            var myGeo = new BMap.Geocoder();
            // 地址解析
            myGeo.getPoint($("#workPlaceId").val(), function (point) {
                if (!point) {
                    $("#workPlaceId_error").html("请输入正确的详细地址");
                    $("#workPlaceId_error").addClass("wrong_box clearfix");
                    $("#workPlaceId_error").css('display', 'inline-block');
                }
            }, $("#cityId").html());
        }
    });

    function validateCity() {

        if ($("#cityId").attr("code") == '') {
            $("#cityId_error").html("请选择城市");
            $("#cityId_error").addClass("wrong_box clearfix");
            $("#cityId_error").css('display', 'inline-block');
            $("#select_city_id").css('margin-bottom', '2px');
            return false;
        }
        return true;
    }
})
