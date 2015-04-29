package com.micro.rent.biz.weixin.message.receive.pay;

import com.micro.rent.biz.weixin.message.WeixinPayResponseBaseInfo;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 通用通知接口响应信息
 *
 * @author liqianxi
 * @date 2014-12-25
 */
@XStreamAlias("xml")
public class UnifyInfo extends WeixinPayResponseBaseInfo {

    /**
     * 设备号
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 用户标识
     */
    @XStreamAlias("openid")
    private String openId;

    /**
     * 是否关注公众账号
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribe;

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 付款银行
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 总金额
     */
    @XStreamAlias("total_fee")
    private String totalFee;

    /**
     * 现金券金额
     */
    @XStreamAlias("coupon_fee")
    private String couponFee;

    /**
     * 货币种类
     */
    @XStreamAlias("fee_type")
    private String feeType;

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
     * 商家数据包
     */
    private String attach;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    @XStreamAlias("cash_fee")
    private String cashFee;

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
     * 取得用户标识
     *
     * @return 用户标识
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设定用户标识
     *
     * @param openId 用户标识
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 取得是否关注公众账号
     *
     * @return 是否关注公众账号
     */
    public String getIsSubscribe() {
        return isSubscribe;
    }

    /**
     * 设定是否关注公众账号
     *
     * @param isSubscribe 是否关注公众账号
     */
    public void setIsSubscribe(String isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    /**
     * 取得交易类型
     *
     * @return 交易类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设定交易类型
     *
     * @param tradeType 交易类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 取得付款银行
     *
     * @return 付款银行
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * 设定付款银行
     *
     * @param bankType 付款银行
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * 取得总金额
     *
     * @return 总金额
     */
    public String getTotalFee() {
        return totalFee;
    }

    /**
     * 设定总金额
     *
     * @param totalFee 总金额
     */
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 取得现金券金额
     *
     * @return 现金券金额
     */
    public String getCouponFee() {
        return couponFee;
    }

    /**
     * 设定现金券金额
     *
     * @param couponFee 现金券金额
     */
    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
    }

    /**
     * 取得货币种类
     *
     * @return 货币种类
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设定货币种类
     *
     * @param feeType 货币种类
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
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
     * 取得商家数据包
     *
     * @return 商家数据包
     */
    public String getAttach() {
        return attach;
    }

    /**
     * 设定商家数据包
     *
     * @param attach 商家数据包
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * 取得支付完成时间
     *
     * @return 支付完成时间
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * 设定支付完成时间
     *
     * @param timeEnd 支付完成时间
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }

}
