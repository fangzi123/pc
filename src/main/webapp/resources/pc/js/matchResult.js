$(function() {
    // 检索条件
    // 初始化检索条件 TODO
    _default_conditions = {
        address : "",
        minPrice : $("#pricerange_min").val(),
        maxPrice : $("#pricerange_max").val(),
        layout : "",
        orientation : "",
        balcony : "",
        bathroom : "",
        bayWindow : "",
        sortType : 0,
        ascOrDesc : "",
        page : 1,
        pageSize : 10
    }
    _conditions = clone(_default_conditions);
    // 搜索按钮
    $("#btn-search").on("click", function() {
        var txt = $(this).prev().val();
        if (txt == "") {
            $("#div-address").remove();
        } else {
            updateTab("div-address", txt, function() {
                $("#btn-search").prev().val("");
                // 检索
                _conditions.address = "";
                match(_conditions);
            });
        }
        // 检索
        _conditions.address = txt;
        match(_conditions);
    });
    // 输入框回车事件
    $("#txt-search").keydown(function(event) {  
        if (event.keyCode == 13) {  
            $("#btn-search").click();
        }  
    });
   
    // 价格选取
    $("#range").ionRangeSlider(
            {
                type : "double",
                min : $("#pricerange_min").val(),
                max : $("#pricerange_max").val(),
                from : $("#pricerange_min").val(),
                to : $("#pricerange_max").val(),
                keyboard : true,
                prefix : "￥",
                onFinish : function(data) {
                    // 创建/变更快速tab
                    updateTab("div-price", "￥" + data.from + " ~" + "￥"
                            + data.to, function() {
                        $("#price-buxian").addClass("active");
                        // 检索
                        _conditions.maxPrice = data.max;
                        _conditions.minPrice = data.min;
                        match(_conditions);
                    });
                    // 取消不限
                    $("#price-buxian").removeClass("active");
                    // 检索
                    _conditions.maxPrice = data.to;
                    _conditions.minPrice = data.from;
                    match(_conditions);
                }
            });
    // 不限按钮(价格区)
    $("#price-buxian").on("click", function() {
        $(this).addClass("active");
        $("#range").data("ionRangeSlider").reset();
        // 取消价格选取tab
        $("#div-price").remove();
        // 检索
        _conditions.maxPrice = data.max;
        _conditions.minPrice = data.min;
        match(_conditions);
    });
    
    // 户型选取
    $("#huxing li").on("click", function() {
        $("#huxing li").removeClass("active");
        $(this).addClass("active");
        // 创建/变更tab
        updateTab("div-huxing", $(this).text(), function() {
            $("#huxing li").removeClass("active");
            $("#huxing li").eq(0).addClass("active");
            _conditions.layout = "";
            match(_conditions);
        });
        // 检索
        _conditions.layout = $(this).attr("data");
        match(_conditions);
    });
    // 取消按钮(户型区)
    $("#huxing li").eq(0).on("click", function() {
        $("#div-huxing").remove();
        // 检索
        _conditions.layout = "";
        match(_conditions);
    });

    // 其他
    $("#qita li").on(
            "click",
            function(event) {
                var targetId = event.target.id;
                if (targetId != false) {
                    if (targetId == "buxian") {
                        // 不限按钮
                        $("#qita li").removeClass("active");
                        $("#qita li").eq(0).addClass("active");
                        $("#qita input").prop("checked", false);
                        $("#qita select").val("");
                        $("[id*='div-qita-']").remove();
                        
                        _conditions.orientation = "";
                        _conditions.balcony = false;
                        _conditions.bathroom = false;
                        _conditions.bayWindow = false;
                        match(_conditions);
                    } else if (event.target.tagName == "INPUT") {
                        // 其他的选项按钮
                        $("#qita li").eq(0).removeClass("active");
                        var checkedBox = $(this).find("input:checked");
                        var id = $(this).find("input").attr("id");
                        if (checkedBox.length == 0) {
                            $(this).removeClass("active");
                            $("#" + "div-qita-" + id).remove();
                            _conditions[id] = false;
                        } else {
                            $(this).addClass("active");
                            _conditions[id] = $(this).find("input").attr("data");
                            // 创建/变更tab
                            updateTab("div-qita-" + targetId, $(this).find("label")
                                    .text(), function() {
                                var id = $(this).parent().attr("id");
                                var dom = $("#" + id.substring(9));
                                dom.parent().removeClass("active");
                                dom.prop("checked", false);
                                _conditions[id.substring(9)] = false;
                                match(_conditions);
                            });
                        }
                        match(_conditions);
                    }
                }
            });
    // 朝向
    $("#qita select").on("change", function() {
    	var value = $(this).val();
    	if (value != '') {
    	    $("#qita li").eq(0).removeClass("active");
    		updateTab("div-qita-cx", $(this).val(), function() {
                $("#qita select").val("");
                // 检索
                _conditions.orientation = "";
                match(_conditions);
            });
    	} else {
    		$("#div-qita-cx").remove();
    	}
    	// 检索
    	_conditions.orientation = value;
        match(_conditions);
    });

    // 快捷tab
    $("#div-conditions").delegate(".delete", "click", function() {
        // 删除条件tab
        $(this).parent().remove();
    });

    // 清空搜索条件
    $("#clear-con").on("click", function() {
        $(".active").removeClass("active");
        $("ul li:first-child").addClass("active");
        $(".conditions input").eq(0).val("");
        $(".conditions input:checked").prop("checked", false);
        $(".conditions select").val("");
        $(".fast-tab:gt(0)").remove();
        $("#range").data("ionRangeSlider").reset();
        // 检索
        _conditions = clone(_default_conditions);
        match(_conditions);
    });

    // 搜索结果排序
    $(".hus-subnav li").hover(function() {
        $(this).find("div").addClass("active");
    }, function() {
        if (!$(this).hasClass("sort-active")) {
            $(this).find("div").removeClass("active");
        }
    });
    $(".hus-subnav li").on("click", function() {
        var div = $(this).find("div");
        // 高亮切换
        $(".hus-subnav li").removeClass("sort-active");
        $(this).addClass("sort-active");
        $(".hus-subnav .active").removeClass("active")
        div.addClass("active");
        _conditions.sortType = $(this).attr("data");
        // 排序切换
        if ($(this).find(".arrow-up").length == 0) {
            div.removeClass("arrow-down").addClass("arrow-up");
            _conditions.ascOrDesc = "0";
        } else {
            div.removeClass("arrow-up").addClass("arrow-down");
            _conditions.ascOrDesc = "1";
        }
        match(_conditions);
    });

    // 分页
    $("#pagination").pagination({
        items : $("#total-record").val(),// 总个数
        itemsOnPage : $("#page-limit").val(), // 每页个数
        cssStyle : 'light-theme', // 指定css样式
        displayedPages : 6, // 展示页码的数量
        edges : 4,// 边界展示页码的个数
        prevText : "上一页",
        nextText : "下一页",
        onPageClick : function(pageNumber, event) {
            _conditions.page=pageNumber;
            _conditions.pageSize=$(this).itemsOnPage;
            match(_conditions,true);
            $("#currentPage").text(pageNumber);
        }
    });
    leftReset();
    rightReset();
    
    // 分页按钮（top）
    $("#top-prev").on("click", function(){
    	$("#pagination").pagination('prevPage');
    	$("#currentPage").text($("#pagination").pagination('getCurrentPage'));
    });
    $("#top-next").on("click", function(){
    	$("#pagination").pagination('nextPage');
    	$("#currentPage").text($("#pagination").pagination('getCurrentPage'));
    });
    
    // 设定预览窗口为可以区域的高度
    var right = $(".house-right").children("div");
    right.height(800);
    // 首页搜索带 地址
    if($("#txt-search").val()){
        _conditions.address=$("#txt-search").val();
        updateTab("div-address", $("#txt-search").val(), function() {
            $("#btn-search").prev().val("");
            // 检索
            _conditions.address = "";
            match(_conditions);
        });
    };
});
var _conditions, _default_conditions;

// 增加/修改快捷tab
/*
 * tabId: 创建的tab名 txt: tab中的文字 callback: 点击取消按钮时的回调函数
 */
var updateTab = function(tabId, txt, callback) {
    var tab = $("#" + tabId);
    if (tab.length == 0) {
        tab = $("#fast-tab-model").clone().attr("id", tabId).css("display",
                "inline");
        tab.find("span").text(txt);
        $("#clear-con").before(tab);
    } else {
        tab.find("span").text(txt);
    }
    tab.find("img").on("click", callback);
}
// ajax(检索)事件
function match(conditions,flag) {
    if(!flag){
        $("#pagination").pagination('selectPage', 1);
        return;
    }
    $.ajax({
        type : "POST",
        url : "listp.json",
        data : conditions,
        dataType : "json",
        async : true,
        success : function(data) {
            // 总搜索结果集发生改变
            if (data.page.totalRecord != $("#total-record").val()) {
                // 重置分页按钮
                $("#pagination").pagination("updateItems",data.page.totalRecord);
            }
            $("#page-start").val(data.page.pageStart);
            $("#page-limit").val(data.page.pageLimit);
            $("#total-record").val(data.page.totalRecord);
            $("#totalPage").text(data.page.totalPage);
            var listDom = $("#house-list");
            listDom.empty();
            //预览初始化赋值
            var preViewDom = $("#preView");
            preViewDom.empty();
            // 展示搜索结果集
            var list = data.page.results;
            if (list && list.length > 0) {
                for (var i in list) {
                    // ajax渲染房源信息
                    var picHTML="";
                    for(var j in list[i].housePics){
                       picHTML+="<input type='hidden' value='/images/house/"+list[i].housePics[j]+"' name='pic"+j+"'/>";
                    }
                    var hiddenHTML="<input type='hidden' value='"+list[i].hid+"' name='houseId'/>"
                                  +"<input type='hidden' value='"+list[i].housePics.length+"' name='pics_length'/>"
                                  +picHTML
                                  +"<input type='hidden' value='"+list[i].communityName+"' name='communityName'/>"
                                  +"<input type='hidden' value='"+list[i].brandName+"' name='brandName'/>"
                                  +"<input type='hidden' value='"+list[i].branchName+"' name='branchName'/>"
                                  +"<input type='hidden' value='"+list[i].address+"' name='address'/>"
                                  +"<input type='hidden' value='"+list[i].layout+"' name='layout'/>"
                                  +"<input type='hidden' value='"+list[i].availableArea+"' name='availableArea'/>"
                                  +"<input type='hidden' value='"+list[i].monthlyPrice+"' name='monthlyPrice'/>"
                                  +"<input type='hidden' value='"+list[i].phone+"' name='phone'/>"
                                  +"<input type='hidden' value='"+list[i].availableTime+"' name='availableTime'/>"
                                  +"<input type='hidden' value='"+list[i].floor+"' name='floor'/>"
                                  +"<input type='hidden' value='"+list[i].totalFloor+"' name='totalFloor'/>"
                                  +"<input type='hidden' value='"+list[i].lng+"' name='cur_lng'/>"
                                  +"<input type='hidden' value='"+list[i].lat+"' name='cur_lat'/>"
                                  +"<input type='hidden' value='"+list[i].paymentType+"' name='paymentType'/>"
                                  +"<input type='hidden' value='"+list[i].months+"' name='months'/>"
                                  +"<input type='hidden' value='"+list[i].orientation+"' name='orientation'/>";
                    var pointer ="<div class='arrow hidden'></div>";
                    if(i==0){
                        pointer="<div class='arrow'></div>";
                    }
                    var domHTML =
                                  "<dl>"
                                + "<input type='hidden' value='"+list[i].hid+"' name='houseId' />"
                                + "<dt><img src='/images/house/"+list[i].housePics[0]+"'></dt>"
                                + "<dd class='first-child'>"
                                + "<h1><a href='/house/findHouseDetail?houseId="+list[i].hid+"' target='_blank'>"+list[i].communityName+"</a></h1>"
                                + "<span><a href='/branch/branchDetail?branchId="+list[i].branchId+"' target='_blank' class='title'>"+list[i].branchName+"</a></span>"
                                + "<span class='address'>"+list[i].address+"</span>"
                                + "<span>"+list[i].layout+" | "+list[i].availableArea+"m²</span>"
                                + "<span class='price'>"+list[i].monthlyPrice+"元/月</span>"
                                + "</dd>"
                                + "<dd class='last-child'>"
                                + "<a href='javascript:void(0);' class='look'>预览</a>"
                                + "<a href='/house/findHouseDetail?houseId="+list[i].hid+"' class='des' target='_blank'>公寓详情</a>"
                                + pointer
                                + "</dd>"
                                + hiddenHTML
                                + "</dl>";
                    listDom.append($(domHTML));
                    if(i==0){
                        var picsObj=[];
                        for(var k in list[i].housePics){
                            picsObj.push("/images/house/"+list[0].housePics[k]);
                        }
                        preViewHtml(preViewDom,picsObj,list[i].communityName,list[i].brandName,list[i].branchName,list[i].lng,list[i].lat,list[i].address,list[i].phone,list[i].availableTime,list[i].layout,list[i].availableArea,list[i].floor,list[i].totalFloor,list[i].orientation,list[i].paymentType,list[i].months,list[i].monthlyPrice,list[i].hid);
                    }
                    }
                leftReset();
            }
        }
    });
}
//渲染预览页面
function preView(dl){
    //取值
    var hid=dl.find("input[name='houseId']").val();
    var pics_length=dl.find("input[name='pics_length']").val();
    var picsObj=[];
    for( i=0;i<pics_length;i++){
        var picValue=dl.find("input[name='pic"+i+"']").val();
        picsObj.push(picValue);
    }
    var communityName=dl.find("input[name='communityName']").val();
    var brandName=dl.find("input[name='brandName']").val();
    var branchName=dl.find("input[name='branchName']").val();
    var address=dl.find("input[name='address']").val();
    var layout=dl.find("input[name='layout']").val();
    var availableArea=dl.find("input[name='availableArea']").val();
    var monthlyPrice=dl.find("input[name='monthlyPrice']").val();
    var phone=dl.find("input[name='phone']").val();
    var availableTime=dl.find("input[name='availableTime']").val();
    var floor=dl.find("input[name='floor']").val();
    var totalFloor=dl.find("input[name='totalFloor']").val();
    var cur_lng=dl.find("input[name='cur_lng']").val();
    var cur_lat=dl.find("input[name='cur_lat']").val();
    var paymentType=dl.find("input[name='paymentType']").val();
    var months=dl.find("input[name='months']").val();
    var orientation=dl.find("input[name='orientation']").val();
    //赋值
    var preViewDom = $("#preView");
    preViewDom.empty();
    preViewHtml(preViewDom,picsObj,communityName,brandName,branchName,cur_lng,cur_lat,address,phone,availableTime,layout,availableArea,floor,totalFloor,orientation,paymentType,months,monthlyPrice,hid);
}

var clone=function(json){
    var txt = JSON.stringify(json);
    return JSON.parse(txt);
}

function preViewHtml(preViewDom,picsObj,communityName,brandName,branchName,cur_lng,cur_lat,address,phone,availableTime,layout,availableArea,floor,totalFloor,orientation,paymentType,months,monthlyPrice,hid){
    var picHTML="<div id='house-pic' class='house1'>";
    for(j=0;j<picsObj.length;j++){
        picHTML+="<div class='house-pic'><img src='"+picsObj[j]+"'></div>";
    }
    picHTML+="</div>";
    var availableTimeHTML="",floorHTML="",monthsHTML="";
    if(availableTime!=null&&availableTime!="null"&&availableTime!=""){
        availableTimeHTML= "<li>入住时间："+availableTime+"</li>";
    }
    if(floor!=null&&floor!="null"&&floor!=""){
        floorHTML= " | "+floor+" / "+totalFloor;
    }
    if(months!=null&&months!="null"&&months!=""){
        monthsHTML= "<li>最短租约："+months+"</li>" ;
    }
    var preViewHTML =
      picHTML
      +"<input type='hidden' id='houseId' value='"+hid+"'/>"
      + "<div class='house2'> "
      + "<h1>"+communityName+"</h1>"
      + "<p>"+branchName+"</p></div>"
      + "<div class='house3'> "
      + "<input type='hidden' name='cur_lng' value='"+cur_lng+"'>"
      + "<input type='hidden' name='cur_lat' value='"+cur_lat+"'>"
      + "<div name='map' id='map' style='height:200px'></div></div>"
      + "<div class='house4'>"
      + "<ul class='dizhi'>"
      + "<li>地址："+address+"</li>"
      + "<li>电话："+phone+"</li>"
      + floorHTML
      + "<li>概况："+layout+" | "+availableArea+"m&sup2;  "
      + floorHTML
      + " | "+orientation+"</li>"
      + "<li>支付方式："+paymentType+"</li>"
      + monthsHTML
      + "</ul></div>"
      + "<div class='house5'>"
      + "<p><span>"+monthlyPrice+"</span>元/月<p>"
      + "<ul class='yuyue'>"
      + "<li class='xin' id='favoriteHouse' ></li>"
      + "<li class='yue' id='preorder'>预约</li></ul>"
      + "</div>";
    preViewDom.append($(preViewHTML));
    rightReset();
}

function leftReset(){
    // 预览(按钮)
    $('.look').on("click", function() {
        $('.last-child .arrow').addClass("hidden");
        $(this).siblings(".arrow").removeClass("hidden");
        var dl=$(this).parent().parent();
        preView(dl);
    });
}

function rightReset(){
    // 预览特效(一半动一半不动)
    $(window).scroll(function() {
        var left = $(".house-left");
        var right = $(".house-right").children("div");
        var scrollTop = $(window).scrollTop();
        if (scrollTop > 340 && scrollTop < left.height() + 340 - right.height()) {
            // 设定预览窗口为可以区域的高度
            right.height($(window).height());
            // 滚动在左边区域之间
            right.removeClass("relative");
            right.addClass("fixed").css("top", 0);
        } else if (scrollTop > left.height() + 340 - right.height()) {
            // 设定预览窗口为可以区域的高度
            right.height($(window).height() - 50);
            // 滚动到底部
            right.removeClass("fixed");
            right.addClass("relative").css("top", left.height() - right.height());
        } else {
            // 滚动长度小于340
            right.height($(window).height() - 100);
            right.removeClass("fixed");
        }
    });
    $(window).resize(function(){
        // 设定预览窗口为可以区域的高度
        var right = $(".house-right").children("div");
        right.height($(window).height() - 50);
    });
    // 预览(轮播图)
    $('#house-pic').slick({
        dots : true,
        infinite : true,
        speed : 300,
        prevArrow : "<div class='div-pre'></div>",
        nextArrow : "<div class='div-nxt'></div>"
    });
    // 收藏(按钮)
    $('.yuyue li').eq(0).on("click", function() {
//        if ($(this).hasClass("yuyue-active")) {
//            $(this).removeClass("yuyue-active");
//        } else {
//            $(this).addClass("yuyue-active");
//        }
        favoriteHouse(this);
        
    });
    
    isfavoriteHouse($('.yuyue li').eq(0));
    
    //预约(按钮)
    $('.yuyue li').eq(1).on("click", function() {
        showPreOrder();
    });

    // 百度地图定位
    var lng = $("#preView").find("input[name='cur_lng']").val();
    var lat = $("#preView").find("input[name='cur_lat']").val();
    var map = new BMap.Map("map");
    var mapPoint = new BMap.Point(lng, lat);
    map.centerAndZoom(mapPoint, 16);
    map.addOverlay(new BMap.Marker(mapPoint));
    //左上角，添加默认缩放平移控件
    map.addControl(new BMap.NavigationControl());
}
