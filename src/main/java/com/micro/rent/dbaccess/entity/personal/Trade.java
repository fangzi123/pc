package com.micro.rent.dbaccess.entity.personal;

/**
 * 订单信息
 *
 * @author liqianxi
 * @date 2014-12-29
 */
public class Trade {

    /**
     * 自增ID
     */
    private int id;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 房屋ID
     */
    private String houseId;

    /**
     * 渠道来源标识代码
     */
    private String channelCode;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 预订者姓名
     */
    private String name;

    /**
     * 预订者性别
     */
    private String gender;

    /**
     * 预订者电话号码
     */
    private String telephone;

    /**
     * 微信ID
     */
    private String weixinId;

    /**
     * 微信用户昵称
     */
    private String nickName;

    /**
     * 租期
     */
    private int leaseTerm;

    /**
     * 订单总额
     */
    private int totalFee;

    /**
     * 支付总额
     */
    private int payFee;

    /**
     * 是否使用现金券
     */
    private String useCoupon;

    /**
     * 现金券总额
     */
    private int couponFee;

    /**
     * 创建时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private long updateTime;

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
     * 取得房屋ID
     *
     * @return 房屋ID
     */
    public String getHouseId() {
        return houseId;
    }

    /**
     * 设定房屋ID
     *
     * @param houseId 房屋ID
     */
    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    /**
     * 取得渠道来源标识代码
     *
     * @return 渠道来源标识代码
     */
    public String getChannelCode() {
        return channelCode;
    }

    /**
     * 设定渠道来源标识代码
     *
     * @param channelCode 渠道来源标识代码
     */
    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    /**
     * 取得订单状态
     *
     * @return 订单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设定订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 取得预订者姓名
     *
     * @return 预订者姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设定预订者姓名
     *
     * @param name 预订者姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得预订者性别
     *
     * @return 预订者性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设定预订者性别
     *
     * @param gender 预订者性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 取得预订者电话号码
     *
     * @return 预订者电话号码
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设定预订者电话号码
     *
     * @param telephone 预订者电话号码
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
     * 取得微信用户昵称
     *
     * @return 微信用户昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设定微信用户昵称
     *
     * @param nickName 微信用户昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 取得租期
     *
     * @return 租期
     */
    public int getLeaseTerm() {
        return leaseTerm;
    }

    /**
     * 设定租期
     *
     * @param leaseTerm 租期
     */
    public void setLeaseTerm(int leaseTerm) {
        this.leaseTerm = leaseTerm;
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
     * 取得是否使用现金券
     *
     * @return 是否使用现金券
     */
    public String getUseCoupon() {
        return useCoupon;
    }

    /**
     * 设定是否使用现金券
     *
     * @param useCoupon 是否使用现金券
     */
    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
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

    /**
     * 取得更新时间
     *
     * @return 更新时间
     */
    public long getUpdateTime() {
        return updateTime;
    }

    /**
     * 设定更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
