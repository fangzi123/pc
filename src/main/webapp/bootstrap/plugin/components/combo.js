/** Select数据加载 - created by caobin * */
$.extend(Component, {

    /**
     * @param options 选项
     **/
    comboBox: function (options) {
        var config = {
            combo: options.id ? $("#" + options.id) : null,
            //缓存id
            cache: options.cacheId ? $("#" + options.cacheId) : null,
            //{value:null, text:null}
            fieldMapping: options.fieldMapping
        };
        //获取缓存于页面的数据
        var data = $.evalJSON(config.cache.val());
        config.combo.append("<option value=''>全部</option>");
        $(data).each(function () {
            config.combo.append("<option value='" + this[config.fieldMapping.value] + "'>" + this[config.fieldMapping.text] + "</option>");
        });

    }
});
