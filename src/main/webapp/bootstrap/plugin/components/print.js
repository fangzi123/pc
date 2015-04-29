$.extend(Component, {
    print: function (options) {
        var config = {
            baseUrl: options.baseUrl,
            //{xx:"11",yy:"22"}
            baseParam: options.baseParam
        };

        var hiddenInputs = "";
        var o = config.baseParam;
        for (var i in o) {
            hiddenInputs += ("<input name='" + i + "' type='hidden' value='" + o[i] + "'/>");
        }

        $('body').append('<form method="POST" action="' + config.baseUrl + '">'
        + hiddenInputs + '</form>');
        $('body').find("form").last().submit();
        $('body').find("form").last().remove();
    }
});
