// tablecloth.js
// copyright brian sewell
// https://github.com/bwsewell/tablecloth
//
// v1.0.0
// May 4, 2012 14:20
// extended by caobin 2013-01

(function ($) {
    $.fn.clearAll = function () {
        this.find("td").remove();
        var footbar = this.next("div[class=row]");
        $(footbar.find("div").get(0)).find("div").html("没有找到符合条件的记录");
        $(footbar.find("div").get(2)).find("li").each(function (i) {
            if (!$(this).hasClass("prev") && !$(this).hasClass("next")) {
                if (i == 2) {
                    if (!$(this).hasClass("active")) {
                        $(this).addClass("active");
                    }
                } else {
                    $(this).remove();
                }
            } else {
                if (!$(this).hasClass("disabled")) {
                    $(this).addClass("disabled");
                }
            }
        });
    };

    $.fn.tablecloth = function (options) {

        var defaults = {
            formId: null,
            theme: "default", // "none","default","stats"
            customClass: "",
            bordered: false,
            condensed: false,
            striped: false,
            sortable: false,
            clean: false,
            cleanElements: "*",
            //以下属性进行功能扩展 - by caobin
            hideColumns: null,

            searchBarConfig: {
                // 检索工具条
                searchBar: false,

                //检索字段-提交检索字段映射
                columnsMapping: null,

                // 默认搜索字段
                defaultSearchingField: null,

                // 绑定含有提交搜索数据的div id
                bindSubmitElement: null
            },


            // checkbox设置项
            checkboxConfig: {
                checkbox: false,
                singleCheck: true,
                //绑定提交按钮id
                bindSubmitElement: null,
                //绑定提交url
                //bindSubmitUrl : null,
                //绑定提交列,1或[1,3]的形式
                bindSubmitColumns: null
            },

            // 分页栏设置项
            paginationConfig: {
                // 分页工具栏
                paginationBar: false,
                // 绑定含有提交分页数据的div id
                bindSubmitElement: null
            },

            //创建新记录设置项
            createConfig: {
                // 可创建新记录
                creatable: false,
                // 绑定提交按钮id
                bindSubmitElement: null,
                // 模板url配置
                template: null,
                // 附加于模板的js
                attachedTplJs: null
            },

            // 编辑设置项
            editConfig: {
                // 可编辑dataTable
                editable: false,
                // 模板url配置
                template: null,
                // 附加于模板的js
                attachedTplJs: null,
                // 绑定提交按钮id
                bindSubmitElement: null,
                // 字段name-列索引映射
                fieldsMapping: null
            },
            //创建项设置
            deleteConfig: {
                // 可删除
                deletable: false,
                // 绑定提交按钮id
                bindSubmitElement: null,
                // 模板url配置
                template: null,
                //
                templateContent: null,
                // 字段name-列索引映射
                fieldsMapping: null
            },
            //创建自定项
            customOperationConfig: null

            //[{bindSubmitElement:null, type:null, action:null}]

        };

        var opts = $.extend(defaults, options);


        // Before we remove any attributes, let's fix a few things up
        this.find("*").each(function () {
            if ($(this).attr("align") == "right") {
                $(this).addClass("right");
            }
            if ($(this).attr("align") == "center") {
                $(this).addClass("center");
            }
            if ($(this).attr("nowrap")) {
                $(this).css('white-space', 'nowrap');
            }
        });


        //增加与上层组件距离 - add by caobin
        $(this).css({"margin-top": "10px", "margin-bottom": "10px"});


        //自定义功能按钮
        if (opts.customOperationConfig) {
            if (!$.isArray(opts.customOperationConfig)) {
                opts.customOperationConfig = [opts.customOperationConfig];
            }
            var table = $(this);
            for (var i = 0; i < opts.customOperationConfig.length; i++) {
                var o = opts.customOperationConfig[i];
                $("#" + o.bindSubmitElement).click([o], function (callback) {
                    var storage = [];
                    var checkboxs = $(table).find("tr>td>input:checkbox");
                    var trs = $(table).find("tbody>tr");
                    if (checkboxs) {
                        storage = [];
                        var hasChecked = false;
                        $(checkboxs).each(function (i) {
                            var line = {};
                            if ($(this).attr("checked")) {
                                hasChecked = true;
                                $(trs[i]).children().each(function (j) {
                                    if (j == 0)return;
                                    line["col" + j] = $(this).html();
                                });
                                storage.push(line);
                            }

                        });

                        if (!hasChecked && callback.data[0].type != "add") {

                            bootbox.alert("请选择一条记录");
                            return;
                        }
                        (callback.data[0].action)(table, storage);


                    }
                });

            }
        }


        //添加删除功能
        if (opts.deleteConfig && opts.deleteConfig.deletable) {
            if (opts.deleteConfig.bindSubmitElement && opts.checkboxConfig && opts.checkboxConfig.singleCheck) {
                var table = $(this);
                $("#" + opts.deleteConfig.bindSubmitElement).click(function () {
                    var hasChecked = false;
                    $(table).find("tr>td>input:checkbox").each(function (i) {
                        if ($(this).attr("checked")) {
                            hasChecked = true;
                            if (opts.deleteConfig.template == null && opts.deleteConfig.templateContent != null) {
                                var o = opts.deleteConfig.templateContent;
                                bootbox.dialog('<form action="' + o.action + '" method="post"><p>删除选中记录？</p><input type="hidden" name="opt" value="' + o.operation + '"/><input name="' + o.pk + '" type="hidden" /></form>', [{
                                    "label": "确定",
                                    "class": "btn-success",
                                    "callback": function () {
                                        if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
                                            var pageSize = $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='pageSize']").val();
                                            $(".bootbox").find("form").append("<input name='pageSize' type='hidden' value='" + pageSize + "'/>");
                                            $(".bootbox").find("form").append("<input name='page' type='hidden' value='1'/>");
                                        }
                                        if (opts.searchBarConfig && opts.searchBarConfig.searchBar) {
                                            $(".bootbox").find("form").append("<input name='" +
                                            $(table).prev().find("input").attr("name") + "' type='hidden' value='" +
                                            $(table).prev().find("input").val() + "'/>");
                                        }
                                        $(".bootbox").find("form").submit();
                                    }
                                }, {
                                    "label": "取消",
                                    "callback": function () {
                                        $(".bootbox").find("form").remove();

                                    }
                                }]);
                                $(table).find("tbody>tr").eq(i).find("td").each(function (j) {
                                    if (opts.deleteConfig.fieldsMapping) {
                                        for (var name in opts.deleteConfig.fieldsMapping) {
                                            if (j == opts.deleteConfig.fieldsMapping[name] - 0) {
                                                var control = null;
                                                if ((control = $(".bootbox").find("input[name=" + name + "]").get(0)) != null) {
                                                    $(control).val($(this).html());
                                                    //radio, checkbox等可继续扩展
                                                } else if ((control = $(".bootbox").find("select[name=" + name + "]").get(0)) != null) {
                                                    var html = $(this).html();
                                                    $(control).find("option").each(function () {
                                                        if ($.trim(html) == $(this).html()) {
                                                            $(this).attr("selected", true);
                                                        }
                                                    });
                                                }

                                            }
                                        }
                                    }
                                });
                            } else {
                                $.post(opts.deleteConfig.template, null, function (data) {
                                    bootbox.dialog(data, [{
                                        "label": "确定",
                                        "class": "btn-success",
                                        "callback": function () {
                                            if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
                                                var pageSize = $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='pageSize']").val();
                                                $(".bootbox").find("form").append("<input name='pageSize' type='hidden' value='" + pageSize + "'/>");
                                                $(".bootbox").find("form").append("<input name='page' type='hidden' value='1'/>");
                                            }
                                            if (opts.searchBarConfig && opts.searchBarConfig.searchBar) {
                                                $(".bootbox").find("form").append("<input name='" +
                                                $(table).prev().find("input").attr("name") + "' type='hidden' value='" +
                                                $(table).prev().find("input").val() + "'/>");
                                            }
                                            $(".bootbox").find("form").submit();
                                        }
                                    }, {
                                        "label": "取消",
                                        "callback": function () {
                                        }
                                    }]);
                                    $(table).find("tbody>tr").eq(i).find("td").each(function (j) {
                                        if (opts.deleteConfig.fieldsMapping) {
                                            for (var name in opts.deleteConfig.fieldsMapping) {
                                                if (j == opts.deleteConfig.fieldsMapping[name] - 0) {
                                                    var control = null;
                                                    if ((control = $(".bootbox").find("input[name=" + name + "]").get(0)) != null) {
                                                        $(control).val($(this).html());
                                                        //radio, checkbox等可继续扩展
                                                    } else if ((control = $(".bootbox").find("select[name=" + name + "]").get(0)) != null) {
                                                        var html = $(this).html();
                                                        $(control).find("option").each(function () {
                                                            if ($.trim(html) == $(this).html()) {
                                                                $(this).attr("selected", true);
                                                            }
                                                        });
                                                    }

                                                }
                                            }
                                        }
                                    });
                                }, "html");
                            }
                        }
                    });
                    if (!hasChecked) {
                        bootbox.alert("请选择一条记录");
                        return;
                    }
                });
            }
        }

        //添加新建功能
        if (opts.createConfig && opts.createConfig.creatable) {
            $("#" + opts.createConfig.bindSubmitElement).click(function () {
                $.post(opts.createConfig.template, null, function (data) {
                    bootbox.dialog(data, [{
                        "label": "确定",
                        "class": "btn-success",
                        "callback": function () {
                            if (!$(".bootbox").find("form").valid())return false;
                            if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
                                $(".bootbox").find("form").append("<input name='pageSize' type='hidden' value='" +
                                $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='pageSize']").val() + "'/>");
                                $(".bootbox").find("form").append("<input name='page' type='hidden' value='" +
                                $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='page']").val() + "'/>");
                            }
                            if (opts.searchBarConfig && opts.searchBarConfig.searchBar) {
                                $(".bootbox").find("form").append("<input name='" +
                                $(table).prev().find("input").attr("name") + "' type='hidden' value='" +
                                $(table).prev().find("input").val() + "'/>");
                            }
                            $(".bootbox").find("form").submit();
                        }

                    }, {
                        "label": "取消",
                        "callback": function () {
                        }
                    }]);

                    if (opts.createConfig.attachedTplJs) {
                        //执行附加于模板上的js
                        (opts.createConfig.attachedTplJs)();
                    }
                }, "html");
            });
        }

        //添加编辑功能
        if (opts.editConfig && opts.editConfig.editable) {
            if (opts.editConfig.bindSubmitElement && opts.checkboxConfig && opts.checkboxConfig.singleCheck) {
                var table = $(this);
                $("#" + opts.editConfig.bindSubmitElement).click(function () {
                    var hasChecked = false;
                    $(table).find("tr>td>input:checkbox").each(function (i) {
                        if ($(this).attr("checked")) {
                            hasChecked = true;


                            $.post(opts.editConfig.template, null, function (data) {
                                bootbox.dialog(data, [{
                                    "label": "确定",
                                    "class": "btn-success",
                                    "callback": function () {
                                        if (!$(".bootbox").find("form").valid())return false;
                                        if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
                                            $(".bootbox").find("form").append("<input name='pageSize' type='hidden' value='" +
                                            $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='pageSize']").val() + "'/>");
                                            $(".bootbox").find("form").append("<input name='page' type='hidden' value='" +
                                            $("#" + opts.paginationConfig.bindSubmitElement).find("input[name='page']").val() + "'/>");
                                        }
                                        if (opts.searchBarConfig && opts.searchBarConfig.searchBar) {
                                            $(".bootbox").find("form").append("<input name='" +
                                            $(table).prev().find("input").attr("name") + "' type='hidden' value='" +
                                            $(table).prev().find("input").val() + "'/>");
                                        }
                                        $(".bootbox").find("form").submit();
                                    }
                                }, {
                                    "label": "取消",
                                    "callback": function () {
                                    }
                                }]);
                                $(table).find("tbody>tr").eq(i).find("td").each(function (j) {
                                    if (opts.editConfig.fieldsMapping) {
                                        for (var name in opts.editConfig.fieldsMapping) {
                                            if (j == opts.editConfig.fieldsMapping[name] - 0) {
                                                var control = null;
                                                if ((control = $(".bootbox").find("input[name=" + name + "]").get(0)) != null) {
                                                    $(control).val($(this).html());
                                                    //radio, checkbox等可继续扩展
                                                } else if ((control = $(".bootbox").find("select[name=" + name + "]").get(0)) != null) {
                                                    var html = $(this).html();
                                                    $(control).find("option").each(function () {
                                                        if ($.trim(html) == $(this).html()) {
                                                            $(this).attr("selected", true);
                                                        }
                                                    });
                                                }

                                            }
                                        }
                                    }
                                });
                                if (opts.editConfig.attachedTplJs) {
                                    //执行附加于模板上的js
                                    (opts.editConfig.attachedTplJs)();
                                }
                            }, "html");


                        }
                    });
                    if (!hasChecked) {
                        bootbox.alert("请选择一条记录");
                        return;
                    }
                });
            }
        }


        //添加分页栏
        if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
            //最多分页按钮个数
            var maxPagedBtn = 5;
            $(this).after(
                '<div class="row">' +
                '<div class="span7">' +
                '<div></div>' +
                '</div>' +
                '<div class="span5 pull-right">' +
                '<div class="pagination pagination-small pull-right" style="margin-top:-3px;">' +
                '<ul>' +
                '<li class="prev"><a href="#">首页</a></li>' +
                '<li class="prev"><a href="#">上页</a></li>' +
                '<li class="next"><a href="#">下页</a></li>' +
                '<li class="next"><a href="#">末页</a></li>' +
                '</ul>' +
                '</div>' +
                '</div>' +
                '</div>'
            );
//			/["+ currentPageBtn +"页]
            if (opts.paginationConfig.bindSubmitElement) {
                var pageData = $("#" + opts.paginationConfig.bindSubmitElement);

                var inputs = $(pageData).find("input");
                var totalSize = $($(inputs).get(0)).val();
                var pageSize = $($(inputs).get(1)).val();
                var page = $($(inputs).get(2)).val();

                //当前需要的分页按钮个数
                var currentPageBtn = Math.ceil(totalSize / pageSize);

                //替换分页显示信息
                var displayinfo = $($(this).next(".row").find(".span7").get(0)).children("div");

                var infoDetail = totalSize > 0 ? "共"
                + totalSize + "条信息" : "没有找到符合条件的记录";

                infoDetail += (totalSize > 0 ? (" | 每页显示 " +
                "<select style='width:65px;height:27px;'>" +
                "<option value='10'>10</option>" +
                "<option value='20'>20</option>" +
                "<option value='30'>30</option>" +
                "<option value='40'>40</option>" +
                "<option value='50'>50</option>" +
                "</select>"
                + " 条 | 第" + page + "/" + (currentPageBtn == 0 || currentPageBtn.toString() == "NaN" ? 1 : currentPageBtn) + "页") : "");
                //$($(this).next(".row").find(".span2").get(0)).children("div").html("第"+ page +"/"+ (currentPageBtn == 0 || currentPageBtn.toString() == "NaN" ? 1 : currentPageBtn) +"页");


                $(displayinfo).html(infoDetail);

                $(this).next(".row").find("select").val($("input[name=pageSize]").val());

                $(this).next(".row").find("select").change(function () {
                    $("input[name=pageSize]").val($(this).val());
                    $(pageData).find("input[name='page']").val(1);
                    $("#" + opts.formId).submit();
                });


                //分页按钮控制
                //获取追加点
                var appendPoint = $(this).next(".row").find("li").get(1);

                if (currentPageBtn < 2) {//1
                    $(appendPoint).after('<li class="active"><a href="#">1</a></li>');
                    $(this).next(".row").find(".prev").addClass("disabled");
                    $(this).next(".row").find(".next").addClass("disabled");
                } else if (currentPageBtn > 1 && currentPageBtn <= maxPagedBtn) {//currentPageBtn
                    if (page == 1) {
                        $(this).next(".row").find(".prev").addClass("disabled");
                    }
                    if (page == currentPageBtn) {
                        $(this).next(".row").find(".next").addClass("disabled");
                    }
                    for (var k = 0; k < currentPageBtn; k++) {
                        if (currentPageBtn + 1 - page == k + 1) {
                            appendPoint = $(appendPoint).after('<li class="active"><a href="#">' + (currentPageBtn - k) + '</a></li>');
                        } else {
                            appendPoint = $(appendPoint).after('<li><a href="#">' + (currentPageBtn - k) + '</a></li>');
                        }
                    }

                    for (var g = 0; g < currentPageBtn; g++) {
                        $($(this).next(".row").find("li").get(2 + g)).find("a").click(function () {
                            if (!$(this).parent("li").hasClass("active")) {
                                $(pageData).find("input[name='page']").val($(this).html());
                                $("#" + opts.formId).submit();
                            }
                        });
                    }
                    //首页/前一页设置
                    $($(this).next(".row").find(".prev").get(0)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(1);
                            $("#" + opts.formId).submit();
                        }
                    });

                    $($(this).next(".row").find(".prev").get(1)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(page - 1);
                            $("#" + opts.formId).submit();
                        }
                    });

                    //末页/后一页设置
                    $($(this).next(".row").find(".next").get(1)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(currentPageBtn);
                            $("#" + opts.formId).submit();
                        }
                    });

                    $($(this).next(".row").find(".next").get(0)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(page - 0 + 1);//-0过程保证page为数字类型再进行运算
                            $("#" + opts.formId).submit();
                        }
                    });

                } else if (currentPageBtn > maxPagedBtn) {//maxPagedBtn
                    if (page == 1) {
                        $(this).next(".row").find(".prev").addClass("disabled");
                    }
                    if (page == currentPageBtn) {
                        $(this).next(".row").find(".next").addClass("disabled");
                    }
                    for (var k = 0; k < maxPagedBtn; k++) {
                        if (maxPagedBtn + 1 - page == k + 1) {
                            appendPoint = $(appendPoint).after('<li class="active"><a href="#">' + (maxPagedBtn - k) + '</a></li>');
                        } else {
                            appendPoint = $(appendPoint).after('<li><a href="#">' + (maxPagedBtn - k) + '</a></li>');
                        }
                    }


                    if (page > Math.ceil(maxPagedBtn / 2)) {
                        //所有翻页数字前移
                        var moveLock = false;
                        var _table = $(this);
                        $(this).next(".row").find("li").each(function (i) {
                            if (i > 1 && i < maxPagedBtn + 2) {
                                if (i == 2 + Math.ceil(maxPagedBtn / 2) && page > currentPageBtn - Math.ceil(maxPagedBtn / 2) + 1) {
                                    moveLock = true;
                                    $(_table).next(".row").find("li").each(function (j) {
                                        if (j > 1 && j < maxPagedBtn + 2) {
                                            $(this).find("a").html(currentPageBtn - maxPagedBtn + 1 + j - 2);
                                            currentPageBtn - maxPagedBtn + 1 + j - 2 == page ? $(this).addClass("active") : $(this).removeClass("active");
                                        }
                                    });
                                }
                                if (!moveLock) {
                                    var posMove = $(this).find("a").html() - 0 + (page - 0 + Math.floor(maxPagedBtn / 2) - maxPagedBtn);
                                    $(this).find("a").html(posMove);
                                    i == 2 + Math.floor(maxPagedBtn / 2) ? $(this).addClass("active") : $(this).removeClass("active");
                                }
                            }
                        });
                    }

                    for (var m = 0; m < maxPagedBtn; m++) {
                        $($(this).next(".row").find("li").get(2 + m)).find("a").click(function () {
                            if (!$(this).parent("li").hasClass("active")) {
                                $(pageData).find("input[name='page']").val($(this).html());
                                $("#" + opts.formId).submit();
                            }
                        });
                    }
                    //首页/前一页设置
                    $($(this).next(".row").find(".prev").get(0)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(1);
                            $("#" + opts.formId).submit();
                        }
                    });

                    $($(this).next(".row").find(".prev").get(1)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(page - 1);
                            $("#" + opts.formId).submit();
                        }
                    });

                    //末页/后一页设置
                    $($(this).next(".row").find(".next").get(1)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(currentPageBtn);
                            $("#" + opts.formId).submit();
                        }
                    });

                    $($(this).next(".row").find(".next").get(0)).find("a").click(function () {
                        if (!$(this).parent("li").hasClass("disabled")) {
                            $(pageData).find("input[name='page']").val(page - 0 + 1);//-0过程保证page为数字类型再进行运算
                            $("#" + opts.formId).submit();
                        }
                    });
                }

            }


        }


        //添加搜索工具条
        if (opts.searchBarConfig && opts.searchBarConfig.searchBar) {
            var cols = [];
            //var defaultKey = "全文搜索";
            var defaultSearchField = opts.searchBarConfig.defaultSearchingField;

            $(this)
                .before('<div class="pull-right input-prepend input-append">' +
                '<span class="btn-group">' +
                '<button class="btn btn-info dropdown-toggle" data-toggle="dropdown">' +
                defaultSearchField +
                '<span class="caret"></span></button>' +
                '<ul class="dropdown-menu"></ul>' +
                '</span>' +
                    //'<div class="input-append">' +
                '<input type="text" name="' + opts.searchBarConfig.columnsMapping[defaultSearchField] + '" class="span2">' +
                    //'<span class="add-on"><i class="icon-remove"></i></span>' +
                    //'</div>' +
                '<button type="submit" class="btn">搜索</button>' +
                '</div>');


            if (opts.paginationConfig && opts.paginationConfig.paginationBar) {
                $($(this).prev().find("button").get(1)).click(function () {
                    $(pageData).find("input[name='page']").val(1);
                });
            }


            if (opts.searchBarConfig.columnsMapping) {
                opts.searchBarConfig.columnsMapping;
                for (var key in opts.searchBarConfig.columnsMapping) {
                    cols.push(key);
                }

                for (var i = 0; i < cols.length; i++) {
                    $(this).prev().find("ul").append('<li><a href="#">' + cols[i] + '</a></li>');
                }

                var tableEl = $(this);
                $(this).prev().find("ul>li").each(function () {
                    $(this).find("a").click(function () {
                        $(tableEl.prev().find("button").get(0)).html($(this).html() + '<span class="caret"></span>');
                        tableEl.prev().find("input").attr("name", opts.searchBarConfig.columnsMapping[$(this).html()]);
                        tableEl.prev().find("input").val("");
                    });
                });
            }

            if (opts.searchBarConfig.bindSubmitElement) {
                var tableEl = $(this);
                $("#" + opts.searchBarConfig.bindSubmitElement).find("input").each(function () {
                    if ($(this).attr("name") == "search_key") {
                        if ($(this).val() != null && $.trim($(this).val()).length != 0) {
                            var selectedLabel = null;
                            for (var key in opts.searchBarConfig.columnsMapping) {
                                if (opts.searchBarConfig.columnsMapping[key] == $(this).val()) {
                                    selectedLabel = key;
                                    break;
                                }
                            }
                            $(tableEl.prev().find("button").get(0)).html(selectedLabel + '<span class="caret"></span>');
                            tableEl.prev().find("input").attr("name", opts.searchBarConfig.columnsMapping[selectedLabel]);
                        }
                    } else if ($(this).attr("name") == "search_value") {
                        tableEl.prev().find("input").val($(this).val());
                    }
                });
            }

        }

        // 列隐藏功能
        if (opts.hideColumns) {
            var trs = $(this).find("tr");
            $(trs).each(function (i) {
                if (!$.isArray(opts.hideColumns)) {
                    opts.hideColumns = [opts.hideColumns];
                }
                for (var j = 0; j < opts.hideColumns.length; j++) {
                    var targetTdTh = $(this).find("td,th")[opts.hideColumns[j] - 1];
                    if (targetTdTh) {
                        $(targetTdTh).css("display", "none");
                    }
                }
            });
        }

        // 扩展checkbox功能  - add by caobin
        if (opts.checkboxConfig && opts.checkboxConfig.checkbox) {
            var trs = $(this).find("tr");
            $(trs).each(function (i) {
                if (i == 0) {
                    if (opts.checkboxConfig.singleCheck) {
                        $(this).prepend("<th style='width:5px;'></th>");
                    } else {
                        $(this).prepend("<th style='width:5px;'><input _name='_chk_" + i + "' type='checkbox'/></th>");
                    }
                } else {
                    $(this).prepend("<td style='width:5px;'><input _name='_chk_" + i + "' type='checkbox'/></td>");
                }
            });
            if (opts.checkboxConfig.singleCheck) {
                var checkboxs = $(this).find("tr>td>input:checkbox");
                if (checkboxs) {
                    //单选事件注册
                    $(checkboxs).click(function () {
                        var _name = $(this).attr("_name");
                        if ($(this).attr("checked")) {
                            $(checkboxs).each(function (i) {
                                if ($(this).attr("_name") != _name) {
                                    $(this).attr("checked", false);
                                    $(this).parent("td").parent("tr").children().each(function () {
                                        $(this).css({background: ""});
                                    });
                                } else {
                                    $(this).parent("td").parent("tr").children().each(function () {
                                        $(this).css({background: "#B5DAE3"});
                                    });
                                }
                            });
                        } else {
                            $(this).parent("td").parent("tr").children().each(function () {
                                $(this).css({background: ""});
                            });
                        }
                    });
                }
            } else {
                var allchkbox = $(this).find("tr>th>input:checkbox");
                var checkboxs = $(this).find("tr>td>input:checkbox");
                if (checkboxs && allchkbox) {
                    //全选事件注册
                    $(allchkbox).click(function () {
                        if ($(this).attr("checked")) {
                            checkboxs.attr("checked", true);
                            $(checkboxs).each(function () {
                                $(this).parent("td").parent("tr").children().each(function () {
                                    $(this).css({background: "#B5DAE3"});
                                });
                            });

                        } else {
                            checkboxs.attr("checked", false);
                            $(checkboxs).each(function () {
                                $(this).parent("td").parent("tr").children().each(function () {
                                    $(this).css({background: ""});
                                });
                            });
                        }
                    });
                    //针对每个checkbox进行注册处理
                    $(checkboxs).each(function () {
                        $(this).click(function () {
                            if ($(this).attr("checked")) {
                                $(this).parent("td").parent("tr").children().each(function () {
                                    $(this).css({background: "#B5DAE3"});
                                });
                            } else {
                                $(this).parent("td").parent("tr").children().each(function () {
                                    $(this).css({background: ""});
                                });
                            }
                        });
                    });
                }
            }

            //checkbox 选中数据处理
            if (opts.checkboxConfig.bindSubmitElement) {
                var storage = [];
                var checkboxs = $(this).find("tr>td>input:checkbox");
                var trs = $(this).find("tbody>tr");
                if (checkboxs) {
                    $("#" + opts.checkboxConfig.bindSubmitElement).click(function () {
                        storage = [];
                        var hasChecked = false;
                        $(checkboxs).each(function (i) {
                            var line = {};
                            if ($(this).attr("checked")) {
                                hasChecked = true;
                                $(trs[i]).children().each(function (j) {
                                    if (j == 0)return;
                                    if (opts.checkboxConfig.bindSubmitColumns) {
                                        if (!$.isArray(opts.checkboxConfig.bindSubmitColumns)) {
                                            opts.checkboxConfig.bindSubmitColumns = [opts.checkboxConfig.bindSubmitColumns];
                                        }
                                        if ($.inArray(j, opts.checkboxConfig.bindSubmitColumns) == -1)return;
                                    }
                                    line["col" + j] = $(this).html();
                                });
                                storage.push(line);
                            }

                        });
                        if (!hasChecked) {
                            bootbox.alert("请选择一条记录");
                            return;
                        }
                        $("#" + opts.formId).append("<input name='_storage' type='hidden' value='" + $.toJSON({'_storage': storage}) + "'/>");
                        $("#" + opts.formId).submit();
                    });
                }
            }
        }


        // Get rid of all inline styling and deprecated table attributes
        // Also get rid of any current classes being applied to the <table>
        // element
        if (opts.clean) {

            this.removeAttr('class').removeAttr('style')
                .removeAttr('cellpadding').removeAttr('cellspacing')
                .removeAttr('bgcolor').removeAttr('align')
                .removeAttr('width').removeAttr('nowrap');

            this.find(opts.cleanElements).each(function () {
                $(this).removeAttr('style').removeAttr('cellpadding')
                    .removeAttr('cellspacing').removeAttr('bgcolor')
                    .removeAttr('align').removeAttr('width')
                    .removeAttr('nowrap');
            });

        }

        // Set the table theme accordingly
        if (opts.theme == "default") {
            this.addClass("table");
        } else if (opts.theme == "dark") {
            this.addClass("table table-dark");
        } else if (opts.theme == "stats") {
            this.addClass("table table-stats");
        } else if (opts.theme == "paper") {
            this.addClass("table table-paper");
        }

        // Set the table theme accordingly
        if (opts.customClass != "") {
            this.addClass(opts.customClass);
        }

        // Set the table options accordingly
        if (opts.condensed) {
            this.addClass("table-condensed");
        }
        if (opts.bordered) {
            this.addClass("table-bordered");
        }
        if (opts.striped) {
            this.addClass("table-striped");
        }
        if (opts.sortable) {
            this.addClass("table-sortable");
            if (jQuery().tablesorter) {
                this.tablesorter({
                    cssHeader: "headerSortable"
                });
            } else {
                console.log('Tablesorter is not loaded');
            }
        }

    };

})(jQuery);
