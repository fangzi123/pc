$.extend(Component, {
    /**
     * @param options 选项
     **/
    labelLoad: function (options) {
        var config = {
            //请求url
            baseUrl: options.baseUrl,
            //请求参数-{xx:"aa", yy:"bb"}
            baseParam: {},
            //主控件(input-text)
            parent: options.parentId ? $("#" + options.parentId) : null,
            //子控件
            child: options.childId ? $("#" + options.childId) : null,
            //回调
            callback: options.callback
        };

        config.parent.change(function () {
            var name = config.parent.attr("name");
            var value = config.parent.val();
            //add to base parameters.
            config.baseParam[name] = value;

            $('body').modalmanager('loading');
            $.post(config.baseUrl, config.baseParam, function (result) {
                try {
                    config.child.html("");
                    (config.callback)(result, config.child);
                } catch (e) {
                }
                //console.log($('body').hasClass("modal-open"));
                if ($('body').hasClass("modal-open")) {
                    $('body').modalmanager('loading');
                }
            }, "text");

        });

    },

    /**
     * @param options 选项
     **/
    cascade: function (options) {
        var _options = {
            //是否异步级联
            async: options.async == true,
            //级联主id
            parent: options.parentId ? $("#" + options.parentId) : null,
            //级联子id
            child: options.childId ? $("#" + options.childId) : null,
            //同步处理时的相关配置
            syncConfig: {
                //缓存数据id
                cache: options.syncConfig ? $("#" + options.syncConfig.cacheId) : null,
                //父控件过滤
                parentFilter: options.syncConfig ? options.syncConfig.parentFilter : null,
                //父控件Option映射,{value: null, text: null}
                parentOptionMapping: options.syncConfig ? options.syncConfig.parentMapping : null,
                //子控件Option映射,{value: null, text: null, ref:null}, ref-关联父控件的value
                childOptionMapping: options.syncConfig ? options.syncConfig.childMapping : null
            },
            //异步处理时的相关配置
            asyncConfig: {
                //父控件Url
                parentBaseUrl: options.asyncConfig ? options.asyncConfig.parentBaseUrl : null,
                //子控件Url
                childBaseUrl: options.asyncConfig ? options.asyncConfig.childBaseUrl : null,
                //父控件Option映射,{value: null, text: null}
                parentOptionMapping: options.asyncConfig ? options.asyncConfig.parentMapping : null,
                //子控件Option映射,{value: null, text: null}
                childOptionMapping: options.asyncConfig ? options.asyncConfig.childMapping : null,
                //请求参数名称
                baseParamName: {
                    type: "_type",//类型
                    pid: "_pid"//父ID
                }
            }
        };

        _options.async ? this._cascade_async(_options) : this._cascade_sync(_options);
    },

    _cascade_sync: function (_options) {
        var config = _options.syncConfig;
        var data = $.evalJSON(config.cache.val());
        //父控件操作
        var grepParentArray = (config.parentFilter)(data);
        _options.parent.append("<option value=''></option>");
        $(grepParentArray).each(function () {
            _options.parent.append("<option value='" + this[config.parentOptionMapping.value] + "'>" + this[config.parentOptionMapping.text] + "</option>");
        });
        //子控件操作
        _options.parent.change(function () {
            var seletedId = $(this).val();
            if (seletedId.length > 0) {
                var grepData = $.grep(data, function (n) {
                    return n[config.childOptionMapping.ref] - 0 == seletedId - 0;
                });
                _options.child.find("option").remove();
                _options.child.append("<option value=''></option>");
                $(grepData).each(function () {
                    _options.child.append("<option value='" + this[config.childOptionMapping.value] + "'>" + this[config.childOptionMapping.text] + "</option>");
                });
            }
        });
    },

    _cascade_async: function (_options) {
        var config = _options.asyncConfig;
        $('body').modalmanager('loading');
        $.post(config.parentBaseUrl, $.parseJSON('{"' + config.baseParamName.type + '":"parent"}'), function (result) {
            var data = $.evalJSON(result);
            _options.parent.append("<option value=''></option>");
            $(data).each(function () {
                _options.parent.append("<option value='" + this[config.parentOptionMapping.value] + "'>" + this[config.parentOptionMapping.text] + "</option>");
            });
            $('body').modalmanager('loading');
        }, "text");
        //子控件操作
        _options.parent.change(function () {
            var seletedId = $(this).val();
            if (seletedId.length > 0) {
                $('body').modalmanager('loading');
                $.post(config.childBaseUrl, $.parseJSON('{"' + config.baseParamName.type + '":"child", "' + config.baseParamName.pid + '":"' + seletedId + '"}'), function (result) {
                    var data = $.evalJSON(result);
                    _options.child.find("option").remove();
                    _options.child.append("<option value=''></option>");
                    $(data).each(function () {
                        _options.child.append("<option value='" + this[config.childOptionMapping.value] + "'>" + this[config.childOptionMapping.text] + "</option>");
                    });
                    $('body').modalmanager('loading');
                }, "text");
            }
        });

    }
});
