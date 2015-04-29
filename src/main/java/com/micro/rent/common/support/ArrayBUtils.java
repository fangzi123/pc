package com.micro.rent.common.support;

public class ArrayBUtils {
    public static String Array2String(String[] s, String split) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length; i++) {
            if (i != (s.length - 1)) {
                sb.append(s[i] + split);
            } else {
                sb.append(s[i]);
            }
        }
        return sb.toString();
    }
}
