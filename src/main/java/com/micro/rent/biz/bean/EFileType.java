package com.micro.rent.biz.bean;

import java.util.HashMap;
import java.util.Map;

public enum EFileType {

    HOUSEINFO("houseinfo"),
    HOUSEPICTURE("housepicture");

    private static Map<String, EFileType> mpExtension;

    static {
        mpExtension = new HashMap<String, EFileType>();

        mpExtension.put("houseinfo", HOUSEINFO);
        mpExtension.put("housepicture", HOUSEPICTURE);
    }

    private String code;

    private EFileType(String code) {
        this.code = code;
    }

    public static EFileType obtainExtensionNameByCode(String code) {
        return mpExtension.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
