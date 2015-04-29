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
                "资源匹配模式": "_search_url"
            },
            // 默认搜索字段
            defaultSearchingField: "资源匹配模式",
            // 绑定含有提交搜索数据的div id
            bindSubmitElement: "exSearchData"
        },

        // checkbox设置项
        checkboxConfig: {
            checkbox: true,
            // 是否单选
            singleCheck: true,
            // 绑定提交按钮id
            bindSubmitElement: "editSubmit",

            // 绑定提交列,1或[1,3]的形式
            bindSubmitColumns: 1
        },


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
                action: "resource_read.html",
                operation: "s2005",
                pk: "delData"
            },
            // 字段name-列索引映射
            fieldsMapping: {
                "delData": "1"
            }
        }


    });
});
