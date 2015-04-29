package com.micro.rent.common.support;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MathUtils {

    /**
     * 地球半径：6378137M
     * 取WGS84标准参考椭球中的地球长半径(单位:m)
     */
    private static double EARTH_RADIUS = 6378137;

    public static String getRandomString(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(10);

            sb.append(str.charAt(number));
        }

        return sb.toString();
    }

    public static String substractDecimal(String minuend, String subtractor) {
        try {
            if (StringUtils.isBlank(minuend) || StringUtils.isBlank(subtractor))
                return "";
            BigDecimal m = new BigDecimal(minuend);
            BigDecimal s = new BigDecimal(subtractor);
            return String.valueOf(m.subtract(s));
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到两点间的距离 米
     *
     * @param lat1 点1精度
     * @param lng1 点1维度
     * @param lat2 点2精度
     * @param lng2 点2维度
     * @return
     */
    public static double getDistanceOfMeter(double lat1, double lng1,
                                            double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static Map<String, Object> square(double lng, double lat, double distance) {
        double dlng = Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))
                / Math.cos(rad(lat)));
        dlng = deg(dlng);
        double dlat = distance / EARTH_RADIUS;
        dlat = deg(dlat);
        double latLeft = lat - dlat;
        double latRight = lat + dlat;
        double lngTop = lng + dlng;
        double lngButtom = lng - dlng;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("latLeft", latLeft);
        map.put("latRight", latRight);
        map.put("lngTop", lngTop);
        map.put("lngButtom", lngButtom);
        return map;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    static double deg(double radian) {
        return radian * 180 / Math.PI;
    }

}
