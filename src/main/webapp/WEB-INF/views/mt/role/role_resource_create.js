// 校验
Activator.activeValidation("role_resource_create_form",
// 校验字段
    {
        role_name: "required"

    },
// 消息描述
    {});

$(document).ready(function () {

    $("button[name='role_resource_submit']").click(function () {
        var arr = new Array();
        $($("select").get(0)).find("option").each(function () {
            arr.push($(this).attr("resourceId") + "");
        });
        $("input[name='ids']").val(arr.join(","));
        $("input[name='roleName']").val($("input[name='role_name']").val());
        $("form[id='role_resource_create_form']").submit();
    });


    var resources = $("input[name='resources']");

    var setData = eval("(" + resources.val() + ")");

    /** 菜单级联处理 **/
    $("select").each(function (i) {
        var selectComp = $(this);
        if (i == 1) {

            //初始化菜单数据
            var lv1Data = $.grep(setData, function (o) {
                return o;
            });
            $(lv1Data).each(function () {
                //数据加入listbox
                selectComp.append("<option resourceId='" + this.resourceId + "' >" + this.urlPattern + "</option>");
            });
        }
    });


    /** 菜单加入/移除处理 **/
    $("button").each(function (i) {
        if (i == 0) {// to left
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
                    $($("select").get(0)).append("<option resourceId='" + $(this).attr("resourceId") + "' >" + $(this).text() + "</option>");
                    $(this).remove();
                });
            });
        } else if (i == 1) {// to right
            $(this).click(function () {
                $($("select").get(0)).find("option").each(function () {
                    if ($(this).attr("selected") == "selected") {
                        var text = $(this).text();
                        $($("select").get(1)).append("<option resourceId='" + $(this).attr("resourceId") + "' >" + text + "</option>");
                        $(this).remove();
                    }
                });
            });
        }
    });

});
