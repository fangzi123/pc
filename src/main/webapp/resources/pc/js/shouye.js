$(document).ready(function() {
    $('.recomd-brands').slick({
        dots : true,
        infinite : true,
        speed : 300,
        slidesToShow : 4,
        slidesToScroll : 4,
        prevArrow : "<div class='div-pre'></div>",
        nextArrow : "<div class='div-nxt'></div>"
    });

    $("#btn-search").on("click", function() {
        $("#search-form").submit();
    });
    // 输入框回车事件
    $("#txt-search").keydown(function(event) {  
        if (event.keyCode == 13) {  
            $("#btn-search").click();
        }  
    });
    $(".brand-pic").hover(
        function(){
            var cover = $(this).find(".cover");
            cover.addClass("opacity-bg-ie");
            cover.css("background", "rgba(0,0,0,0.6)").fadeIn(400);
        },
        function(){
            $(this).find(".cover").fadeOut(400);
        }
    );
});