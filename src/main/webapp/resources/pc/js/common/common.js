var TIMER;//倒计时
var VERIFYCODE;//验证码
var isSame_VERIFYCODE;//验证码
$(document).ready(function() {
    // 城市选取特效
    $("#model-sel").hover(function() {
        $(this).find("[class*='dz-citys']").show();
    }, function() {
        $(this).find("[class*='dz-citys']").hide();
    });
    $("#model-sel a").on("click", function() {
        $("#model-sel span").text($(this).text());
        $("#model-sel").find("[class*='dz-citys']").hide();
    });

    $("#logo").on("click", function() {
        $("#pop-win-neirong").show();
        $(".mack").show();
    })
    $("#register").on("click", function() {
        $("#pop-win-register").show();
        $(".mack").show();
    })
  $("#wnnn").hover(function() {
       $("#dz-denglu").show();
    }, function() {
       $("#dz-denglu").hide();
   });
    
    $(".wnnnn").hover(function() {
        $("#dz-dengl").show();
     }, function() {
       $("#dz-dengl").hide();
   });
 

    $(".mack").on("click", function() {
        $("[id^='pop-win-']").hide();
        $(".mack").hide();
    });
    $(".fang").on("click", function() {
        $("[id^='pop-win-']").hide();
        $(".mack").hide();
    });

    $("#logon").hover(
            function() {
            	  $(this).find("[class*='dz-c']").show();
            },
            function() {
                $(this).find("[class*='dz-c']").hide();
            }
    );
    $("#zcc").on("click", function() {
        $("#pop-win-neirong").hide();
        $("#pop-win-register").show();
    })
    $("#dll").on("click", function() {
        $("#pop-win-register").hide();
        $("#pop-win-neirong").show();
    })
    // 登录
    var ctxPath = $("input[name='ctxPath']").val();
    $("#login").on("click", function() {
    var form=$("#login-form");
    var username = form.find("input[name='username']").val();
    var password = form.find("input[name='password']").val();
      if(form.validation("validate")){
      $.ajax({
            url : ctxPath + "/login.json",
            type : 'POST',
            data : {
                username : username,
                password : password
            },
            dataType : 'json',
            async:false,
            success : function(data) {
                if (data.success == true) {
                    window.location.href=ctxPath+"/"; 
                }else{
                    $("#login-check").text("用户名或密码错误");
                }
            }
        })
        }
    });
    // 注册
    $("#register-btn").on("click", function() {
        var form=$("#register-form");
        var username = form.find("input[name='username']").val();
        var password = form.find("input[name='password']").val();
        var verifyCode = form.find("input[name='verifyCode']").val();
        if(form.validation("validate")){
        if(verifyCode!=VERIFYCODE){
            $("#register-code").text("验证码错误");
            return;
        }
          $.ajax({
                url : ctxPath + "/register.json",
                type : 'POST',
                data : {
                    username : username,
                    password : password
                },
                dataType : 'json',
                async:false,
                success : function(data) {
                    if (data.success == true) {
                        $.ajax({
                            url : ctxPath + "/login.json",
                            type : 'POST',
                            data : {
                                username : username,
                                password : password
                            },
                            dataType : 'json',
                            async:false,
                            success : function(data) {
                                if (data.success == true) {
                                    window.location.href=ctxPath+"/"; 
                                    $("#pop-win-registerOk").show();
                                    $("#mack-div").show();
                                }
                            }
                        })
                      
                    }else{
                        $("#register-check").text("手机已经注册过，请更换手机号");
                    }
                }
            })}
    });
    $("#passConfirm").on("focus", function(){
        // 聚焦密码确认框时才显示密码确认消息
        $("#passConfirmMsg").show();
    });
    
    $("#logout").on("click", function() {
        $.ajax({
            url : ctxPath + "/logout.json",
            type : 'post',
            success : function(data) {
                window.location.href=ctxPath+"/"; 
            }
        })
    })

    // 发送短信密码验证
    $(".anniu").on("click", function() {
        // $("#login").removeAttr("disabled");

        var timer = countDown(this);
        var username = $(this).parents("form").find("input[name='username']").val();
        dynamicPassword(this, username, timer);

    })

    // 发送短信验证码
    $("#getverifycode").on("click", function() {
        var timer = countDown(this);
        var mobile = {
            mobile : $("input[name='mobile']").val()
        };
        getVerifyCode(this, mobile, timer);
    })
    //加盟 发送短信验证码
    $("#alliance-verify").on("click", function() {
        var timer = countDown(this);
        var mobile = {
                mobile : $("input[name='phone']").val()
        };
        getVerifyCode(this, mobile, timer);
    })

    // 倒计时
    var countDown = function(btn) {
        var i = 59;
        $(btn).css({
            "background" : "#DCDCDC",
            "color" : "#ffffff"
        }).attr("value", '倒计时' + i + '秒').attr("disabled", "disabled");
        ;
        var timer = setInterval(function() {
            i--;
            $(btn).attr("value", '倒计时' + i + '秒');
            // 模拟数据:如果短信验证发送失败,则立即停止倒计时
            var str = '失败';
            if (i < 1 || !str) {
                $(btn).css({
                    "background" : "#fff3e1",
                    "color" : "#f39800"
                }).attr("value", '重新获取').removeAttr("disabled");
                // $("#login").attr("disabled", "disabled");
                clearInterval(timer);
            }
        }, 1000);
        TIMER = timer;
        return timer;
    }

    var dynamicPassword = function(btn, username, timer) {
        
        $.ajax({
            url : ctxPath + "/user/password/dynamic.json",
            type : 'GET',
            data : {
                username : username,
                status : $(btn).attr("status")
            },
            dataType : 'json',
            async : false,
            success : function(data) {
                //
                if (data.success == false) {
                    $(btn).css({
                        "background" : "#fff3e1",
                        "color" : "#f39800"
                    }).attr("value", '重新获取').removeAttr("disabled");
                    clearInterval(timer);
                    // message
                }else{
                    VERIFYCODE=data.verifyCode;
                }

            }
        })
    }

    var getVerifyCode = function(btn, mobile, timer) {
        $.ajax({
            url : ctxPath + "/ccp/get_verifyCode.json",
            type : 'GET',
            data : mobile,
            dataType : 'json',
            success : function(data) {
                //
                if (data.success == false) {
                    $(btn).css({
                        "background" : "#fff3e1",
                        "color" : "#f39800"
                    }).attr("value", '重新获取').removeAttr("disabled");
                    clearInterval(timer);
                    // message
                }

            }
        })
    }
    
    // 添加校验层
    $("input,select,textarea").not("[type=submit]").validation(); 
})

function isTheSameVerifyCode(mobile,verifyCode){
    $.ajax({
        url : ctxPath + "/ccp/isTheSame_VerifyCode.json",
        type : 'post',
        data : {
            mobile:mobile,
            verifyCode:verifyCode
        },
        dataType : 'json',
        async : false,
        success : function(data) {
            if (data.success) {
                isSame_VERIFYCODE=true;
            }
        }
    })
}
