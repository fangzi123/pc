package com.micro.rent.biz.enum_;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信订单状态：
 * <ul>
 * <li>SUCCESS:支付成功</li>
 * <li>REFUND:转入退款</li>
 * <li>NOTPAY:未支付</li>
 * <li>CLOSED:已关闭</li>
 * <li>REVOKED:已撤销</li>
 * <li>USERPAYING:用户支付中</li>
 * <li>NOPAY:未支付(输入密码或确认支付超时)</li>
 * <li>PAYERROR:支付失败(其他原因,如银行返回失败)</li>
 * </ul>
 *
 * @author liqianxi
 * @date 2015-01-06
 */
public enum EWeixinTradeState {

    // 支付成功
    SUCCESS("SUCCESS"),
    // 转入退款
    REFUND("REFUND"),
    // 未支付
    NOTPAY("NOTPAY"),
    // 已关闭
    CLOSED("CLOSED"),
    // 已撤销
    REVOKED("REVOKED"),
    // 用户支付中
    USERPAYING("USERPAYING"),
    // 未支付(输入密码或确认支付超时)
    NOPAY("NOPAY"),
    // 支付失败(其他原因,如银行返回失败)
    PAYERROR("PAYERROR");

    private static Map<String, String> mp;

    static {
        mp = new HashMap<String, String>();
        mp.put("SUCCESS", "支付成功");
        mp.put("REFUND", "转入退款");
        mp.put("NOTPAY", "未支付");
        mp.put("CLOSED", "已关闭");
        mp.put("REVOKED", "已撤销");
        mp.put("USERPAYING", "用户支付中");
        mp.put("NOPAY", "未支付(输入密码或确认支付超时)");
        mp.put("PAYERROR", "支付失败(其他原因,如银行返回失败)");
    }

    private String code;

    private EWeixinTradeState(String code) {
        this.code = code;
    }

    public static String getNameByCode(String code) {
        return mp.get(code);
    }

    public String getCode() {
        return this.code;
    }
}
