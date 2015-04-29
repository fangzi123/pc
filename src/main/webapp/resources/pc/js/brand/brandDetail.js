$(function(){
    // 百度地图定位
    var map = new BMap.Map('map');
    var mapPoint = new BMap.Point(116.404, 39.915);
    map.centerAndZoom(mapPoint, 12);
    map.enableScrollWheelZoom(true);

    // 需要传递的参数模板参照下边
    var projects = JSON.parse($("#projects").val());
    var brandName = $("#brandName").val();

    // 用来生成标识字符
    /* var charCode = 65; */
    var charCode = 1;
    for ( var i in projects) {
        // 分店详情列表
        var projectDom = $("<a href='#' name='" + charCode + "'></a>"
                + "<div class='aa project-info'>"
                + "    <div class='a1'>"
                /* + String.fromCharCode(charCode) */
                + charCode
                + "</div>"
                + "    <div class='a2'>"
                + "        <a id='test' class='mingcheng' href='/branch/branchDetail?branchId="+projects[i].id+"' target='_blank'>"
                //+ brandName
                //+ "("
                + projects[i].projectName
                + "</a>"
                + "        <p class='dizhi'>地址："
                + projects[i].address
                + "</p>"
                + "        <p class='dianhua'>电话："
                + projects[i].phoneNum
                + "</p>"
                + "        <p class='qian'><span>"
                + projects[i].price
                + "元/月</span> 起</p>"
                // TODO 房屋详情页的URL和必须参数
                + "        <a href='/branch/branchDetail?branchId="+projects[i].id+"' target='_blank' class='xiangqing'>详情</a>"
                + "    </div>"
                + "    <div class='a3'><img src='/images/house/" + projects[i].img + "' alt=''/></div>"
                + "</div>");
        projectDom.appendTo($("#div-projects"));
        // 地图图标
        var mapPoint = new BMap.Point(projects[i].lng, projects[i].lat);
        var opts = {
            position : mapPoint, // 指定文本标注所在的地理位置
            offset : new BMap.Size(-11, -28)
        //设置文本偏移量
        }
        /* var label = new BMap.Label("<div class='map-point p-unactive'>"
                + String.fromCharCode(charCode) + "</div>", opts); // 创建文本标注对象 */
        var label = new BMap.Label("<a href='#" + charCode + "'>"
                + "<div class='map-point p-unactive'>" + charCode
                + "</div></a>", opts); // 创建文本标注对象
        label.setStyle({
            background: "transparent",
            border: "0px"
        });
        charCode = charCode + 1; // 标识字符递增
        label.addEventListener("click",
                function() {
                    $(".hovered").removeClass("hovered");
                    $("label.active").removeClass("active");
                    this.infoZone.addClass('hovered');
                    toUnactive(map);
                    this.setContent(this.content.replace(/p-unactive/,
                            'p-active'));
                    this.K.className = this.K.className + " active"
                });
        map.addOverlay(label);
        var labels = map.getOverlays();
        // 互相绑定
        $("#div-projects .project-info").last().data("marker",
                labels[labels.length - 1]);
        label.infoZone = projectDom;
    }

    // 鼠标hover在分店详情时
    $("[class*='project-info']").hover(function() {
        $(".hovered").removeClass('hovered');
        $(this).addClass('hovered');
        toUnactive(map);
        var layer = $(this).data("marker");
        $("label.active").removeClass("active");
        layer.K.className = layer.K.className + " active";
        layer.setContent(layer.content.replace(/p-unactive/, 'p-active'));
    }, function() {
        $(this).removeClass('hovered');
        toUnactive(map);
    });
});
/*
 * 地图标点全初始化为未选择状态
 */
var toUnactive = function(map) {
    // body...
    var layers = map.getOverlays();
    for ( var i in layers) {
        if (layers[i].content) {
            layers[i].setContent(layers[i].content.replace(/p-active/,
                    'p-unactive'));
        }
    }
}