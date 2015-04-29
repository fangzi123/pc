$(document).ready(function () {

    var arrPoint = $("#points").val();

    arrPoint = $.parseJSON(arrPoint);

    $("#mapImg").click(function () {

        $("#mapImg").css("display", "none");
        $("#allmap").css("display", "block");

        // 百度地图API功能
        var map = new BMap.Map("allmap");

        $.each(arrPoint, function (index) {

            if (index == 0) {
                map.centerAndZoom(new BMap.Point(this.lng, this.lat), 14);
            }
            if (index > 0) {
                var marker1 = new BMap.Marker(new BMap.Point(this.lng, this.lat));  // 创建标注
                map.addOverlay(marker1);              // 将标注添加到地图中
            }
        });


        //创建信息窗口
        //var infoWindow1 = new BMap.InfoWindow("普通标注");
        //marker1.addEventListener("click", function(){this.openInfoWindow(infoWindow1);});

        $("#close-map").css("display", "inline-block");
        $("#close-img").addClass('close-img');

        $("header span").removeClass('top-search');
    })

    $("#close-img").on('click', function () {
        $("#mapImg").css("display", "block");
        $("#allmap").css("display", "none");
        $("#close-map").css("display", "none");
        $("#close-img").removeClass('close-img');

        $("header span").addClass('top-search');

        map = null;

    });

//2014-09-12


    //each
    $(".main_image").each(function (index) {
        var $this = $(this);

        var $dragBln = false;

        $this.touchSlider({
            flexible: true,
            speed: 200,
            btn_prev: $("#btn_prev" + index),
            btn_next: $("#btn_next" + +index),
            paging: $this.siblings(".flicking_con").find(".flicking_inner a"),//$(".flicking_con a"),
            counter: function (e) {
                $this.siblings(".flicking_con").find(".flicking_inner a").removeClass("on").eq(e.current - 1).addClass("on");
            }
        });

        $this.bind("mousedown", function () {
            $dragBln = false;
        })
        $this.bind("dragstart", function () {
            $dragBln = true;
        })
        $this.find("a").click(function () {
            if ($dragBln) {
                return false;
            }
        })

        /*timer = setInterval(function() { $("#btn_next"+index).click();}, 5000);
         $(".main_visual").hover(function() {
         clearInterval(timer);
         }, function() {
         timer = setInterval(function() { $("#btn_next"+index).click();}, 5000);
         })
         $(".main_image").bind("touchstart", function() {
         clearInterval(timer);
         }).bind("touchend", function() {
         timer = setInterval(function() { $("#btn_next"+index).click();}, 5000);
         })*/
    })


})
