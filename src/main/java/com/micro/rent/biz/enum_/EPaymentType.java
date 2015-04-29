package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

/**
 * 收费方式：
 * 1-付三押一；
 * 2-
 *
 * @author Lvzg
 */
public enum EPaymentType {

    // 1：付三押一，2：付一押一，3：付六押一
    PAY3_DEP1("1"),
    PAY1_DEP1("2"),
    PAY6_DEP1("3");

    private static Map<String, String> mpPaymentType;

    static {
        mpPaymentType = new HashMap<String, String>();

        mpPaymentType.put("1", "付三押一");
        mpPaymentType.put("2", "付一押一");
        mpPaymentType.put("3", "付六押一");

    }

    private String code;

    private EPaymentType(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mpPaymentType.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
