//倒计时
var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
//timer处理函数
function setRemainTime() {
    if (curCount == 0) {
        window.clearInterval(InterValObj);//停止计时器
        $("#btnGetVerifyCode").removeClass().attr("class", "btn btn-yellow btn-block btn-big woniu-box-center").attr("onclick", "getVerifyCode()").html("<span>获取验证码</span>");
    } else {
        curCount--;
        $("#btnGetVerifyCode").removeClass("btn-yellow").addClass("btn-default").html("<span>重新获取(" + curCount + ")</span>")
    }
}


/*
 * 返回日期格式 (YYYYMMDD) 
 */
function formatToYYYYMMDD(date) {
    var month = (date.getMonth() < 9) ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1);
    var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    return date.getFullYear() + "" + month + "" + day;
}

/*
 * 返回日期格式 (YYYY年MM月DD日) 
 */
function formatToStr(date) {
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return date.getFullYear() + "年" +
        ((month < 10) ? (" " + month) : month) + "月" + ((day < 10) ? (" " + day) : day) + "日";
}

function initOrderDate() {
    // 日期选择初始化
    var date = new Date();
    // 日期button
    var dateBtn = $(".dropdown").find("button span").eq(0);
    // 日期列表
    var dateList = $(".dropdown").find("ul");
    for (var i = 0; i < 7; i++) {
        // 获取日期YYYYMMDD
        var dateNum = formatToYYYYMMDD(date);
        // 获取日期(YYYY年MM月DD日)
        var dateStr = formatToStr(date);
        // 获取周期
        var week = "";
        switch (date.getDay()) {
            case 1:
                week = "周一";
                break;
            case 2:
                week = "周二";
                break;
            case 3:
                week = "周三";
                break;
            case 4:
                week = "周四";
                break;
            case 5:
                week = "周五";
                break;
            case 6:
                week = "周六";
                break;
            case 0:
                week = "周日";
                break;
        }

        // 设置日期
        if (i == 0) {
            dateBtn.text(dateStr + " " + week);
            $("#orderDate").val(dateNum);
        }
        var dateDom = $('<li role="presentation"><a role="menuitem" tabindex="-1" href="#" dateNum=""></a></li>');
        dateDom.find("a").text(dateStr + " " + week).attr("dateNum", dateNum);
        dateDom.appendTo(dateList);
        // 递增一天
        date.setTime(date.getTime() + 86400000);
    }
}

/*
 * 自定义下拉框动作绑定
 */
function selectedItem(ulDom) {
    ulDom.find("a").on("click", function () {
        $(".dropdown button span").eq(0).text($(this).text());
        $("#orderDate").val($(this).attr("dateNum"));
    });
}

function verifyTelephone(telephone) {
    return telephone && (/^\d{11}$/).exec(telephone);
}

function getVerifyCode() {
    var tel = $("input[name='orderTel']").val();
    if (verifyTelephone(tel)) {
        // 点击按钮后，间隔1分钟后允许再次请求
        var ctxPath = $("input[name='ctxPath']").val();
        $.ajax({
            url: ctxPath + "/rental/order/get_verifyOrder.json",
            type: 'POST',
            data: {orderTel: $("input[name='orderTel']").val()},
            dataType: 'json',
            success: function (data) {
                if (data.success) {
                    $("#btnGetVerifyCode").attr("onclick", "javascript:void(0);");
                    curCount = count;
                    InterValObj = window.setInterval(setRemainTime, 1000); //启动计时器，1秒执行一次
                } else {
                    layer.msg(data.message, 1, -1); //2秒后自动关闭，-1代表不显示图标
                }
            }
        });
    } else {
        layer.msg("请输入正确的手机号！", 1, -1); //2秒后自动关闭，-1代表不显示图标
    }
}

$(document).ready(function () {
    // 初始化预约时间
    initOrderDate();
    // 自定义下拉菜单
    selectedItem($("ul"));
    // 底部按钮位置调整
    var footerHeight = $("body")[0].offsetHeight - $("div").eq(0)[0].offsetHeight;
    $("#div_footer").height(footerHeight > 88 ? footerHeight : 88);

    $("#btnPreOrder").click(function () {
        var ctxPath = $("input[name='ctxPath']").val();
        $.ajax({
            url: ctxPath + "/rental/order/add_preOrder.json",
            type: 'POST',
            data: {
                weixinId: $("input[name='weixinId']").val(),
                houseId: $("input[name='houseId']").val(),
                projectId: $("input[name='projectId']").val(),
                verifyCode: $("input[name='verifyCode']").val(),
                orderDate: $("#orderDate").val(),
                orderTel: $("input[name='orderTel']").val()
            },
            dataType: 'json',
            beforeSend: function () {
                $("#btnPreOrder").attr("disabled", true);
                curCount = 0;
            },
            success: function (data) {
                $("#btnPreOrder").attr("disabled", false);
                if (data.success) {
                    $("#addPreOrder").append("<input type='hidden' name='description' value='" + (data.description ? data.description : '') + "'" + "/>");
                    $("#addPreOrder").append("<input type='hidden' name='houseTel' value='" + (data.houseTel ? data.houseTel : '') + "'" + "/>");
                    $("#addPreOrder").append("<input type='hidden' name='houseAdd' value='" + (data.houseAdd ? data.houseAdd : '') + "'" + "/>");
                    $("#addPreOrder").append("<input type='hidden' name='orderDate' value='" + (data.orderDate ? data.orderDate : '') + "'" + "/>");
                    $("#addPreOrder").attr("action", "move_to_confirm_order");
                    $("#addPreOrder").submit();
                } else {
                    $("input[name='verifyCode']").val("");
                    layer.msg(data.message, 1, -1); //2秒后自动关闭，-1代表不显示图标
                }
            }
        });

    })
});
