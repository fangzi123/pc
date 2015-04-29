package com.micro.rent.biz.bean;

import java.util.HashMap;
import java.util.Map;

public enum EExtensionName {

    XLS("xls"),
    XLSX("xlsx"),
    TXT("txt");

    private static Map<String, EExtensionName> mpExtension;

    static {
        mpExtension = new HashMap<String, EExtensionName>();

        mpExtension.put("xls", XLS);
        mpExtension.put("xlsx", XLSX);
        mpExtension.put("txt", TXT);
    }

    private String code;

    private EExtensionName(String code) {
        this.code = code;
    }

    public static EExtensionName obtainExtensionNameByCode(String code) {
        return mpExtension.get(code);
    }

    public String getCode() {
        return this.code;
    }

}
