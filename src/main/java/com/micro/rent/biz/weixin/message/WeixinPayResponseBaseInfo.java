package com.micro.rent.biz.weixin.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信支付相关接口响应信息基类
 *
 * @author liqianxi
 * @date 2014-12-26
 */
@XStreamAlias("xml")
public class WeixinPayResponseBaseInfo {

    /**
     * 返回状态码
     */
    @XStreamAlias("return_code")
    private String returnCode;

    /**
     * 返回信息
     */
    @XStreamAlias("return_msg")
    private String returnMsg;

    /**
     * 公众账号ID
     */
    @XStreamAlias("appid")
    private String appId;

    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    private String mchId;

    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    private String nonceStr;

    /**
     * 签名
     */
    private String sign;

    /**
     * 业务结果
     */
    @XStreamAlias("result_code")
    private String resultCode;

    /**
     * 错误代码
     */
    @XStreamAlias("err_code")
    private String errCode;

    /**
     * 错误代码描述
     */
    @XStreamAlias("err_code_des")
    private String errCodeDes;

    /**
     * 取得返回状态码
     *
     * @return 返回状态码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设定返回状态码
     *
     * @param returnCode 返回状态码
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 取得返回信息
     *
     * @return 返回信息
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * 设定返回信息
     *
     * @param returnMsg 返回信息
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * 取得公众账号ID
     *
     * @return 公众账号ID
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设定公众账号ID
     *
     * @param appId 公众账号ID
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 取得商户号
     *
     * @return 商户号
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * 设定商户号
     *
     * @param mchId 商户号
     */
    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    /**
     * 取得随机字符串
     *
     * @return 随机字符串
     */
    public String getNonceStr() {
        return nonceStr;
    }

    /**
     * 设定随机字符串
     *
     * @param nonceStr 随机字符串
     */
    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    /**
     * 取得签名
     *
     * @return 签名
     */
    public String getSign() {
        return sign;
    }

    /**
     * 设定签名
     *
     * @param sign 签名
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 取得业务结果
     *
     * @return 业务结果
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * 设定业务结果
     *
     * @param resultCode 业务结果
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * 取得错误代码
     *
     * @return 错误代码
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * 设定错误代码
     *
     * @param errCode 错误代码
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 取得错误代码描述
     *
     * @return 错误代码描述
     */
    public String getErrCodeDes() {
        return errCodeDes;
    }

    /**
     * 设定错误代码描述
     *
     * @param errCodeDes 错误代码描述
     */
    public void setErrCodeDes(String errCodeDes) {
        this.errCodeDes = errCodeDes;
    }

}
