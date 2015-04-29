package com.micro.rent.biz.enum_;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 星期： 1-周一； 2-周二； 3-周三； 4-周四；5-周五； 6-周六；7-周日
 *
 * @author liqianxi
 */
public enum EWeekDayType {

    MONDAY(Calendar.MONDAY),
    TUESDAY(Calendar.TUESDAY),
    WEDNESDAY(Calendar.WEDNESDAY),
    THURSEDAY(Calendar.THURSDAY),
    FRIDAY(Calendar.FRIDAY),
    SATURDAY(Calendar.SATURDAY),
    SUNDAY(Calendar.SUNDAY);

    private static Map<Integer, String> mp;

    static {
        mp = new HashMap<Integer, String>();
        mp.put(Calendar.MONDAY, "周一");
        mp.put(Calendar.TUESDAY, "周二");
        mp.put(Calendar.WEDNESDAY, "周三");
        mp.put(Calendar.THURSDAY, "周四");
        mp.put(Calendar.FRIDAY, "周五");
        mp.put(Calendar.SATURDAY, "周六");
        mp.put(Calendar.SUNDAY, "周日");
    }

    private int code;

    private EWeekDayType(int code) {
        this.code = code;
    }

    public static String getNameByCode(int code) {
        return mp.get(code);
    }

    public int getCode() {
        return this.code;
    }
}
