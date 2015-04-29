Activator.activeValidation("pwd_form",
    // 校验字段
    {
        newPasswd: {
            required: true,
            minlength: 6,
            maxlength: 6,
            // 只允许字母，数字以及下划线
            alphanumeric: true
        },
        newPasswdAgain: {
            required: true,
            minlength: 6,
            maxlength: 6,
            // 只允许字母，数字以及下划线
            alphanumeric: true,
            passwdConfirm: 'newPasswd'
        },
        oldPasswd: {
            required: true,
            minlength: 6,
            maxlength: 6,
            // 只允许字母，数字以及下划线
            alphanumeric: true,
            validatePwd: {
                url: ctx + "/user/validatePwd.json",
                data: {
                    username: function () {
                        return $("#username").val();
                    },
                    password: function () {
                        return $("#oldPasswd").val();
                    }
                }

            }
        }
    },
    // 消息描述
    {});

$(document).ready(function () {

    BizUtils.setLocalWidth();


    $("#passwd_submit").click(function () {
        $("#pwd_form").submit();
        $("p[class*='help-block']").each(function () {
            if (langue == "zh") {
                $(this).addClass("zh-block-help-position");
            } else {
                $(this).addClass("en-block-help-position");
            }
        });
    })

    $("#oldPasswd").blur(function () {

        $("p[class*='help-block']").each(function () {
            if (langue == "zh") {
                $(this).addClass("zh-block-help-position");
            } else {
                $(this).addClass("en-block-help-position");
            }
        });
        $("#old_passwd_id").css("display", "none");
    })

    $("#newPasswd").blur(function () {

        $("p[class*='help-block']").each(function () {
            if (langue == "zh") {
                $(this).addClass("zh-block-help-position");
            } else {
                $(this).addClass("en-block-help-position");
            }
        });
    })

    $("#newPasswdAgain").blur(function () {

        $("p[class*='help-block']").each(function () {
            if (langue == "zh") {
                $(this).addClass("zh-block-help-position");
            } else {
                $(this).addClass("en-block-help-position");
            }
        });
    })

})
