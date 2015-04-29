package com.micro.rent.biz.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单类型
 * 1-预约；
 * 2-预订；
 *
 * @author liqianxi
 * @date 2014/11/6
 */
public enum EOrderType {
    ORDER("1"),
    RESERVE("2");

    private static Map<String, String> mpOri;

    static {
        mpOri = new HashMap<String, String>();
        mpOri.put("1", "预约");
        mpOri.put("2", "预订");
    }

    private String code;

    private EOrderType(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mpOri.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
