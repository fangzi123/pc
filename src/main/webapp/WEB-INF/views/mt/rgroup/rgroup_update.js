// 校验
Activator.activeValidation("group_update_form",
// 校验字段
    {
        group_name: "required"

    },
// 消息描述
    {});


$(document).ready(function () {
    $("button[name='group_update_submit']").click(function () {
        var arr = new Array();
        var arr1 = new Array();
        $($("select").get(0)).find("option").each(function () {
            arr.push($(this).attr("resourceRoleId") + "");
        });
        $($("select").get(2)).find("option").each(function () {
            arr1.push($(this).attr("funcRoleId") + "");
        });
        $("input[name='rids']").val(arr.join(","));
        $("input[name='fids']").val(arr1.join(","));
        $("input[name='groupName']").val($("input[name='group_name']").val());
        $("form[id='group_update_form']").submit();
    });

    var fcandidate_resources = $("input[id='fcandidate_resources']");
    var fselected_resources = $("input[id='fselected_resources']");
    var rcandidate_resources = $("input[id='rcandidate_resources']");
    var rselected_resources = $("input[id='rselected_resources']");

    var fcandidateSetData = eval("(" + fcandidate_resources.val() + ")");
    var fselectedSetData = eval("(" + fselected_resources.val() + ")");
    var rcandidateSetData = eval("(" + rcandidate_resources.val() + ")");
    var rselectedSetData = eval("(" + rselected_resources.val() + ")");


    /** 菜单级联处理 **/
    $("select").each(function (i) {
        var selectComp = $(this);
        if (i == 0) {
            $(rselectedSetData).each(function () {
                if (this.resourceRoleId) {
                    //数据加入listbox
                    selectComp.append("<option resourceRoleId='" + this.resourceRoleId + "' >" + this.resourceRoleName + "</option>");
                }
            });
        } else if (i == 1) {
            $(rcandidateSetData).each(function () {
                if (this.resourceRoleId) {
                    //数据加入listbox
                    selectComp.append("<option resourceRoleId='" + this.resourceRoleId + "' >" + this.resourceRoleName + "</option>");
                }
            });
        } else if (i == 2) {
            $(fselectedSetData).each(function () {
                if (this.funcRoleId) {
                    //数据加入listbox
                    selectComp.append("<option funcRoleId='" + this.funcRoleId + "' >" + this.funcRoleName + "</option>");
                }
            });
        } else if (i == 3) {
            $(fcandidateSetData).each(function () {
                if (this.funcRoleId) {
                    //数据加入listbox
                    selectComp.append("<option funcRoleId='" + this.funcRoleId + "' >" + this.funcRoleName + "</option>");
                }
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
                    $($("select").get(0)).append("<option resourceRoleId='" + $(this).attr("resourceRoleId") + "' >" + $(this).text() + "</option>");
                    $(this).remove();
                });
            });
        } else if (i == 1) {// to right
            $(this).click(function () {
                $($("select").get(0)).find("option").each(function () {
                    if ($(this).attr("selected") == "selected") {
                        var text = $(this).text();
                        $($("select").get(1)).append("<option resourceRoleId='" + $(this).attr("resourceRoleId") + "' >" + text + "</option>");
                        $(this).remove();
                    }
                });
            });
        } else if (i == 2) {// to left
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
                    $($("select").get(2)).append("<option funcRoleId='" + $(this).attr("funcRoleId") + "' >" + $(this).text() + "</option>");
                    $(this).remove();
                });
            });
        } else if (i == 3) {// to right
            $(this).click(function () {
                $($("select").get(2)).find("option").each(function () {
                    if ($(this).attr("selected") == "selected") {
                        var text = $(this).text();
                        $($("select").get(3)).append("<option funcRoleId='" + $(this).attr("funcRoleId") + "' >" + text + "</option>");
                        $(this).remove();
                    }
                });
            });
        }
    });


//	var roleType = $("input[name='roleType']");
//	if(roleType.val() == "FUNC") {
//		$($("select").get(0)).parent("div").parent("div").attr("style", "display:none");
//	} else if(roleType.val() == "RESOURCE") {
//		$($("select").get(2)).parent("div").parent("div").attr("style", "display:none");
//	}

});
