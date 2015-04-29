package com.micro.rent.biz.weixin.web;

import com.micro.rent.biz.enum_.ETradeStatus;
import com.micro.rent.biz.enum_.EWeixinTradeState;
import com.micro.rent.biz.personal.service.TradeService;
import com.micro.rent.biz.weixin.message.WeixinPayResponseBaseInfo;
import com.micro.rent.biz.weixin.message.receive.pay.OrderInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundInfo;
import com.micro.rent.biz.weixin.message.receive.pay.UnifyInfo;
import com.micro.rent.biz.weixin.service.WeixinPayService;
import com.micro.rent.common.support.BusinessUtil;
import com.micro.rent.common.support.MD5Util;
import com.micro.rent.common.support.Sha1Util;
import com.micro.rent.common.support.XstreamHelper;
import com.micro.rent.common.web.controller._BaseController;
import com.micro.rent.dbaccess.entity.pay.PayLog;
import com.micro.rent.dbaccess.entity.pay.Payment;
import com.micro.rent.dbaccess.entity.personal.Trade;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 微信支付相关接口
 *
 * @author liqianxi
 * @date 2014-12-25
 */
@Controller
@RequestMapping("/pay")
public class WeixinPayController extends _BaseController {

    /**
     * 微信返回成功标识
     */
    private static final String SUCCESS = "SUCCESS";
    private transient final Logger log = LoggerFactory.getLogger(this
            .getClass());
    /**
     * 应用id
     */
    @Value("${appid}")
    private String appid;

    /**
     * 预订
     */
    @Autowired
    private TradeService tradeService;

    /**
     * 微信支付
     */
    @Autowired
    private WeixinPayService weixinPayService;

    /**
     * 支付入口<br/>
     * <b>测试支付功能时，request中传入forTest=true</b>
     */
    @RequestMapping("/entry")
    public ModelAndView payEntry(HttpServletRequest request,
                                 HttpServletResponse response) {
        ModelAndView mv = createMAV("pay/pay");
        String errorMessage = supportWeixinPay(request);
        if (errorMessage != null) {
            mv.addObject("error", true);
            mv.addObject("errorMessage", errorMessage);
            return mv;
        }

        SortedMap<String, String> unifiedOrderMap = new TreeMap<String, String>();
        // 设定参数
        setUnifiedOrderMap(unifiedOrderMap, request);
        // 调用统一支付接口，并取得返回的预支付ID
        String prepayId = weixinPayService.unifiedOrder(unifiedOrderMap);

        if (prepayId != null) {
            SortedMap<String, String> signParams = new TreeMap<String, String>();
            signParams.put("appId", appid);
            signParams.put("timeStamp",
                    String.valueOf((System.currentTimeMillis() / 1000)));
            signParams.put("nonceStr", Sha1Util.getNonceStr());
            signParams.put("package", "prepay_id=" + prepayId);
            signParams.put("signType", "MD5");

            mv.addObject("appId", appid);
            mv.addObject("timeStamp", signParams.get("timeStamp"));
            mv.addObject("nonceStr", signParams.get("nonceStr"));
            mv.addObject("package", signParams.get("package"));
            mv.addObject("signType", signParams.get("signType"));
            mv.addObject("paySign",
                    MD5Util.createMD5Sign(signParams, true, true));

            int totalFee = NumberUtils.toInt(findStringParameterValue(request,
                    "totalFee"));
            int couponFee = NumberUtils.toInt(findStringParameterValue(request,
                    "couponFee"));
            mv.addObject("totalFee", totalFee);
            mv.addObject("couponFee", couponFee);
            mv.addObject("payFee", totalFee - couponFee);
            // 显示房屋名称
            mv.addObject("displayHouseName",
                    findStringParameterValue(request, "displayHouseName"));
        } else {
            mv.addObject("error", true);
            mv.addObject("errorMessage", "预订失败，请重新预订！");
        }

        return mv;
    }

    /**
     * 订单查询
     */
    @RequestMapping("/orderQuery")
    public ModelAndView orderQuery(HttpServletRequest request,
                                   HttpServletResponse response) {
        ModelAndView mv = createMAV("pay/tradeQueryResult");
        SortedMap<String, String> orderQueryMap = new TreeMap<String, String>();
        // 设定参数
        setOrderQueryMap(orderQueryMap, request);

        OrderInfo orderInfo = weixinPayService.orderQuery(orderQueryMap);
        if (orderInfo != null && SUCCESS.equals(orderInfo.getReturnCode())
                && SUCCESS.equals(orderInfo.getResultCode())) {
            mv.addObject("tradeState",
                    EWeixinTradeState.getNameByCode(orderInfo.getTradeState()));
            mv.addObject("transactionId", orderInfo.getTransactionId());
            mv.addObject("tradeNo", orderInfo.getOutTradeNo());
            mv.addObject("totalFee", NumberUtils.toInt(orderInfo.getTotalFee()));
            mv.addObject("couponFee",
                    NumberUtils.toInt(orderInfo.getCouponFee()));
        } else {
            mv.addObject("error", true);
            mv.addObject("errorMessage", "订单查询失败，请稍后再次查询！");
        }

        return mv;
    }

    /**
     * 退款
     */
    @RequestMapping("/refund")
    public ModelAndView refund(HttpServletRequest request,
                               HttpServletResponse response) {
        ModelAndView mv = createMAV("pay/refund");
        String tradeNo = findStringParameterValue(request, "tradeNo");
        // 查看该订单状态
        Trade tradeInfo = tradeService.selectTradeInfoByTradeNo(tradeNo);
        if (tradeInfo != null) {
            SortedMap<String, String> refundMap = new TreeMap<String, String>();
            // 设定参数
            setRefundMap(refundMap, request);
            // 退款
            RefundInfo refundInfo = weixinPayService.refund(refundMap);
            log.info("RefundInfo--退款申请--xml解析完毕");
            if (refundInfo != null
                    && SUCCESS.equals(refundInfo.getReturnCode())
                    && SUCCESS.equals(refundInfo.getResultCode())) {
                // 更新订单状态为“已取消”
                if (ETradeStatus.PAY.getCode().equals(tradeInfo.getStatus())) {
                    updateTradeStatus(tradeNo, ETradeStatus.CANCEL.getCode());
                }
                // 查询退款记录信息
                Payment payRecorde = weixinPayService
                        .selectPaymentInfo(refundInfo.getOutRefundNo());
                if (payRecorde == null) {
                    // 添加退款记录
                    addRefundInfo(refundInfo);
                }

                // 设定MV
                mv.addObject("refundId", refundInfo.getRefundId());
                mv.addObject("refundFee",
                        NumberUtils.toInt(refundInfo.getRefundFee()));
            } else {
                mv.addObject("error", true);
                mv.addObject("errorMessage", "申请退款失败，请稍后重新申请！");
            }

        } else {
            mv.addObject("error", true);
            mv.addObject("errorMessage", "申请退款失败，请稍后重新申请！");
        }

        return mv;
    }

    /**
     * 通用通知接口
     */
    @RequestMapping("/unify")
    @ResponseBody
    public String unify(HttpServletRequest request, HttpServletResponse response) {
        log.info("pay/unify----start---");
        String requestContent = null;
        try {
            requestContent = inputStream2String(request.getInputStream());
        } catch (IOException e) {
            log.error("取微信发送请求内容失败!");
            return createResponseContent(false, "微信请求读取失败！");
        }

        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, UnifyInfo.class});
        UnifyInfo unifyInfo = new UnifyInfo();
        try {
            unifyInfo = (UnifyInfo) xstream.fromXML(requestContent);
        } catch (Exception e) {
            log.error("通用通知接口xml解析出错 [{}]", new Object[]{requestContent});
            e.printStackTrace();
        }

        // 记录访问日志
        addPayLogInfo(unifyInfo, requestContent, request.getRequestURL()
                .toString());

        // 验证返回状态码、业务结果
        if (!SUCCESS.equals(unifyInfo.getReturnCode())
                || !SUCCESS.equals(unifyInfo.getResultCode())) {
            log.error("微信通用通知接口返回错误内容[return_msg:" + unifyInfo.getReturnMsg()
                    + ",err_code:" + unifyInfo.getErrCode() + ",err_code_des:"
                    + unifyInfo.getErrCodeDes());
            return createResponseContent(false, "");
        }

        // 验证签名
        if (!checkSign(unifyInfo)) {
            log.error("签名验证失败！");
            return createResponseContent(false, "签名验证失败！");
        }

        // 查询该订单状态
        String woniuTradeNo = unifyInfo.getOutTradeNo();
        Trade tradeInfo = tradeService.selectTradeInfoByTradeNo(woniuTradeNo);
        if (tradeInfo == null) {
            log.error("订单状态查询失败！");
            return createResponseContent(false, "订单状态查询失败！");
        }

        // 订单已支付成功
        if (ETradeStatus.PAY.getCode().equals(tradeInfo.getStatus())) {
            log.info("订单已支付！");
            return createResponseContent(true, null);
        }

        // 查询支付记录信息
        Payment payRecorde = weixinPayService.selectPaymentInfo(woniuTradeNo);
        if (payRecorde == null) {
            // 添加支付信息
            addPaymentInfo(unifyInfo);
        }
        // 更新支付状态
        updateTradeStatus(unifyInfo.getOutTradeNo(), ETradeStatus.PAY.getCode());

        log.info("订单支付成功！");
        return createResponseContent(true, null);
    }

    /**
     * 支付成功跳转处理
     */
    @RequestMapping("/confirmPay")
    public ModelAndView payConfirm(HttpServletRequest request) {
        ModelAndView mv = createMAV("pay/payConfirm");
        mv.addObject("payFee",
                NumberUtils.toInt(findStringParameterValue(request, "payFee")));
        return mv;
    }

    /**
     * 设定统一支付接口所需参数
     *
     * @param unifiedOrderMap
     * @param request
     */
    private void setUnifiedOrderMap(SortedMap<String, String> unifiedOrderMap,
                                    HttpServletRequest request) {
        // 微信ID
        String weixinId = findStringParameterValue(request, "weixinId");
        // 房屋ID
        String houseId = findStringParameterValue(request, "houseId");
        String tradeNo1 = this.findStringParameterValue(request, "tradeNo");
        Trade trade = tradeService.selectTradeInfoByTradeNo(tradeNo1);
        String tradeNo = "";
        Integer payFee = 0;
        if (trade == null) {
            Trade tradeInfo = new Trade();
            tradeInfo.setChannelCode(findStringParameterValue(request,
                    "channelCode"));
            tradeInfo.setGender(findStringParameterValue(request, "gender"));
            tradeInfo.setHouseId(houseId);
            tradeInfo.setLeaseTerm(NumberUtils.toInt(findStringParameterValue(
                    request, "leaseTerm")));
            tradeInfo.setName(findStringParameterValue(request, "name"));
            tradeInfo
                    .setNickName(findStringParameterValue(request, "nickName"));
            tradeInfo.setTelephone(findStringParameterValue(request,
                    "telephone"));
            int totalFeeInt = NumberUtils.toInt(findStringParameterValue(
                    request, "totalFee"));
            int couponFeeInt = NumberUtils.toInt(findStringParameterValue(
                    request, "couponFee"));
            tradeInfo.setTotalFee(totalFeeInt);
            tradeInfo.setCouponFee(couponFeeInt);
            tradeInfo.setPayFee(totalFeeInt - couponFeeInt);
            // 0:不使用现金券 1:使用现金券
            tradeInfo.setUseCoupon(couponFeeInt == 0 ? "0" : "1");
            tradeInfo.setWeixinId(weixinId);
            // 测试标识
            Boolean forTest = findBooleanParameterValue(request, "forTest");
            // 生成订单，并返回订单号
            tradeNo = tradeService.addTradeInfo(tradeInfo,
                    forTest == null ? false : forTest.booleanValue());
            payFee = tradeInfo.getPayFee();
        } else {
            tradeNo = trade.getTradeNo();
            payFee = trade.getPayFee();
        }

        // 商品描述
        unifiedOrderMap.put("body", houseId);
        // 商户订单号
        unifiedOrderMap.put("out_trade_no", tradeNo);
        // 总金额额度（设定为支付额度？）
        unifiedOrderMap.put("total_fee", String.valueOf(payFee));
        // 终端IP
        unifiedOrderMap.put("spbill_create_ip", request.getRemoteAddr());
        // 通知地址
        unifiedOrderMap.put("notify_url", getUnifyUrl(request));
        // 交易类型
        unifiedOrderMap.put("trade_type",
                findStringParameterValue(request, "tradeType"));
        // 微信ID
        if (StringUtils.isNotEmpty(weixinId)) {
            unifiedOrderMap.put("openid", weixinId);
        }
    }

    /**
     * 设定订单查询口所需参数
     *
     * @param orderQueryMap
     * @param request
     */
    private void setOrderQueryMap(SortedMap<String, String> orderQueryMap,
                                  HttpServletRequest request) {
        if (StringUtils.isNotBlank(findStringParameterValue(request,
                "transactionId"))) {
            orderQueryMap.put("transaction_id",
                    findStringParameterValue(request, "transactionId"));
        }
        orderQueryMap.put("out_trade_no",
                findStringParameterValue(request, "tradeNo"));
    }

    /**
     * 设定退款申请接口所需参数
     *
     * @param refundMap
     * @param request
     */
    private void setRefundMap(SortedMap<String, String> refundMap,
                              HttpServletRequest request) {
        // 更新订单状态
        String tradeNo = findStringParameterValue(request, "tradeNo");
        if (StringUtils.isNotBlank(findStringParameterValue(request,
                "transactionId"))) {
            refundMap.put("transaction_id",
                    findStringParameterValue(request, "transactionId"));
        }
        refundMap.put("out_trade_no", tradeNo);
        // 退款单号（"R" + 订单号）
        refundMap.put("out_refund_no", "R" + tradeNo);
        refundMap.put("total_fee",
                findStringParameterValue(request, "totalFee"));
        refundMap
                .put("refund_fee", findStringParameterValue(request, "payFee"));
    }

    /**
     * 通知地址
     */
    private String getUnifyUrl(HttpServletRequest request) {
        String serverRoot = BusinessUtil.getServerRootURL(request
                .getContextPath());
        return serverRoot + "/pay/unify";
    }

    /**
     * 输入流转换为字符串
     *
     * @param is 输入流
     * @return 字符串
     * @throws IOException
     */
    private String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }

        in.close();
        return buffer.toString();
    }

    /**
     * 验证签名
     *
     * @return true：签名验证成功/false：签名验证失败
     */
    private boolean checkSign(UnifyInfo unifyInfo) {
        SortedMap<String, String> sortedMap = new TreeMap<String, String>();
        sortedMap.put("return_code", unifyInfo.getReturnCode());
        sortedMap.put("appid", unifyInfo.getAppId());
        sortedMap.put("mch_id", unifyInfo.getMchId());
        if (StringUtils.isNotEmpty(unifyInfo.getDeviceInfo())) {
            sortedMap.put("device_info", unifyInfo.getDeviceInfo());
        }
        sortedMap.put("nonce_str", unifyInfo.getNonceStr());
        sortedMap.put("result_code", unifyInfo.getResultCode());
        sortedMap.put("openid", unifyInfo.getOpenId());
        sortedMap.put("is_subscribe", unifyInfo.getIsSubscribe());
        sortedMap.put("trade_type", unifyInfo.getTradeType());
        sortedMap.put("bank_type", unifyInfo.getBankType());
        sortedMap.put("total_fee", unifyInfo.getTotalFee());
        if (StringUtils.isNotEmpty(unifyInfo.getCouponFee())) {
            sortedMap.put("coupon_fee", unifyInfo.getCouponFee());
        }
        if (StringUtils.isNotEmpty(unifyInfo.getFeeType())) {
            sortedMap.put("fee_type", unifyInfo.getFeeType());
        }
        sortedMap.put("transaction_id", unifyInfo.getTransactionId());
        sortedMap.put("out_trade_no", unifyInfo.getOutTradeNo());
        if (StringUtils.isNotEmpty(unifyInfo.getAttach())) {
            sortedMap.put("attach", unifyInfo.getAttach());
        }
        sortedMap.put("time_end", unifyInfo.getTimeEnd());
        if (StringUtils.isNotEmpty(unifyInfo.getCashFee())) {
            sortedMap.put("cash_fee", unifyInfo.getCashFee());
        }
        if (StringUtils.isNotEmpty(unifyInfo.getReturnMsg())) {
            sortedMap.put("return_msg", unifyInfo.getReturnMsg());
        }
        if (StringUtils.isNotEmpty(unifyInfo.getErrCodeDes())) {
            sortedMap.put("err_code_des", unifyInfo.getErrCodeDes());
        }
        if (StringUtils.isNotEmpty(unifyInfo.getErrCode())) {
            sortedMap.put("err_code", unifyInfo.getErrCode());
        }

        return unifyInfo.getSign().equals(
                MD5Util.createMD5Sign(sortedMap, true, true));
    }

    /**
     * 生成响应内容
     *
     * @param success   是否成功
     * @param returnMsg 错误原因
     * @return 响应内容
     */
    private String createResponseContent(boolean success, String returnMsg) {
        StringBuilder result = new StringBuilder("<xml>");
        if (success) {
            result.append("<return_code>");
            result.append("SUCCESS");
            result.append("</return_code>");
        } else {
            result.append("<return_code>");
            result.append("FAIL");
            result.append("</return_code>");
            result.append("<return_msg>");
            result.append("<![CDATA[").append(returnMsg).append("]]>");
            result.append("</return_msg>");
        }
        result.append("</xml>");
        return result.toString();
    }

    /**
     * 添加支付日志记录
     *
     * @param info
     * @param requestContent 请求内容
     * @param requestUrl     请求URL
     */
    private void addPayLogInfo(UnifyInfo info, String requestContent,
                               String requestUrl) {
        PayLog payLog = new PayLog();
        payLog.setTradeNo(info.getOutTradeNo());
        payLog.setWeixinId(info.getOpenId());
        payLog.setTradeType(info.getTradeType());
        payLog.setRequestApi(requestUrl);
        payLog.setRequestContent(requestContent);

        log.info("添加支付日志记录：" + requestContent);
        weixinPayService.addPayLogInfo(payLog);
    }

    /**
     * 添加退款记录
     *
     * @param info
     */
    private void addRefundInfo(RefundInfo info) {
        Payment payment = new Payment();
        payment.setCouponFee(-NumberUtils.toInt(info.getCouponRefundFee()));
        payment.setTotalFee(-NumberUtils.toInt(info.getRefundFee()));
        payment.setPayFee(payment.getTotalFee() - payment.getCouponFee());
        payment.setTradeNo(info.getOutRefundNo());
        payment.setTransactionId(info.getRefundId());
        weixinPayService.addPaymentInfo(payment);
    }

    /**
     * 添加支付记录
     *
     * @param info 支付信息
     */
    private void addPaymentInfo(UnifyInfo info) {
        Payment payment = new Payment();
        payment.setBankType(info.getBankType());
        payment.setCouponFee(NumberUtils.toInt(info.getCouponFee()));
        payment.setIsSubscribe(info.getIsSubscribe());
        payment.setTotalFee(NumberUtils.toInt(info.getTotalFee()));
        payment.setPayFee(payment.getTotalFee() - payment.getCouponFee());
        payment.setTradeNo(info.getOutTradeNo());
        payment.setTransactionId(info.getTransactionId());
        payment.setWeixinId(info.getOpenId());

        log.info("添加支付记录：" + info.getOutTradeNo());
        weixinPayService.addPaymentInfo(payment);
    }

    /**
     * 更新订单状态
     *
     * @param tradeNo 订单号
     * @param status  订单状态
     */
    private void updateTradeStatus(String tradeNo, String status) {
        Trade tradeInfo = new Trade();
        tradeInfo.setStatus(status);
        tradeInfo.setTradeNo(tradeNo);
        tradeService.updateTradeStatus(tradeInfo);
    }

    /**
     * 验证用户微信客户端是否支持支付功能<br/>
     * 微信5.0版(含)以上支持支付功能
     *
     * @param request
     * @return 非null:提示消息
     */
    private String supportWeixinPay(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        String weixinVersionPrefix = "MicroMessenger/";
        int winxinVersionIndex = userAgent.indexOf(weixinVersionPrefix);
        if (winxinVersionIndex == -1) {
            return "请使用微信内置浏览器打开！";
        }

        // 微信客户端主版本号
        char weixinMainVersionChar = new String(
                userAgent.substring(winxinVersionIndex
                        + weixinVersionPrefix.length())).charAt(0);
        return weixinMainVersionChar > '4' ? null
                : "您正在使用的微信客户端版本不支持微信支付功能，请升级微信客户端后再进行支付！";
    }
}
