 function setCookie(name, value) {
   var Days = 30;
   var exp = new Date();
   exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
   document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
}

 function getCookie(c_name) {
   if (document.cookie.length > 0) {
   c_start = document.cookie.indexOf(c_name + "=");//这里因为传进来的的参数就是带引号的字符串，所以c_name可以不用加引号
   if (c_start != -1) {
   c_start = c_start + c_name.length + 1;
   c_end = document.cookie.indexOf(";", c_start);//当indexOf()带2个参数时，第二个代表其实位置，参数是数字，这个数字可以加引号也可以不加（最好还是别加吧）
   if (c_end == -1) c_end = document.cookie.length;
   return unescape(document.cookie.substring(c_start, c_end));
      }
   }
   return "no";
 }