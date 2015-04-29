$(function () {
    // 底部按钮适应屏幕变化
    var footerHeight = $("body")[0].offsetHeight - $("#div_content")[0].offsetHeight;
    $("#div_footer").height(footerHeight > 88 ? footerHeight : 88);

    var priceTds = $("#pricetd td");
    var longPrice = $("input[name=longPrice]");
    $("#pricetd td").on("touchstart", function () {
        priceTds.removeClass("on").addClass("off");
        $(this).removeClass("off").addClass("on");
        longPrice.val($(this).attr("data"));
    });

    var layoutTds = $("#layoutd td");
    var layout = $("input[name=layout]");
    $("#layoutd td").on("touchstart", function () {
        layoutTds.removeClass("on").addClass("off");
        $(this).removeClass("off").addClass("on");
        layout.val($(this).attr("data"));
    });

    $("#filter_submit").click(function () {
        var form = $("#filter_form");
        form.submit();
    });
});
