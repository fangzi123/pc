package com.micro.rent.common.support;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.util.Base64;

public class CookieUtil {
    private static String KEY = " cookie ";// 8位 或 倍数
    private static Integer EXPIRED_TIME = 60*60*24;//一天

    public static void saveCookie(HttpServletResponse response, String name,
            String value, Integer validTime) {
    //    String des64 = encrypt(value);
        Cookie cookie = new Cookie(name, value);
        if (validTime != null) {
            cookie.setMaxAge(validTime);
        } else {
            cookie.setMaxAge(EXPIRED_TIME);
        }
        cookie.setPath("/");//
        response.addCookie(cookie);
    }
    

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(name)) {
                cookie.setPath("/");//
                return cookie;
            }
        }
        return null;
    }

    public static void removeCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");//
        response.addCookie(cookie);
    }

//    public static String encrypt(String value) {
//        return Base64.encodeBase64String(encrypt(value.getBytes())).trim();
//    }

//    public static String decrypt(String value) {
//        return new String(decrypt(Base64.decodeBase64(value)));
//    }

    private static byte[] encrypt(byte[] datasource) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] decrypt(byte[] src) {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        try {
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(KEY.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

}
