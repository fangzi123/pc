package com.micro.rent.common.web.service.impl;

import java.text.MessageFormat;

import org.apache.commons.mail.EmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.support.EmailUtil;
import com.micro.rent.common.web.service.EmailService;

/**
 * 邮箱服务
 *
 * @date 2015/1/22
 */
@Service
public class EmailServiceImpl implements EmailService {
    protected transient Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 向客服发送信息
     *
     * @param clientTel
     *            预约客户电话
     * @param orderTime
     *            预约时间
     * @param houseAddress
     *            公寓地址
     * @param houseTel
     *            公寓公司电话
     * @param brandName
     *            公寓公司
     * @return 邮件发送是否成功
     */
    @Override
    public boolean sendOrderMessage(String clientTel, String orderTime,
            String houseAddress, String houseTel, String brandName) {
        String subject;
        String message;
        try {
            subject = ConfigUtil.getConfig("mail.template.order.subject");
            message = ConfigUtil.getConfig("mail.template.order.message");
        } catch (Exception e) {
            log.error("获取预约邮件主题、信息模板信息失败！");
            return false;
        }

        try {
            EmailUtil.sendHtmlEmail(MessageFormat.format(subject, clientTel,
                    houseAddress, orderTime, brandName), MessageFormat.format(
                            message, clientTel, houseAddress, orderTime, brandName,
                    houseTel));
        } catch (EmailException e) {
            log.error("发送预约邮件失败！");
            return false;
        }

        return true;
    }

    public boolean sendOrderMessageToCusetomer(String clientTel,
            String orderDate, Integer houseId) {

        return false;
    }

}
