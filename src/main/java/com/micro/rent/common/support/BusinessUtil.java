package com.micro.rent.common.support;

import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.comm.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class BusinessUtil {

    /**
     * 得到页面选中的数据
     */
    public static LinkedHashMap<String, String> getStorage(String _storage) {
        Map<String, ArrayList<LinkedHashMap<String, String>>> map = JsonUtils.jsonString2Map(_storage);
        if (map == null || map.isEmpty())
            return null;
        ArrayList<LinkedHashMap<String, String>> ids = map.get(Constants._storage);
        if (ids == null || ids.isEmpty())
            return null;
        LinkedHashMap<String, String> selectArr = ids.get(0);
        if (selectArr == null || selectArr.isEmpty())
            return null;
        return selectArr;
    }

    /**
     * 替换卡号-前6后4当中用*代替
     *
     * @param cardNo
     * @return
     */
    public static String subCardNo(String cardNo) {
        if (StringUtils.isBlank(cardNo))
            return "";
        else if (cardNo.length() < 6)
            return cardNo;
        return cardNo.substring(0, 6).concat("******").concat(cardNo.substring(cardNo.length() - 4, cardNo.length()));
    }

    public static Boolean compareStringByteLength(String source, int length) {
        int sourceLenth = 0;
        try {
            sourceLenth = source.getBytes("utf-8").length;
        } catch (UnsupportedEncodingException e) {

        }
        return sourceLenth > length;
    }

    public static List<String> extractMonthRange(String startYearMonth, String endYearMonth) {
        //日期格式 yyyyMM
        String dateExpress = "^\\d{4}((0[1-9])|(1[0-2]))$";
        org.apache.commons.lang3.Validate.matchesPattern(startYearMonth, dateExpress);
        org.apache.commons.lang3.Validate.matchesPattern(endYearMonth, dateExpress);

        List<String> dateRange = new ArrayList<String>();
        //如果开始日期为空或者如果结束日期为空
        if (StringUtils.isBlank(startYearMonth)
                || StringUtils.isBlank(endYearMonth)) {
            dateRange = getAllMonth();
        }
        //两个参数不为空
        else {
            // 获取前四位年份
            int startYear = Integer.valueOf(startYearMonth.substring(0, 4));
            int startMonth = Integer.valueOf(startYearMonth.substring(4));
            int endYear = Integer.valueOf(endYearMonth.substring(0, 4));
            int endMonth = Integer.valueOf(endYearMonth.substring(4));

            //如果年份跨度 >= 2
            if (endYear - startYear >= 2) {
                dateRange = getAllMonth();
            }
            //如果年份跨度 == 1
            else if (endYear - startYear == 1) {
                //endMonth > startMonth
                if ((endMonth - startMonth >= 0)
                        || (startMonth - endMonth == 1)) {
                    dateRange = getAllMonth();
                } else {
                    for (int j = startMonth; j <= 12; j++) {
                        dateRange.add(StringUtils.leftPad(Integer.valueOf(j).toString(), 2, "0"));
                    }
                    for (int k = 1; k <= endMonth; k++) {
                        dateRange.add(StringUtils.leftPad(Integer.valueOf(k).toString(), 2, "0"));
                    }
                }
            }
            //如果年份跨度 < 1
            else {
                for (int i = startMonth; i <= endMonth; i++) {
                    dateRange.add(StringUtils.leftPad(Integer.valueOf(i).toString(), 2, "0"));
                }
            }

        }

        return dateRange;
    }

    private static List<String> getAllMonth() {
        return Arrays.asList(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
    }

    /**
     * 判断用户使用的微信客户端是否支持微信支付
     *
     * @param userAgent 请求的UserAgent
     * @return true:支持微信支付/false:不支持微信支付
     */
    public static boolean supportWeixinPay(String userAgent) {
        String indexStr = "MicroMessenger/";
        int index = userAgent.indexOf(indexStr);
        if (index == -1) {
            return false;
        }

        return (new String(indexStr.substring(index + indexStr.length())).charAt(0) > '4') ? true : false;
    }

    /**
     * 服务器根URL
     *
     * @param contextPath 项目根路径
     * @return 服务器根URL
     */
    public static String getServerRootURL(String contextPath) {
        String rootUrl = null;
        try {
            rootUrl = ConfigUtil.getConfig("balancer.server.url");
        } catch (Exception e) {
            rootUrl = "http://182.92.24.42";
        }
        // 负载服务器地址
        return (rootUrl + contextPath);
    }
}
