package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

/**
 * 装修： 0-开间； 1-毛坯； 2-普通装修； 3-中等装修； 4-精装修； 5-豪华装修；
 *
 * @author zheng
 */
public enum ERenovation {

    BAY("0"), BLANK("1"), ORDINARY("2"), MEDIUM("3"), FINE("4"), LUXURY("5");

    private static Map<String, String> mp;

    static {
        mp = new HashMap<String, String>();
        mp.put("0", "开间");
        mp.put("1", "毛坯");
        mp.put("2", "普通装修");
        mp.put("3", "中等装修");
        mp.put("4", "精装修");
        mp.put("5", "豪华装修");
    }

    private String code;

    private ERenovation(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mp.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
