/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */

jQuery.extend(jQuery.validator.messages, {
    required: "字段不能留空",
    remote: "请修正该字段",
    email: "请输入正确格式的电子邮件",
    url: "请输入合法的网址",
    date: "请输入合法的日期",
    dateISO: "请输入合法的日期 (ISO).",
    number: "请输入合法的数字",
    digits: "只能输入整数",
    creditcard: "请输入合法的信用卡号",
    equalTo: "请输入相同的值",
    accept: "请输入拥有合法后缀名的字符串",
    maxlength: jQuery.validator.format("请输入一个长度最多是 {0} 的字符串"),
    minlength: jQuery.validator.format("请输入一个长度最少是 {0} 的字符串"),
    rangelength: jQuery.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
    range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
    max: jQuery.validator.format("请输入一个最大为 {0} 的值"),
    min: jQuery.validator.format("请输入一个最小为 {0} 的值"),
    //add methods
    alphanumeric: "只能输入字母，数字以及下划线",
    dateGreat: "开始日期小于结束日期",
    dateLess: "结束日期大于开始日期",
    dateRange: "起始日期不能大于结束日期",
    amtRange: "‘大于金额’不能小于‘小于金额’",
    yearMonth: "请输入正确格式的年月值(201405)",
    yearMonthRange: "截止年月不能小于起始年月",
    passwdConfirm: "两次输入密码不一致",
    level6_item: '请输入商品编码',
    validateUser: '账号不存在',
    validatePwd: '密码不正确',
    pwdUsername: "请先输入有效登录账号",
    vaidateFileType: '请选择正确的文件类型或文件',
    validateCaptcha: '验证码不正确',
    validateSupplier: '供应商编号不存在'

});
