$(function(){
    // 初始化
    //登陆left-l 隐藏
    if(is_login){
        $(".left-l").css("display","none");
    }
    
    var tab=$("#tab").val();
    var cookie_mobile_null=$("#cookie_mobile_null").val();
    if(tab){
        $(".contact").eq(tab).show().siblings(".contact").hide();
        $('.title>ul>li').eq(tab).addClass('on').siblings().removeClass()
        $('#list dl').eq(tab).addClass('sel').siblings().removeClass();
    }
    if(cookie_mobile_null&&tab=="1"){
        $("#pop-win-mobile").show();
        $(".mack").show();
    }
    $("#input-mobile").on("click",function(){
        var mobile=$("input[name='mobile']").val();
        var verifyCode=$("input[name='verifyCode']").val();
        isTheSameVerifyCode(mobile,verifyCode);
        if(isSame_VERIFYCODE){
            window.location.href="preOrder?mobile="+mobile;
        }else{
            alert("验证码不一致");
        }
    })
    // 分页
    paginationInit("favorite", {
        totalRecord: $("#favorite_total").val(),
        pageLimit: $("#favorite_page_limit").val()
    });
    paginationInit("preOrder", {
        totalRecord: $("#preOrder_total").val(),
        pageLimit: $("#preOrder_page_limit").val()
    });
    paginationInit("activity", {
        totalRecord: $("#activity_total").val(),
        pageLimit: $("#activity_page_limit").val()
    });
    
    // TAB切换
    $('.title>ul>li').on('click',function(){
        var index=$(this).index();
        $(".contact").eq(index).show().siblings(".contact").hide();
        if(index==0){
            $(this).addClass('on first').siblings().removeClass();
        }else{
            $(this).addClass('on').siblings().removeClass()
        }
        $('#list dl').eq(index).addClass('sel').siblings().removeClass();
        
        if (index == 0) {
            // 收藏
            showPage("favorite");
        } else if (index == 1) {
            // 预约
            showPage("preOrder");
            if(cookie_mobile_null){
                $("#pop-win-mobile").show();
                $(".mack").show();
            }
        } else if (index == 2) {
            // 活动
            showPage("activity");
        }
    });
    // 分页按钮(上边的)
    $("[id*='_prev']").on("click", function(){
        var page = $(this).attr("id").replace("_prev", "");
        $("#" + page + "_pagination").pagination('prevPage');
    });
    $("[id*='_next']").on("click", function(){
        var page = $(this).attr("id").replace("_next", "");
        $("#" + page + "_pagination").pagination('nextPage');
    });
    
    // 删除按钮
    $(".contact").delegate(".com", "mouseover", function(){
        $(this).children("dt").children(".del").show();
    });
    $(".contact").delegate(".com", "mouseout", function(){
        $(this).children("dt").children(".del").hide();
    });
    $(".contact").delegate(".del", "click", function(){
        var page = $(this).parents("[id$='_list']").attr("id").replace("_list", "");
        var delDom = $(this).parents(".com");
        $.ajax({
            url: page + ".json",
            type: "post",
            data: {
                houseId: delDom.find("[name='house_id']").val(),
                id: $("#tenant_id").val()
            },
            dataType: "json",
            success: function() {
                delDom.remove();
            }
        });
    });
    
  //更新个人资料
    $("#update-btn").on("click",function(){
        var flag=true;
        var nickname=$("input[name='nickname']").val();
        var name=$("input[name='name']").val();
        var gender=$("select[name='gender']").val();
        var year=$("#year").val();
        var month=$("#month").val();
        var day=$("#day").val();
        var birthday=year+"/"+month+"/"+day;
        var email=$("input[name='email']").val();
        var password=$("input[name='password']").val();
        var password2=$("input[name='password2']").val();
        if(password!=password2){
            flag=false;
            alert("密码不一致");
        }
        if(flag){
            $.ajax({
                url: "updateTenant.json",
                type: "post",
                data: {
                    nickname:nickname,
                    name:name,
                    gender:gender,
                    birthday:birthday,
                    email:email,
                    password:password
                },
                dataType: "json",
                success: function(data) {
                    if(data.success){
                        alert("更新成功");
                    }
                }
            });
        }
    })
    //注销账户
    $(".zhuxiao").on("click",function(){
            $.ajax({
                url: "unsubscribe.json",
                dataType: "json",
                type: "post",
                success: function(data) {
                    if(data.success){
                        $.ajax({
                            url : ctxPath + "/logout.json",
                            type : 'post',
                            success : function(data) {
                                window.location.href=ctxPath+"/"; 
                            }
                        })
                    }
                }
            });
    })
})

var showPage = function(pageName) {
    $.ajax({
        url: pageName + ".json",
        type: "post",
        dataType: "json",
        success: function(data) {
            // 初始化分页控件
            var pageInfo = {
                totalRecord: data.page.totalRecord,
                pageLimit: data.page.pageLimit
            };
            paginationInit(pageName, pageInfo)
            // 展示预约结果
            shouResultForPage(pageName, data);
        }
    });
}

var shouResultForPage = function(pageName, data) {
    $("#" + pageName + "_currentPage").text($("#" + pageName + "_pagination").pagination('getCurrentPage'));
    $("#" + pageName + "_totalPage").text(data.page.totalPage);
    var listDiv = $("#" + pageName + "_list");
    listDiv.empty();
    for (var i in data.page.results) {
        var result = data.page.results[i];
        var domHTML;
        if (pageName == "activity") {
            var rewardBg;
            // 优惠券额度
            if (result.reward < 101) {
                rewardBg = "red";
            } else if (result.reward < 301) {
                rewardBg = "yellow";
            } else if (result.reward < 501) {
                rewardBg = "blue";
            } else {
                rewardBg = "green";
            }
            // 活动时限
            var stTime = new Date(result.startTime);
            var endTime = new Date(result.endTime);
            var now = new Date();
            var activityText;
            if (stTime > now) {
                activityText = "<p class='jjks'>活动即将开始</p>";
            } else if (stTime <= now && now <= endTime) {
                activityText = "<p class='jxz'>活动进行中</p>";
            } else {
                activityText = "<p class='yjs'>活动已结束</p>";
            }
            
            domHTML = 
                  "<div class='" + rewardBg + " com'>          "
                + " <dt>                             "
                + " <h1><span>￥</span>" + result.reward + "</h1>      "
                + " <p>有效时间</p>                  "
                + " <p>" + result.startTime + "至" + result.endTime + "</p>    "
                + " <span class='del'></span>        "
                + " </dt>                            "
                + " <dd>" + result.name + "</dd>      "
                + activityText
                + " <input type='hidden' name='house_id' value='" + result.id + "'/>"
                + "</div>                            ";
        } else {
            domHTML = 
                "<dl class='com'>                                                        "
              + "    <dt>" 
              + "<a href='" + ctxPath + "/house/findHouseDetail?houseId=" + result.houseId + "' target='_blank'>" 
              + "<img src='/images/house/" + result.picPath + "' alt=''/></a>"
              + "        <span class='del'></span>                                       "
              + "    </dt>                                                               "
              + "    <p class='wen'>" + result.branchName + "</p>                        "
              + "    <p class='zi'>" + result.layout + " | " + result.area + "m²</p>     "
              + "    <p class='qian'>" + result.monthlyPrice + "元/月</p>                 "
              + " <input type='hidden' name='house_id' value='" + result.houseId + "'/>"
              + "</dl>";
        }
        listDiv.append($(domHTML));
    }
}

var paginationInit = function(pageName, pageInfo) {
    pageInfo = $.extend({
        totalRecord: 0,
        pageLimit: 0
    }, pageInfo);
    $("#" + pageName + "_pagination").pagination({
        items: pageInfo.totalRecord,
        itemsOnPage: pageInfo.pageLimit ,
        cssStyle: 'light-theme',
        displayedPages: 10,
        edges: 3,
        onPageClick: function(pageNumber, event){
            // 发送ajax请求
            $.ajax({
                url: pageName + ".json",
                type: "post",
                data: {
                    "pageStart": pageNumber,
                },
                dataType: "json",
                success: function(data) {
                    shouResultForPage(pageName, data);
                }
            });
        },
    });
}

