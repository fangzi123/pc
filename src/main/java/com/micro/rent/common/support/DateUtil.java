package com.micro.rent.common.support;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.micro.rent.biz.enum_.EWeekDayType;
import com.micro.rent.common.comm.Constants;

/**
 * @author
 * @Description: 日期时间工具类
 * @create
 */
public class DateUtil {

    /**
     * @return String 日期字符串
     * @throws
     * @Description: 获取当前系统日期 格式:yyyy-mm-dd
     */
    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd");
    }

    /**
     * @return String
     * @throws
     * @Title: getDate
     * @Description: 获取当前系统日期 格式:yyyymmdd
     */
    public static String getDate() {
        return getCurrentDate("yyyyMMdd");
    }

    /**
     * 功能描述：根据给定的格式将str转化为Date类型
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date getDate(String dateStr, String format) {
        DateFormat formater = new SimpleDateFormat(format);
        Date result;
        try {
            result = formater.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }

    /**
     * @param format
     *            日期格式
     * @return String 日期字符串
     * @throws
     * @Description: 获取当前系统日期
     */
    public static String getCurrentDate(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     * @return String 时间字符串
     * @throws
     * @Description: 获取当前系统时间 格式:HH:mm:ss
     */
    public static String getCurrentTime() {
        return getCurrentDate("HH:mm:ss");
    }

    /**
     * @param format
     *            时间格式
     * @return String 时间字符串
     * @throws
     * @Description: 获取当前系统时间
     */
    public static String getCurrentTime(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     * @return String 时间字符串
     * @throws
     * @Description: 获取当前系统时间戳 格式:yyyy-mm-dd HH:mm:ss
     */
    public static String getCurrentTimeStamp() {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @param format
     *            时间格式
     * @return String 时间字符串
     * @throws
     * @Description: 获取当前系统时间戳
     */
    public static String getCurrentTimeStamp(String format) {
        return getCurrentDateTimeStr(format);
    }

    /**
     * @param format
     *            日期时间格式
     * @return String 日期时间字符串
     * @throws
     * @Description: 获取当前日期时间字符串
     */
    public static String getCurrentDateTimeStr(String format) {
        DateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date());
    }

    /**
     * @param source
     * @param interval
     * @return String
     * @throws Exception
     * @Title:sourcePlusInterval
     * @Description:日期增加
     */
    public static String sourcePlusInterval(String source, int interval) {
        DateFormat informater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(informater.parse(source));
        } catch (ParseException e) {

        }
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + interval);
        return informater.format(calendar.getTime());
    }

    /**
     * @param @param source 日期原串
     * @param @param infmt 日期输入格式
     * @param @param outfmt 日期输出格式
     * @param @return 目标日期字符串
     * @throws ParseException
     * @Description: 将字符串从一种格式转化的
     */
    public static String getFmtDate(String source, String infmt, String outfmt)
            throws ParseException {
        // 输入格式
        DateFormat informater = new SimpleDateFormat(infmt);
        // 输出格式
        DateFormat outfomater = new SimpleDateFormat(outfmt);

        return outfomater.format(informater.parse(source));
    }

    /**
     * @param @param source 日期原串
     * @param @param infmt 日期输入格式
     * @param @param outfmt 日期输出格式
     * @param @return 目标日期字符串
     * @throws ParseException
     * @Description: 将字符串从一种格式转化的
     */
    public static String getFmtDateStr(String source, String infmt,
            String outfmt) {
        String result = "";
        try {
            // 输入格式
            DateFormat informater = new SimpleDateFormat(infmt);
            // 输出格式
            DateFormat outfomater = new SimpleDateFormat(outfmt);

            result = outfomater.format(informater.parse(source));

        } catch (Exception e) {
            result = source;
        }

        return result;
    }

    /**
     * @param @param num
     * @param @param fmt
     * @param @return 设定文件
     * @param num
     * @param fmt
     * @return
     * @throws
     * @Title:getAppointDay
     * @Description:TODO(得到指定日期)
     */
    public static String getAppointDay(String fmt, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, num);
        Date tmp = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        String date = sdf.format(tmp);
        return date;
    }

    /**
     * @param source
     * @param interval
     * @return Date
     * @Title:dataPlusInterval
     * @Description:日期增加(Date格式操作)
     */
    public static Date dataPlusInterval(Date source, int interval) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DAY_OF_YEAR, day + interval);
        return calendar.getTime();
    }

    /**
     * @param date
     *            日期
     * @param format
     *            日期格式
     * @return 日期字符串
     * @Description:日期转化为字符串
     */
    public static String getDateStr(Date date, String format) {
        DateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(date);
    }

    public static String secondsToHourMinute(long sTime) {
        String format;
        if (sTime >= 3600) {
            format = "H小时m分钟";
        } else {
            format = "m分钟";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);// 初始化Formatter的转换格式。
        long ms = sTime * 1000;
        return formatter.format(ms - TimeZone.getDefault().getRawOffset());
    }

    public static String getDateAndWeek(String orderDate) {
        // time
        DateFormat dateFormat = new SimpleDateFormat(
                Constants.DATE_FORMAT_NO_SEPARATOR);
        StringBuilder orderTimeDisplayBul = new StringBuilder();
        try {
            Date date = dateFormat.parse(orderDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int weedDay = calendar.get(Calendar.DAY_OF_WEEK);
            orderTimeDisplayBul.append(DateUtil.getFmtDate(orderDate,
                    Constants.DATE_FORMAT_NO_SEPARATOR, "yyyy年MM月dd日"));
            orderTimeDisplayBul.append("  ").append(
                    EWeekDayType.getNameByCode(weedDay));
        } catch (ParseException e) {
            // log.error("预约日期格式解析错误", e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return orderTimeDisplayBul.toString();
    }

}
