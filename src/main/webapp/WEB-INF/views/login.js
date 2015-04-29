// 校验
Activator.activeValidation("login_form",
// 校验字段
    {
        username: {
            required: true,
            minlength: 4,
            // 只允许字母，数字以及下划线
            alphanumeric: true

        },
        password: {
            required: true,
            minlength: 6,
            alphanumeric: true
        },
        captcha: {
            required: true,
            minlength: 4
        }
    },
// 消息描述
    {});

$(document).ready(function () {
    $("#captcha-img").attr("src", $("#captcha-hidden").val() + "/captcha.img?opt=handleCaptcha&rc=" + new Date().getTime());
    $("#captcha-img").click(function () {
        $(this).attr("src", $("#captcha-hidden").val() + "/captcha.img?opt=handleCaptcha&rc=" + new Date().getTime());
    });
});
