package com.micro.rent.common.comm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class StringUtil {
    public static Integer pointZero2Integer(String str) {
        Integer a = null;
        if (str.endsWith(".0")) {
            String s = str.substring(0, str.indexOf("."));
            a = Integer.valueOf(s);
        }
        return a;
    }

    public static String removePointZero(String str) {
        String s = str;

        if (str.endsWith(".0")) {
            s = str.substring(0, str.indexOf("."));
        }
        return s;
    }

    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat dd = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = dd.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String secondToHm(int second) {
        int h = 0;
        int d = 0;
        int s = 0;
        int temp = second % 3600;
        if (second > 3600) {
            h = second / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = second / 60;
            if (second % 60 != 0) {
                s = second % 60;
            }
        }

        String du = new String();
        if (h != 0) {
            du = h + "小时";
        }
        du += d + "分钟";
        return du;
    }

    public static double distanceSimplify(double lat1, double lng1, double lat2, double lng2) {

        double dx = lng1 - lng2; // 经度差值

        double dy = lat1 - lat2; // 纬度差值

        double b = (lat1 + lat2) / 2.0; // 平均纬度

        double Lx = Math.toRadians(dx) * 6367000.0 * Math.cos(Math.toRadians(b)); // 东西距离

        double Ly = 6367000.0 * Math.toRadians(dy); // 南北距离

        return Math.sqrt(Lx * Lx + Ly * Ly);  // 用平面的矩形对角距离公式计算总距离

    }

    public static ArrayList<Map.Entry<String, Double>> sortMap(Map map) {
        List<Map.Entry<String, Double>> entries = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
                    public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
//	    		          if ((o1.getValue() - o2.getValue())>0)  
                        return (int) (o1.getValue() - o2.getValue());
//	    		          else if((o1.getValue() - o2.getValue())==0)  
//	    		            return 0;  
//	    		          else   
//	    		            return -1;  
                    }
                }
        );
        return (ArrayList<Map.Entry<String, Double>>) entries;
    }

    public static void main(String[] args) {
        int a = 5000;
        String b = secondToHm(a);
        System.out.println(b);
    }
}
