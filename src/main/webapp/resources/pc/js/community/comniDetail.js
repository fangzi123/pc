
$(function(){
    // 页面初始化
    // 分页
    $("#pagination").pagination({
        items: $("#availableHouseInfoPageTotalRecord").val(), // TODO: 搜索结果总数:初始化的时候需要初始化该值
        itemsOnPage: $("#availableHouseInfoPageLimit").val(), 
        cssStyle: 'light-theme',
        displayedPages: 10,
        edges: 3,
        onPageClick: function(pageNumber, event){
            // 发送ajax请求
            $.ajax({
                url: contextPath + "/branch/findPageidAvailableHouseInfo",
                type: "post",
                data: {"start":pageNumber, "branchId":$("#branchId").val()},
                dataType: "json",
                success: function(data) {
                    $("#currentPage").text($("#pagination").pagination('getCurrentPage'));
                    var listDom = $("#house-list").find("dl");
                    listDom.empty();
                    for (var i in data) {
                        // TODO 翻页按钮生成搜索结果，参数名字可以自己调
                        var domHTML = data[i].totalNumber > 0 ? 
                                      "<a class='btn-detail' href='/house/findHouseDetail?houseId=" + data[i].houseId + "'>详情</a>" : "<a class='btn-over'>已订完</a>";
                        var domHTML = 
                                      "<dd>                                              "
                                    + "   <dl>                                           "
                                    + "       <dt><img src='/images/layout/" + data[i].picPath + "' /></dt> "
                                    + "       <dd>" + data[i].layout + "</dd>         "
                                    + "       <dd>" + data[i].area + "</dd>              "
                                    + "       <dd>" + data[i].priceGroup.monthlyPrice + "</dd>        "
                                    + "       <dd>                                       "
                                    + domHTML
                                    + "       </dd>                                      "
                                    + "   </dl>                                          "
                                    + "   <div class='clear'></div>                      "
                                    + "</dd>                                             ";
                        listDom.append($(domHTML));
                    }
                }
            });
        },
    });
    // 百度地图定位
    var point = new Object();
    point.lng = $("#addressX").val(); // TODO 小区经度
    point.lat = $("#addressY").val(); // TODO 小区纬度
    var map = new BMap.Map("map");
    var mapPoint = new BMap.Point(point.lng, point.lat);
    map.centerAndZoom(mapPoint, 16);
    map.addOverlay(new BMap.Marker(mapPoint));
    //左上角，添加默认缩放平移控件
    map.addControl(new BMap.NavigationControl());

    // 收藏(按钮)
    $('.yuyue li').eq(0).on("click", function(){
        if ($(this).hasClass("yuyue-active")) {
            $(this).removeClass("yuyue-active");
        } else {
            $(this).addClass("yuyue-active");
        }
    });

    // 图片轮播图
    $('.house-pic-bg').slick({
        slidesToShow: 1,
        slidesToScroll: 1,
        arrows: false,
        fade: true,
        asNavFor: '.house-pic-sm'
    });

    $('.house-pic-sm').slick({
        arrows: false,
        slidesToShow: 8,
        slidesToScroll: 1,
        asNavFor: '.house-pic-bg',
        centerMode: true,
        focusOnSelect: true,
        centerPadding: "10px"
    });

    // 分页按钮(上边的)
    $("#prev").on("click", function(){
        $("#pagination").pagination('prevPage');
    });
    $("#next").on("click", function(){
        $("#pagination").pagination('nextPage');
    });
    
    // 预览特效(一半动一半不动)
    $(window).scroll(function() {
        var left = $(".main2-left");
        var right = $(".main2-right").children("div");
        var scrollTop = $(window).scrollTop();
        if (scrollTop > 116 && scrollTop < left.height() - right.height() + 90) {
            // 滚动在左边区域之间
            right.removeClass("relative");
            right.addClass("fixed").css("top", 0);
        } else if (scrollTop > left.height() - right.height() + 90) {
            // 滚动到底部
            right.removeClass("fixed");
            right.addClass("relative").css("top", left.height() - right.height() - 45);
        } else if (scrollTop < 117 ) {
            right.removeClass("fixed");
        }
    });
});
