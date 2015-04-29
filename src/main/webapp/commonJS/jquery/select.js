// JavaScript Document
$(function () {
    $(".select").each(function () {
        var s = $(this);
        var z = parseInt(s.css("z-index"));
        var dt = $(this).find("dt");
        var dd = $(this).find("dd");
        var _show = function () {
            dd.slideDown(200);
            dt.addClass("cur");
            s.css("z-index", z + 1);
        };   //展开效果
        var _hide = function () {
            dd.slideUp(200);
            dt.removeClass("cur");
            s.css("z-index", z);
        };    //关闭效果
        dt.click(function () {
            dd.is(":hidden") ? _show() : _hide();
        });
        dd.find("a").live('click', function () {
            var dda = this;
            dt.text($(this).text());
            //add code attribute
            dt.attr("code", $(this).attr("code"));
            //provinceId ajax
            if (dt.attr("id") === 'cityId') {
                $("#cityId_error").css('display', 'none');
                $("#select_city_id").css('margin-bottom', '10px');
            }
            _hide();
        });     //选择效果（如需要传值，可自定义参数，在此处返回对应的"value"值 ）
        $("body").click(function (i) {
            !$(i.target).parents(".select").first().is(s) ? _hide() : "";
        });
    })


})
