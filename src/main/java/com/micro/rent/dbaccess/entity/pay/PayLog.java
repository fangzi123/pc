package com.micro.rent.dbaccess.entity.pay;

/**
 * 支付日志信息
 *
 * @author liqianxi
 * @date 2014-12-30
 */
public class PayLog {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 微信ID
     */
    private String weixinId;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 请求API
     */
    private String requestApi;

    /**
     * 请求内容
     */
    private String requestContent;

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
     * 取得请求API
     *
     * @return 请求API
     */
    public String getRequestApi() {
        return requestApi;
    }

    /**
     * 设定请求API
     *
     * @param requestApi 请求API
     */
    public void setRequestApi(String requestApi) {
        this.requestApi = requestApi;
    }

    /**
     * 取得请求内容
     *
     * @return 请求内容
     */
    public String getRequestContent() {
        return requestContent;
    }

    /**
     * 设定请求内容
     *
     * @param requestContent 请求内容
     */
    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
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
