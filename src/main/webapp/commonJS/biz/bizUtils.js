function BizUtils() {
}

BizUtils.prototype.setcaseselectval = function (s1, s2, s3, s4) {
};

BizUtils.prototype.setselectvalforbiz = function (s1, s2) {
    var trantypes = $('#' + s2).val();
    $('#' + s1).children().each(function (i, e) {
        if ($(e).val() === trantypes) {
            $(e).attr("selected", 'true');
        }
    });
};
BizUtils.prototype.setChangeReadonly = function (s1, s2) {
    $('#' + s1).change(function () {
        var optselectval = $(this).children('option:selected').val();
        if (optselectval === '0') {
            if ($('#' + s2).attr("readonly") !== "readonly") {
                $('#' + s2).attr("readonly", "readonly");
            }
        } else if (optselectval === '1') {
            if ($('#' + s2).attr("readonly") === "readonly") {
                $('#' + s2).removeAttr("readOnly");
            }
        }
    });
};

BizUtils.prototype.setReadonly = function (s1, s2) {
    var optselectval = $('#' + s1).children('option:selected').val();
    if (optselectval === '0') {
        if ($('#' + s2).attr("readonly") !== "readonly") {
            $('#' + s2).attr("readonly", "readonly");
        }
    } else if (optselectval === '1') {
        if ($('#' + s2).attr("readonly") === "readonly") {
            $('#' + s2).removeAttr("readOnly");
        }
    }
};

BizUtils.prototype.clear = function (formId, table) {
    var form = $("#" + formId);
    form.find(":text,select").each(function (i) {
        $(this).val("");
        $(this).parents('.control-group').removeClass('success');
        $(this).parents('.control-group').removeClass('error');
    });
    form.find("p").remove(".help-block");
    // 清空表格数据
    table.clearAll();
}

BizUtils.prototype.setLocale = function (id, language, defaultUrl) {
    var locale = 'locale=' + language;
    $("#" + id).click(function () {
        if (defaultUrl == undefined || url === '') {
            var url = location.href;

            var search = location.search;
            if (search === '') {
                url += '?' + locale;
            } else {
                if (search.indexOf('locale') < 0) {
                    url =
                        search.substring(0, 1) + locale + '&' +
                        search.substring(1);
                } else {
                    if (search.indexOf("&") > 0) {
                        //console.log(search.substring(14));
                        url =
                            search.substring(0, 1) + locale + '&' +
                            search.substring(14);
                    } else {
                        url = '?' + locale;
                    }
                }
            }

        } else {
            url = defaultUrl;
        }

        location.href = url;
    })


}

BizUtils.prototype.setLocalWidth = function () {
    if (langue == "zh") {
        BizUtils.loadjscssfile(contextBasePath + '/commonJS/jqValidation/messages_zh_CN.js', 'js');
        $("label[class*='control-label']").each(function () {
            $(this).removeClass("en_label");
            $(this).addClass("zh_label");
        });
        $("div[class*='controls']").each(function () {
            $(this).removeClass("en_controls");
            $(this).addClass("zh_controls");
        });

        $(".btn_margin").removeClass("en_btn_margin").addClass("zh_btn_margin");

    } else {
        BizUtils.loadjscssfile(contextBasePath + '/commonJS/jqValidation/messages_en_US.js', 'js');

        $("label[class*='control-label']").each(function () {
            $(this).removeClass("zh_label");
            $(this).addClass("en_label");
        });
        $("div[class*='controls']").each(function () {
            $(this).removeClass("zh_controls");
            $(this).addClass("en_controls");
        });

        $(".btn_margin").removeClass("zh_btn_margin").addClass("en_btn_margin");

    }
}

BizUtils.prototype.getMenuTitle = function (text) {
    var arr = text.split("|");
    if (arr.length == 1) return text;
    if (langue == "zh") {
        return arr[0]
    } else {
        return arr[1]
    }
}

BizUtils.prototype.loadjscssfile = function (filename, filetype) {

    if (filetype == "js") {
        var fileref = document.createElement('script');
        fileref.setAttribute("type", "text/javascript");
        fileref.setAttribute("src", filename);
    } else if (filetype == "css") {

        var fileref = document.createElement('link');
        fileref.setAttribute("rel", "stylesheet");
        fileref.setAttribute("type", "text/css");
        fileref.setAttribute("href", filename);
    }
    if (typeof fileref != "undefined") {
        document.getElementsByTagName("head")[0].appendChild(fileref);
    }

}

var BizUtils = new BizUtils();
