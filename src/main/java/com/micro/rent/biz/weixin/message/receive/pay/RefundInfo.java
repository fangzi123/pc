package com.micro.rent.biz.weixin.message.receive.pay;

import com.micro.rent.biz.weixin.message.WeixinPayResponseBaseInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 退款申请接口响应信息
 *
 * @author liqianxi
 * @date 2014-12-26
 */
@XStreamAlias("xml")
public class RefundInfo extends WeixinPayResponseBaseInfo {

    @XStreamAlias("sub_mch_id")
    private String subMchId;
    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 退款渠道
     */
    @XStreamAlias("refund_channel")
    private String refundChannel;

    /**
     * 退款金额
     */
    @XStreamAlias("refund_fee")
    private String refundFee;

    /**
     * 现金券退款金额
     */
    @XStreamAlias("coupon_refund_fee")
    private String couponRefundFee;

    /**
     * 取得设备号
     *
     * @return 设备号
     */
    public String getDeviceInfo() {
        return deviceInfo;
    }

    /**
     * 设定设备号
     *
     * @param deviceInfo 设备号
     */
    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    /**
     * 取得微信支付订单号
     *
     * @return 微信支付订单号
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 设定微信支付订单号
     *
     * @param transactionId 微信支付订单号
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 取得商户订单号
     *
     * @return 商户订单号
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * 设定商户订单号
     *
     * @param outTradeNo 商户订单号
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * 取得商户退款单号
     *
     * @return 商户退款单号
     */
    public String getOutRefundNo() {
        return outRefundNo;
    }

    /**
     * 设定商户退款单号
     *
     * @param outRefundNo 商户退款单号
     */
    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    /**
     * 取得微信退款单号
     *
     * @return 微信退款单号
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * 设定微信退款单号
     *
     * @param refundId 微信退款单号
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    /**
     * 取得退款渠道
     *
     * @return 退款渠道
     */
    public String getRefundChannel() {
        return refundChannel;
    }

    /**
     * 设定退款渠道
     *
     * @param refundChannel 退款渠道
     */
    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    /**
     * 取得退款金额
     *
     * @return 退款金额
     */
    public String getRefundFee() {
        return refundFee;
    }

    /**
     * 设定退款金额
     *
     * @param refundFee 退款金额
     */
    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    /**
     * 取得现金券退款金额
     *
     * @return 现金券退款金额
     */
    public String getCouponRefundFee() {
        return couponRefundFee;
    }

    /**
     * 设定现金券退款金额
     *
     * @param couponRefundFee 现金券退款金额
     */
    public void setCouponRefundFee(String couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

}
