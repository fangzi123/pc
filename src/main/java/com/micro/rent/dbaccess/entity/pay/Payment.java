package com.micro.rent.dbaccess.entity.pay;

/**
 * 支付信息
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public class Payment {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 订单总额
     */
    private int totalFee;

    /**
     * 支付总额
     */
    private int payFee;

    /**
     * 现金券总额
     */
    private int couponFee;

    /**
     * 付款银行
     */
    private String bankType;

    /**
     * 微信ID
     */
    private String weixinId;

    /**
     * 是否关注公众账号
     */
    private String isSubscribe;

    /**
     * 微信支付订单号
     */
    private String transactionId;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 取得自增ID
     *
     * @return 自增ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设定自增ID
     *
     * @param id 自增ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 取得订单号
     *
     * @return 订单号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设定订单号
     *
     * @param tradeNo 订单号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 取得订单总额
     *
     * @return 订单总额
     */
    public int getTotalFee() {
        return totalFee;
    }

    /**
     * 设定订单总额
     *
     * @param totalFee 订单总额
     */
    public void setTotalFee(int totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 取得支付总额
     *
     * @return 支付总额
     */
    public int getPayFee() {
        return payFee;
    }

    /**
     * 设定支付总额
     *
     * @param payFee 支付总额
     */
    public void setPayFee(int payFee) {
        this.payFee = payFee;
    }

    /**
     * 取得现金券总额
     *
     * @return 现金券总额
     */
    public int getCouponFee() {
        return couponFee;
    }

    /**
     * 设定现金券总额
     *
     * @param couponFee 现金券总额
     */
    public void setCouponFee(int couponFee) {
        this.couponFee = couponFee;
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
     * 取得微信ID
     *
     * @return 微信ID
     */
    public String getWeixinId() {
        return weixinId;
    }

    /**
     * 设定微信ID
     *
     * @param weixinId 微信ID
     */
    public void setWeixinId(String weixinId) {
        this.weixinId = weixinId;
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
     * 取得创建时间
     *
     * @return 创建时间
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * 设定创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
