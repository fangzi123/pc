$(document).ready(function () {
    $("#example").tablecloth({
        formId: "exForm",
        theme: "default",
        striped: true,
        bordered: true,
        // 隐藏列,1或[1,3]的形式
        hideColumns: 1,
        // 搜索栏设置项
        searchBarConfig: {
            // 搜索工具条
            searchBar: true,
            // 检索字段-提交检索字段映射
            columnsMapping: {
                "菜单名称": "_search_menuName",
                "菜单层级": "_search_lv"
            },
            // 默认搜索字段
            defaultSearchingField: "菜单名称",
            // 绑定含有提交搜索数据的div id
            bindSubmitElement: "exSearchData"
        },

        // checkbox设置项
        checkboxConfig: {
            checkbox: true,
            // 是否单选
            singleCheck: true
        },

        //自定动作项
        customOperationConfig: [
            //跳转到新增
            {
                bindSubmitElement: "addSubmit",
                type: "add",
                action: function () {
                    $("input[name='subBtn']").val("add");
                    $("form").submit();
                }
            },
            //跳转到编辑
            {
                bindSubmitElement: "editSubmit",
                action: function (table, selectedData) {
                    $("input[name='subBtn']").val("update");
                    $("form").append("<input type='hidden' name='menuId' value='" + selectedData[0].col1 + "'/>");
                    $("form").submit();
                }
            }

        ],


        // 分页栏设置项
        paginationConfig: {
            // 分页工具栏
            paginationBar: true,
            // 绑定含有提交分页数据的div id
            bindSubmitElement: "exPageData"
        },

        //创建项设置
        deleteConfig: {
            // 可删除
            deletable: true,
            // 绑定提交按钮id
            bindSubmitElement: "delSubmit",
            templateContent: {
                action: "menu_read.html",
                operation: "s3005",
                pk: "delData"
            },
            // 字段name-列索引映射
            fieldsMapping: {
                "delData": "1"
            }
        }


    });
});
