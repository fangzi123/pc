// 激活器 - author: caobin
var Activator = {

    // 激活日历
    activeCalendar: function (divId, options) {
        $(document).ready(function () {
            $("#" + divId).datepicker(options);
        });
    },


    // 激活校验
    activeValidation: function (formId, rules, messages) {
        $(document).ready(function () {
            $("#" + formId).validate({
                rules: rules,

                messages: messages,

                errorClass: "help-block",

                errorElement: "p",

                errorPlacement: function (error, element) {
                    var control = element.parent("div");
                    // 最多进行5次迭代，以确认正确的controls-div位置
                    for (var i = 0; i < 5; i++) {
                        if (!control.hasClass("controls")) {
                            control = control.parent("div");
                        } else {
                            break;
                        }
                    }
                    // 5次迭代后仍未找到controls，则在第一层div后追加消息文本
                    if (!control.hasClass("controls")) {
                        control = element.parent("div");
                    }
                    error.appendTo(control);
                },

                highlight: function (element, errorClass, validClass) {
                    $(element).parents('.control-group').removeClass('success');
                    $(element).parents('.control-group').addClass('error');
                    //$(element).parents('.control-group').find("button").removeClass('btn-success');
                    //$(element).parents('.control-group').find("button").addClass('btn-danger');
                },
                unhighlight: function (element, errorClass, validClass) {
                    $(element).parents('.control-group').removeClass('error');
                    $(element).parents('.control-group').addClass('success');
                    //$(element).parents('.control-group').find("button").addClass('btn-success');
                    //$(element).parents('.control-group').find("button").removeClass('btn-danger');
                }

            });

        });
    }
};
