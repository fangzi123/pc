$(function () {
    // 按钮按下去时文字变为黄色
    $("button").on("click", function () {
        $("button").css("color", "#000000");
        $(this).css("color", "#F39600");
    });

    $("button").hover(function () {
        $("button").css("background", "#FFFFFF");
        $(this).css("background", "#E6E6E6");
    });

    var type = $("#type").val();
    if (type == "f") {
        $("button").css("color", "#000000");
        $("#collectBtn").css("color", "#F39600").css("background", "#e6e6e6");
    } else if (type == "o") {
        $("button").css("color", "#000000");
        $("#orderBtn").css("color", "#F39600").css("background", "#e6e6e6");
    }
});
