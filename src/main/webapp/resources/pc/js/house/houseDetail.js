$(function(){
    isfavoriteHouse($('.yuyue li').eq(0));
    // 收藏(按钮)
    $('.yuyue li').eq(0).on("click", function(){
        favoriteHouse(this);
    });
    //预约(按钮)
    $('.yuyue li').eq(1).on("click", function() {
        showPreOrder();
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
            right.addClass("relative").css("top", left.height() - right.height() - 20);
        } else if (scrollTop < 117 ) {
            right.removeClass("fixed");
        }
    });
      /*//推荐房源的淡入淡出
        $(".brand-pic").hover(
                function(){
                    $(this).find(".cover").stop().fadeIn(400);
                },
                function(){
                    $(this).find(".cover").stop().fadeOut(400);
                }
        );*/
        
        /*$(".xiangqing").on("click", function(){
        	location.href = "findHouseDetail?houseId=" + this.getAttribute("houseId");
        });*/
    
});