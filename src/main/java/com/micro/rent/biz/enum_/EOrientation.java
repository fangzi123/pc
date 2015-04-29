package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

/**
 * 朝向：朝向：
 * 1-东；
 * 2-南；
 * 3-西；
 * 4-北；
 * 5-东南；
 * 6-东北；
 * 7-西南；
 * 8-西北；
 *
 * @author Lvzg
 */
public enum EOrientation {

    EAST("1"),
    SOUTH("2"),
    WEST("3"),
    NORTH("4"),
    EASTSOUTH("5"),
    EASTNORTH("6"),
    WESTSOUTH("7"),
    WESTNORTH("8");

    private static Map<String, String> mpOri;

    static {
        mpOri = new HashMap<String, String>();

        mpOri.put("1", "东");
        mpOri.put("2", "南");
        mpOri.put("3", "西");
        mpOri.put("4", "北");
        mpOri.put("5", "东南");
        mpOri.put("6", "东北");
        mpOri.put("7", "西南");
        mpOri.put("8", "西北");

    }

    private String code;

    private EOrientation(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mpOri.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
