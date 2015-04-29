package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单状态： 0-未支付； 1-已支付； 2-已取消
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public enum ETradeStatus {

    // 未支付
    UNPAY("0"),
    // 已支付
    PAY("1"),
    // 已取消
    CANCEL("2");

    private static Map<String, String> mp;

    static {
        mp = new HashMap<String, String>();
        mp.put("0", "未支付");
        mp.put("1", "已支付");
        mp.put("2", "已取消");
    }

    private String code;

    private ETradeStatus(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mp.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
