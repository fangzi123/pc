package com.micro.rent.biz.weixin.service;

import com.micro.rent.biz.weixin.message.receive.pay.OrderInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundQueryInfo;
import com.micro.rent.dbaccess.entity.pay.PayLog;
import com.micro.rent.dbaccess.entity.pay.Payment;

import java.util.SortedMap;

/**
 * @author liqianxi
 * @Description: 微信支付
 * @date 2014-12-24
 */
public interface WeixinPayService {

    /**
     * 添加支付信息
     *
     * @param 支付信息
     */
    void addPaymentInfo(Payment payment);

    /**
     * 取得支付信息
     *
     * @param tradeNo 订单号
     * @return 支付信息
     */
    Payment selectPaymentInfo(String tradeNo);

    /**
     * 添加支付日志记录
     *
     * @param 支付日志信息
     */
    void addPayLogInfo(PayLog payLog);

    /**
     * 统一支付
     *
     * @param 统一支付接口请求信息
     * @return 统一支付信息
     */
    String unifiedOrder(SortedMap<String, String> unifiedOrderMap);

    /**
     * 订单查询
     *
     * @param 订单查询接口请求信息
     * @return 订单信息
     */
    OrderInfo orderQuery(SortedMap<String, String> orderQueryMap);

    /**
     * 退款申请
     *
     * @param 退款申请接口请求信息
     * @return 退款信息
     */
    RefundInfo refund(SortedMap<String, String> refundMap);

    /**
     * 退款查询
     *
     * @param 退款查询接口请求信息
     * @return 退款单信息
     */
    RefundQueryInfo refundQuery(SortedMap<String, String> refundQueryMap);
}
