// 校验
Activator.activeValidation("user_update_form",
// 校验字段
    {
        user_name: {
            required: true,
            minlength: 4,
            // 只允许字母，数字以及下划线
            alphanumeric: true

        },
        userPassword: {
            required: true,
            minlength: 6,
            // 只允许字母，数字以及下划线
            alphanumeric: true

        }

    },
// 消息描述
    {});


$(document).ready(function () {

    $("button[name='user_update_submit']").click(function () {
        var arr = new Array();
        $($("select").get(0)).find("option").each(function () {
            arr.push($(this).attr("rgroupId") + "");
        });
        $("input[name='gids']").val(arr.join(","));
        $("input[name='userName']").val($("input[name='user_name']").val());
        $("input[name='password']").val($("input[name='userPassword']").val());
        $("input[name='status']").val($("input[name='isLeaf']").val());


        $("form[id='user_update_form']").submit();
    });


    var isLeafInput = $("input[name='isLeaf']");
    $(isLeafInput).parent("div").find("button").each(function () {
        if ($(this).val() == isLeafInput.val()) {
            $(this).addClass("active");
        } else {
            $(this).removeClass("active");
        }
        $(this).click(function () {
            $(isLeafInput).val($(this).val());
        });
    });

    var candidate_resources = $("input[id='candidate_resources']");
    var selected_resources = $("input[id='selected_resources']");

    var candidateSetData = eval("(" + candidate_resources.val() + ")");
    var selectedSetData = eval("(" + selected_resources.val() + ")");


    /** 菜单级联处理 **/
    $("select").each(function (i) {
        var selectComp = $(this);
        if (i == 0) {
            $(selectedSetData).each(function () {
                if (this.rgroupId) {
                    //数据加入listbox
                    selectComp.append("<option rgroupId='" + this.rgroupId + "' >" + this.rgroupName + "</option>");
                }
            });
        } else if (i == 1) {
            $(candidateSetData).each(function () {
                if (this.rgroupId) {
                    //数据加入listbox
                    selectComp.append("<option rgroupId='" + this.rgroupId + "' >" + this.rgroupName + "</option>");
                }

            });
        }
    });


    /** 菜单加入/移除处理 **/
    $("button").each(function (i) {
        if (i == 2) {// to left
            $(this).click(function () {
                var allSel = $("select").filter(function (j) {
                    return j > 0 && j < 4;
                }).find("option").filter(function () {
                    return $(this).attr("selected") == "selected";
                });

                var ID = [];
                allSel.each(function () {
                    ID.push($(this).val());
                });
                allSel.each(function () {
                    $($("select").get(0)).append("<option rgroupId='" + $(this).attr("rgroupId") + "' >" + $(this).text() + "</option>");
                    $(this).remove();
                });
            });
        } else if (i == 3) {// to right
            $(this).click(function () {
                $($("select").get(0)).find("option").each(function () {
                    if ($(this).attr("selected") == "selected") {
                        var text = $(this).text();
                        $($("select").get(1)).append("<option rgroupId='" + $(this).attr("rgroupId") + "' >" + text + "</option>");
                        $(this).remove();
                    }
                });
            });
        }
    });

});
