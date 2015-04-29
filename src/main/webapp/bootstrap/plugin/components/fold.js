/** 折叠效果 - created by caobin * */
$.extend(Component, {

    fold: function (options) {
        /** IE:103%, FF:91.5% **/
        var config = {
            blockDiv: $("#" + options.blockId),
            foldBtn: $("#" + options.foldBtnId),
            initHide: options.initHide,
            collapseText: options.collapseText,
            expandText: options.expandText,
            //调节偏移量
            offsetPercent: options.offsetPercent || 0
        };
        with (config) {
            var btnWidth = $.browser.msie ? (103 - 0 + offsetPercent) + "%" : (91.5 - 0 + offsetPercent) + "%";
            //设定button长度
            foldBtn.css({width: btnWidth});
            //初始隐藏处理
            if (initHide) {
                blockDiv.addClass("collapse");
                foldBtn.append('<i class="icon-chevron-down"></i>&nbsp;' + expandText);
            } else {
                foldBtn.append('<i class="icon-chevron-up"></i>&nbsp;' + collapseText);
            }

            foldBtn.click(function () {
                if (blockDiv.hasClass("collapse")) {
                    blockDiv.removeClass("collapse");
                    foldBtn.html("");
                    foldBtn.append('<i class="icon-chevron-up"></i>&nbsp;' + collapseText);
                } else {
                    blockDiv.addClass("collapse");
                    foldBtn.html("");
                    foldBtn.append('<i class="icon-chevron-down"></i>&nbsp;' + expandText);
                }

            });
        }
    }
});
