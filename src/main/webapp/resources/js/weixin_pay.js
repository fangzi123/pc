function callpay() {
    $("#btnPay").attr("disabled", true);
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', jsApiCall, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', jsApiCall);
            document.attachEvent('onWeixinJSBridgeReady', jsApiCall);
        }
    } else {
        jsApiCall();
    }
    $("#btnPay").attr("disabled", false);
}

function jsApiCall() {
    var appid = $("input[name='appId']").val();
    var timeStamp = $("input[name='timeStamp']").val();
    var nonceStr = $("input[name='nonceStr']").val();
    var packageStr = $("input[name='package']").val();
    var signType = $("input[name='signType']").val();
    var paySign = $("input[name='paySign']").val();

    WeixinJSBridge.invoke('getBrandWCPayRequest', {
        "appId": appid, // 公众号名称,由商户传入
        "timeStamp": timeStamp, // 时间戳,自 1970 年以来的秒数
        "nonceStr": nonceStr, // 随机串
        "package": packageStr,
        "signType": signType, // 微信签名方式:
        "paySign": paySign
        // 微信签名
    }, function (res) {
        if (res.err_msg == "get_brand_wcpay_request:ok") {
            // 支付成功（不保证后端支付成功） 跳转至支付成功页面
            $("#pay_form").submit();
        } else {
            layer.msg("支付失败，请重新支付！", 2, -1); // 2秒后自动关闭，-1代表不显示图标
        }
    });
}
