  $(function () {
            var $day = $("#day"),
              $month = $("#month"),
              $year = $("#year");

            <!--出始化年-->
            var dDate = new Date(),
              dCurYear = dDate.getFullYear(),
              str = "";
            for (var i = dCurYear - 100; i < dCurYear + 1; i++) {
                if (i == dCurYear) {
                    str = "<option value=" + i + " selected=true>" + i + "</option>";
                } else {
                    str = "<option value=" + i + ">" + i + "</option>";
                }
                $year.append(str);
            }

            <!--出始化月-->
            for (var i = 1; i <= 12; i++) {

                if (i == (dDate.getMonth() + 1)) {
                    str = "<option value=" + i + " selected=true>" + i + "</option>";
                } else {
                    str = "<option value=" + i + ">" + i + "</option>";
                }
                $month.append(str);
            }
            <!--调用函数出始化日-->
            TUpdateCal($year.val(), $month.val());
            $("#year,#month").bind("change", function(){
                TUpdateCal($year.val(),$month.val());
            });
        });

        <!--根据年月获取当月最大天数-->
        function TGetDaysInMonth(iMonth, iYear) {
            var dPrevDate = new Date(iYear, iMonth, 0);
            return dPrevDate.getDate();
        }

        function TUpdateCal(iYear, iMonth) {
            var dDate = new Date(),
                daysInMonth = TGetDaysInMonth(iMonth, iYear),
                str = "";

            $("#day").empty();

            for (var d = 1; d <= parseInt(daysInMonth); d++) {

                if (d == dDate.getDate()) {
                    str = "<option value=" + d + " selected=true>" + d + "</option>";
                } else {
                    str = "<option value=" + d + ">" + d + "</option>";
                }
                $("#day").append(str);
            }
        }