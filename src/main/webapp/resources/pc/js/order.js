$(document).ready(function() {

    var ctxPath = $("input[name='ctxPath']").val();

   
//    //确认预约
    $("#order").on("click", function() {
       addPreOrder();
    });
    $("#order-date").on("click", function() {
       addPreOrder_date();
    });
    
   
    
    var addPreOrder=function(){
        var name = $("input[name='name']").val();
        var mobile = $("input[name='mobile']").val();
        var verifyCode = $("#orderform input[name='verifyCode']").val();
        var orderDate = $("#sel-date").val();
        var houseId = $("#houseId").val();
        $.ajax({
            url : ctxPath + "/order/add_preOrder.json",
            type : 'POST',
            data : {
                name : name,
                mobile : mobile,
                verifyCode : verifyCode,
                houseId : houseId,
                orderDate : orderDate
            },
            dataType : 'json',
            success : function(data) {

                if (data.success == true) {
                    // $(".tankuang").html("").append($("#ordertrue")).show();
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-true").show();
                } else if(data.success == false){
                    // $(".tankuang").html("").append($("#orderfalse")).show();
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-false").show();
                }else{
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-already").show();
                }
            }
        })
    }

    var addPreOrder_date=function(){
        var orderDate = $("#sel-date-date").val();
        var houseId = $("#houseId").val();
        $.ajax({
            url : ctxPath + "/order/add_preOrder.json",
            type : 'POST',
            data : {
                houseId : houseId,
                orderDate : orderDate
            },
            dataType : 'json',
            success : function(data) {
                if (data.success == true) {
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-true").show();
                } else if(data.success == false){
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-false").show();
                }else{
                    $(".tankuang").hide();
                    $("#pop-win-yuyue-already").show();
                }
            }
        })
    }
});


var showPreOrder=function(){
    $("#pop-win-yuyue-true").hide();//成功页
    $("#pop-win-yuyue-false").hide();//失败页
    $("#pop-win-yuyue-already").hide();//已预约 页
    
    var pre=getCookie("preorder_mobile");
    var isLogin=$("#yonghu").text();
    if("no"!=pre||isLogin){//cookie里预约过   或者已登录
        $("#pop-win-yuyue-date").show();
    }else{
        $("#pop-win-yuyue").show();
        clearInterval(TIMER);
        $("input[name='verifyCode']").val('');
        $("#getverifycode").css({
            "background" : "#fff3e1",
            "color" : "#f39800"
        }).attr("value", '发送验证码').removeAttr("disabled");
    }
    $(".mack").show();
   
}
