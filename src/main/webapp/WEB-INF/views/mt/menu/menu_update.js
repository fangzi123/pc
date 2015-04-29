// 校验
Activator.activeValidation("menu_create_form",
// 校验字段
    {
        menuName: "required",
        userName: {
            required: true,
            minlength: 4,
            // 只允许字母，数字以及下划线
            alphanumeric: true

        },
        displayOrder: {
            required: true,
            number: true
        },
        url: "required"

    },
// 消息描述
    {});


var lvChange = function (obj) {
    var pmenuSel = $($("form").get(0)).find("select[name='pmenuId']");
    var menus = $($("form").get(0)).find("input[name='menus']");
    $(obj).find("option").each(function () {
        if ($(this).attr("selected")) {
            var lvOpVal = $(this).val();
            if (lvOpVal == '') {
                $($("button").get(0)).attr("disabled", "disabled");
                $($("button").get(1)).attr("disabled", "disabled");
                $($("form").get(0)).find("input[name='isLeaf']").val("0");


                $(pmenuSel.find("option").get(0)).attr("selected", "selected");
                pmenuSel.attr("disabled", "disabled");
                pmenuSel.removeAttr("required");
                $(pmenuSel).parents('.control-group').removeClass('error');
                $(pmenuSel).parents('.controls').children("p[class='help-block']").remove();
            } else {
                $($("button").get(0)).removeAttr("disabled");
                $($("button").get(1)).removeAttr("disabled");

                pmenuSel.removeAttr("disabled");
                pmenuSel.attr("required", "required");

                var setMenus = eval("(" + menus.val() + ")");

                pmenuSel.find("option").each(function () {
                    if ($(this).attr("value") != -1) {
                        $(this).remove();
                    }
                });

                $(setMenus).each(function () {
                    if ($(this).attr("lv") == lvOpVal && $("input[name='menuId']").val() != $(this).attr("menuId")) {
                        pmenuSel.append("<option value=" + $(this).attr("menuId") + ">" + $(this).attr("menuName") + "</option>");
                    }
                });
            }
        }
    });
};


$(document).ready(function () {
    var urlInput = $($("form").get(0)).find("input[name='url']");
    var lvSel = $($("form").get(0)).find("select[name='lv']");
    var isLeafInput = $($("form").get(0)).find("input[name='isLeaf']");
    var lvEdit = $($("form").get(0)).find("input[name='lvEdit']");
    var pmenuIdEdit = $($("form").get(0)).find("input[name='pmenuIdEdit']");

    //初始化动作Start
    $(isLeafInput).parent("div").find("button").each(function () {
        if ($(this).val() == isLeafInput.val()) {
            $(this).addClass("active");
            if ($(this).val() == '1') {
                urlInput.removeAttr("disabled");
            } else if ($(this).val() == '0') {
                urlInput.attr("disabled", "disabled");
            }
        } else {
            $(this).removeClass("active");
        }
    });
    lvSel.find("option").each(function () {
        var childLv = lvEdit.val() - 1;
        if ($(this).val() == childLv) {
            $(this).attr("selected", "selected");
            lvChange(lvSel);
            $("select[name='pmenuId']").find("option").each(function () {
                if ($(this).val() == pmenuIdEdit.val()) {
                    $(this).attr("selected", "selected");
                }
            });
        }
    });

    //初始化动作End

    $(isLeafInput).parent("div").find("button").each(function () {
        $(this).click(function () {
            $("input[name='isLeaf']").val($(this).val());
            if ($(this).val() == '1') {
                urlInput.removeAttr("disabled");
                urlInput.attr("required", "required");
            } else if ($(this).val() == '0') {
                urlInput.attr("value", "");
                urlInput.attr("disabled", "disabled");
                urlInput.removeAttr("required");

                $(urlInput).parents('.control-group').removeClass('error');
                $(urlInput).parents('.controls').children("p[class='help-block']").remove();
            }
        });
    });


    lvSel.change(function () {
        lvChange(this);
    });
});


		

 
