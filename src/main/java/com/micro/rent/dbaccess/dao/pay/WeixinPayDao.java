package com.micro.rent.dbaccess.dao.pay;

import com.micro.rent.dbaccess.entity.pay.PayLog;
import com.micro.rent.dbaccess.entity.pay.Payment;

/**
 * 微信支付DAO
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public interface WeixinPayDao {

    /**
     * 生成支付信息
     *
     * @param payment 支付信息
     */
    void insertPayment(Payment payment);

    /**
     * 查询订单信息
     *
     * @param tradeId 订单ID
     * @return 支付信息
     */
    Payment selectPaymentInfo(String tradeNo);

    /**
     * 生成支付日志记录
     *
     * @param payLog 支付日志
     */
    void insertPayLog(PayLog payLog);
}
