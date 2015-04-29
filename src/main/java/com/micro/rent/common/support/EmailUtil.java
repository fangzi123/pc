package com.micro.rent.common.support;

import com.micro.rent.common.comm.ConfigUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.*;

/**
 * 邮件发送工具类
 *
 * @author liqianxi
 * @date 2014-11-12
 */
public class EmailUtil {
    // 发送方邮件服务器地址
    private static String host;
    // 发送方邮件服务器端口号
    private static String port;
    // 发送方邮件账号
    private static String account;
    // 发送方邮件账号密码
    private static String password;
    // 发送者邮件地址
    private static String from;
    // 默认有邮件发送地址
    private static String defaultTarget;

    static {
        try {
            host = ConfigUtil.getConfig("mail.host");
            port = ConfigUtil.getConfig("mail.port");
            account = ConfigUtil.getConfig("mail.account");
            password = ConfigUtil.getConfig("mail.password");
            from = ConfigUtil.getConfig("mail.from");
            defaultTarget = ConfigUtil.getConfig("mail.defaultTarget");
        } catch (Exception e) {
        }
    }

    /**
     * 发送邮件至默认地址
     *
     * @param subject 邮件主题
     * @param message 邮件内容
     * @throws EmailException
     */
    public static void sendEmail(String subject, String message) throws EmailException {
        sendEmail(subject, message, null);
    }

    /**
     * 发送邮件至指定地址
     *
     * @param subject 邮件主题
     * @param message 邮件内容
     * @throws EmailException
     */
    public static void sendEmail(String subject, String message, String[] targets) throws EmailException {
        if (targets == null || targets.length == 0) {
            targets = defaultTarget.split(",");
        }
        Email email = new SimpleEmail();
        email.setHostName(host);
        if (!StringUtils.isEmpty(port)) {
            email.setSmtpPort(Integer.valueOf(port));
        }
        email.setAuthenticator(new DefaultAuthenticator(account, password));
        email.setCharset("utf-8");
        email.setStartTLSRequired(true);
        email.setFrom(from);
        email.addTo(targets);
        email.setSubject(subject);
        email.setMsg(message);
        email.send();
    }

    /**
     * 发送HTML格式邮件至指定地址
     *
     * @param subject 邮件主题
     * @param message 邮件内容
     * @throws EmailException
     */
    public static void sendHtmlEmail(String subject, String message) throws EmailException {
        sendHtmlEmail(subject, message, null);
    }

    /**
     * 发送HTML格式邮件至指定地址
     *
     * @param subject 邮件主题
     * @param message 邮件内容
     * @param targets 邮件发送目标地址
     * @throws EmailException
     */
    public static void sendHtmlEmail(String subject, String message, String[] targets) throws EmailException {
        if (targets == null || targets.length == 0) {
            targets = defaultTarget.split(",");
        }

        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(host);
        if (!StringUtils.isEmpty(port)) {
            htmlEmail.setSmtpPort(Integer.valueOf(port));
        }
        htmlEmail.setAuthenticator(new DefaultAuthenticator(account, password));
        htmlEmail.setCharset("utf-8");
        htmlEmail.setStartTLSRequired(true);
        htmlEmail.setFrom(from);
        htmlEmail.addTo(targets);
        htmlEmail.setSubject(subject);
        htmlEmail.setHtmlMsg(message);
        htmlEmail.send();
    }

}
