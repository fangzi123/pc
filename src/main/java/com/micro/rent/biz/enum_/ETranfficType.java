package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

public enum ETranfficType {

    /**
     * 交通类型(1：驾车/2：公共交通/3：步行/99：其他)
     */
    EMPTY_TYPE("99"),
    DRIVING("1"),
    TRANSIT("2"),
    WALKING("3");


    private static Map<String, String> mpOri;

    static {
        mpOri = new HashMap<String, String>();

        mpOri.put("1", "driving");
        mpOri.put("3", "walking");
        mpOri.put("2", "transit");
    }

    private String code;

    private ETranfficType(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mpOri.get(code);
    }

    public static ETranfficType getSelfByCode(String code) {
        if (DRIVING.getCode().equals(code)) {
            return DRIVING;
        } else if (WALKING.getCode().equals(code)) {
            return WALKING;
        } else if (TRANSIT.getCode().equals(code)) {
            return TRANSIT;
        }

        return EMPTY_TYPE;
    }

    public String getCode() {
        return this.code;
    }
}
