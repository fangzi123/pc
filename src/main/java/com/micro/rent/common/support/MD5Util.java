package com.micro.rent.common.support;

import com.micro.rent.common.comm.ConfigUtil;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;

/**
 * MD5工具类
 *
 * @author liqianxi
 * @date 2014-12-24
 */
public class MD5Util {

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname)) {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
            }
        } catch (Exception exception) {
        }

        return resultString;
    }

    /**
     * 创建签名MD5
     *
     * @param signParams  参与签名元素
     * @param isWeixinPay 是否为创建微信支付签名
     * @param upperCase   true:大写字符结果/false:小写字符结果
     * @return 签名MD5
     * @throws Exception
     */
    public static String createMD5Sign(SortedMap<String, String> signParams,
                                       boolean isWeixinPay, boolean upperCase) {
        StringBuffer sb = new StringBuffer();
        Set<Entry<String, String>> es = signParams.entrySet();
        Iterator<Entry<String, String>> it = es.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            sb.append(k + "=" + v + "&");
            //要采用URLENCODER的原始值！
        }

        // 创建微信支付签名时，参与签名项目增加窝牛支付密钥
        if (isWeixinPay) {
            try {
                sb.append("key=" + ConfigUtil.getConfig("weixin.pay.key") + "&");
            } catch (Exception e) {
                return null;
            }
        }

        String params = sb.substring(0, sb.lastIndexOf("&"));
        return upperCase ? MD5Encode(params, "UTF-8").toUpperCase() : MD5Encode(params, "UTF-8");
    }

}
