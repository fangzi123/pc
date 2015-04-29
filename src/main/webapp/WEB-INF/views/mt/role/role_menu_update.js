// 校验
Activator.activeValidation("role_menu_update_form",
// 校验字段
    {
        role_name: "required"

    },
// 消息描述
    {});

$(document).ready(function () {

    $("button[name='role_update_submit']").click(function () {
        var arr = new Array();
        $($("select").get(0)).find("option").each(function () {
            var ids = $(this).val().split("-");
            $(ids).each(function () {
                if ($.inArray(this + "", arr) < 0) {
                    arr.push(this + "");
                }
            });
        });
        $("input[name='mids']").val(arr.join(","));
        $("input[name='roleName']").val($("input[name='role_name']").val());

        $("form[id='role_menu_update_form']").submit();
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
                //数据加入listbox
                selectComp.append("<option lv='" + this.lv + "' isLeaf='" + this.isLeaf + "' value='" + this.menuCode + "'>" + this.menuName + "</option>");
            });
        } else if (i == 1) {
            //初始化一级菜单数据
            var lv1Data = $.grep(candidateSetData, function (o) {
                return o.lv == '1';
            });
            $(lv1Data).each(function () {
                //数据加入listbox
                selectComp.append("<option lv='" + this.lv + "' isLeaf='" + this.isLeaf + "' value='" + this.menuId + "'>" + this.menuName + "</option>");
            });
            selectComp.each(function () {
                $(this).change(function () {
                    $($("select").get(2)).find("option").remove();
                    $($("select").get(3)).find("option").remove();
                    var option = $(this);
                    var lv2Data = $.grep(candidateSetData, function (o) {
                        return o.lv == '2' && o.pmenuId == option.val();
                    });
                    var leftIDs = [];
                    $($("select").get(0)).find("option").each(function () {
                        leftIDs.push($(this).val().replace(/(\d+\-)+/g, ""));
                    });
                    $(lv2Data).each(function () {
                        if ($.inArray(this.menuId, leftIDs) < 0) {
                            $($("select").get(2)).append("<option lv='" + this.lv + "' isLeaf='" + this.isLeaf + "' value='" + this.menuId + "'>" + this.menuName + "</option>");
                        }
                    });
                    $($("select").get(2)).each(function () {
                        $(this).change(function () {
                            $($("select").get(3)).find("option").remove();
                            var option = $(this);
                            var lv3Data = $.grep(candidateSetData, function (o) {
                                return o.lv == '3' && o.pmenuId == option.val();
                            });
                            var leftIDs = [];
                            $($("select").get(0)).find("option").each(function () {
                                leftIDs.push($(this).val().replace(/(\d+\-)/g, ""));
                            });
                            $(lv3Data).each(function () {
                                if ($.inArray(this.menuId, leftIDs) < 0) {
                                    $($("select").get(3)).append("<option lv='" + this.lv + "' isLeaf='" + this.isLeaf + "' value='" + this.menuId + "'>" + this.menuName + "</option>")
                                }
                            });
                        });
                    });
                });
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
                    if ($(this).attr("isLeaf") == '1') {
                        $($("select").get(0)).append("<option lv='" + $(this).attr("lv") + "' isLeaf='1' value='" + ID.join("-") + "'>" + $(this).text() + "</option>");
                        $(this).remove();
                    }
                });
            });
        } else if (i == 1) {// to right
            $(this).click(function () {
                $($("select").get(0)).find("option").each(function () {
                    if ($(this).attr("selected") == "selected") {
                        var lv = $(this).attr("lv") - 1;
                        var val = $(this).val();
                        var text = $(this).text();
                        if (lv > 0) {
                            $($("select").get(lv)).find("option").each(function () {
                                if (val.indexOf($(this).val()) > -1 && $(this).attr("selected") == "selected") {
                                    $($("select").get(lv + 1)).append("<option lv='" + (lv + 1) + "' isLeaf='1' value='" + val.replace(/(\d+\-)/g, "") + "'>" + text + "</option>")
                                }
                            });
                        } else if (lv == 0) {
                            $($("select").get(lv + 1)).append("<option lv='" + (lv + 1) + "' isLeaf='1' value='" + val.replace(/(\d+\-)/g, "") + "'>" + text + "</option>")
                        }
                        $(this).remove();
                    }
                });
            });
        }
    });

});


