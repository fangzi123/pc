package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.message.WeixinPayResponseBaseInfo;
import com.micro.rent.biz.weixin.message.receive.pay.OrderInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundQueryInfo;
import com.micro.rent.biz.weixin.service.WeixinPayService;
import com.micro.rent.common.support.MD5Util;
import com.micro.rent.common.support.Sha1Util;
import com.micro.rent.common.support.XstreamHelper;
import com.micro.rent.dbaccess.dao.pay.WeixinPayDao;
import com.micro.rent.dbaccess.entity.pay.PayLog;
import com.micro.rent.dbaccess.entity.pay.Payment;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.KeyStore;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;

/**
 * 微信支付
 *
 * @author liqianxi
 * @date 2014-12-24
 */
@Service
public class WeixinPayServiceImpl implements WeixinPayService {

    /**
     * 响应内容中微信预支付ID的XML开始元素
     */
    private static final String PREPAY_ID_XML_START_PREFIX = "<prepay_id><![CDATA[";
    /**
     * 响应内容中微信预支付ID项的XML结束元素
     */
    private static final String PREPAY_ID_XML_END_PREFIX = "]]></prepay_id>";
    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * 应用id
     */
    @Value("${appid}")
    private String appid;

    /**
     * 商户号
     */
    @Value("${weixin.pay.mchId}")
    private String mchId;

    /**
     * 操作员
     */
    @Value("${weixin.pay.opUserId}")
    private String opUserId;

    /**
     * 微信统一支付接口URL
     */
    @Value("${weixin.pay.unifiedOrderUrl}")
    private String weixinUnifiedOrderUrl;

    /**
     * 微信订单查询接口URL
     */
    @Value("${weixin.pay.orderQueryUrl}")
    private String weixinOrderQueryUrl;

    /**
     * 微信退款申请接口URL
     */
    @Value("${weixin.pay.refundUrl}")
    private String weixinRefundUrl;

    /**
     * 微信退款查询接口URL
     */
    @Value("${weixin.pay.refundQueryUrl}")
    private String weixinRefundQueryUrl;

    /**
     * 微信支付DAO
     */
    @Autowired
    private WeixinPayDao weixinPayDao;

    /**
     * 添加支付信息
     *
     * @param payment 支付信息
     */
    @Override
    public void addPaymentInfo(Payment payment) {
        payment.setCreateTime(System.currentTimeMillis() / 1000);
        weixinPayDao.insertPayment(payment);
    }

    /**
     * 取得支付信息
     *
     * @param tradeNo 订单号
     * @return 支付信息
     */
    @Override
    public Payment selectPaymentInfo(String tradeNo) {
        return weixinPayDao.selectPaymentInfo(tradeNo);
    }

    /**
     * 添加支付日志记录
     *
     * @param paylLog 支付日志信息
     */
    @Override
    public void addPayLogInfo(PayLog paylLog) {
        paylLog.setCreateTime(System.currentTimeMillis() / 1000);
        weixinPayDao.insertPayLog(paylLog);
    }

    /**
     * 统一支付
     *
     * @param 统一支付接口请求信息
     * @return 统一支付信息
     */
    @SuppressWarnings("deprecation")
    @Override
    public String unifiedOrder(SortedMap<String, String> unifiedOrderMap) {
        // 交易类型
        if (!unifiedOrderMap.containsKey("trade_type")
                || StringUtils.isEmpty(unifiedOrderMap.get("trade_type"))) {
            unifiedOrderMap.put("trade_type", "JSAPI");
        }

        setRequestCommonElement(unifiedOrderMap);

        // 参数验证
        if (!checkUnifiedOrderParams(unifiedOrderMap)) {
            return null;
        }

        // 请求微信统一支付接口URL
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(weixinUnifiedOrderUrl);
        postMethod.setRequestBody(toXmlString(unifiedOrderMap));
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("微信统一支付失败" + "[" + statusCode + "]");
            } else {
                String responseString = new String(postMethod
                        .getResponseBodyAsString().getBytes(
                                postMethod.getResponseCharSet()));
                log.info("微信统一支付接口响应：" + responseString);
                return getPrePayId(responseString);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 订单查询
     *
     * @param 订单查询接口请求信息
     * @return 订单信息
     */
    @SuppressWarnings("deprecation")
    @Override
    public OrderInfo orderQuery(SortedMap<String, String> orderQueryMap) {
        setRequestCommonElement(orderQueryMap);

        // 参数验证
        if (!checkOrderQueryParams(orderQueryMap)) {
            return null;
        }

        // 请求微信订单查询接口URL
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(weixinOrderQueryUrl);
        postMethod.setRequestBody(toXmlString(orderQueryMap));
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("微信订单查询失败" + "[" + statusCode + "]");
            } else {
                String responseString = new String(postMethod
                        .getResponseBodyAsString().getBytes(
                                postMethod.getResponseCharSet()));
                log.info("微信订单查询接口响应：" + responseString);
                return getOrderInfo(responseString);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 退款申请
     *
     * @param 退款申请接口请求信息
     * @return 退款信息
     */
    @SuppressWarnings("deprecation")
    @Override
    public RefundInfo refund(SortedMap<String, String> refundMap) {

        // 操作员
        refundMap.put("op_user_id", opUserId);
        setRequestCommonElement(refundMap);

        // 参数验证
        if (!checkRefundParams(refundMap)) {
            return null;
        }

        // 请求微信退款申请接口URL
        // 加载微信连接证书
        InputStream inputStream;
        KeyStore keyStore;
        CloseableHttpClient httpClient;
        try {
            // add ca path
            inputStream = WeixinPayServiceImpl.class
                    .getResourceAsStream("/weixinPayCA/apiclient_cert.p12");
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(inputStream, mchId.toCharArray());
            // Trust own CA and all self-signed certs
            SSLContext sslContext = SSLContexts.custom()
                    .loadKeyMaterial(keyStore, mchId.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(
                    sslContext,
                    new String[]{"TLSv1"},
                    null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory).build();
        } catch (Exception e) {
            log.error("加载微信认账证书失败", e);
            return null;
        }

        try {
            HttpPost postMethod = new HttpPost(weixinRefundUrl);
            postMethod.setEntity(new StringEntity(toXmlString(refundMap),
                    "text/xml", "utf-8"));
            CloseableHttpResponse response = httpClient.execute(postMethod);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("微信退款申请失败" + "[" + statusCode + "]");
            } else {
                String responseString = EntityUtils.toString(
                        response.getEntity(), "utf-8");
                log.info("微信退款申请接口响应：" + responseString);
                return getRefundInfo(responseString);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 退款查询
     *
     * @param 退款查询接口请求信息
     * @return 退款单信息
     */
    @SuppressWarnings("deprecation")
    @Override
    public RefundQueryInfo refundQuery(SortedMap<String, String> refundQueryMap) {
        setRequestCommonElement(refundQueryMap);

        // 参数验证
        if (!checkRefundQueryParams(refundQueryMap)) {
            return null;
        }

        // 请求微信退款查询接口URL
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(weixinRefundUrl);
        postMethod.setRequestBody(toXmlString(refundQueryMap));
        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.error("微信退款查询失败" + "[" + statusCode + "]");
            } else {
                String responseString = new String(postMethod
                        .getResponseBodyAsString().getBytes(
                                postMethod.getResponseCharSet()));
                log.info("微信退款查询接口响应：" + responseString);
                return getRefundQueryInfo(responseString);
            }

        } catch (HttpException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 设置微信接口请求中的共同项目<br/>
     * <b>各个接口的个别项目设定完毕后调用</b>
     *
     * @param sortedMap
     */
    private void setRequestCommonElement(SortedMap<String, String> sortedMap) {
        // 公众账号ID
        sortedMap.put("appid", appid);
        // 商户号
        sortedMap.put("mch_id", mchId);
        // 随机数
        sortedMap.put("nonce_str", Sha1Util.getNonceStr());
        // 生成支付签名
        sortedMap.put("sign", MD5Util.createMD5Sign(sortedMap, true, true));
    }

    private String toXmlString(SortedMap<String, String> sortedMap) {
        StringBuilder strBul = new StringBuilder("<xml>");
        Iterator<Entry<String, String>> iterator = sortedMap.entrySet()
                .iterator();
        while (iterator.hasNext()) {
            Entry<String, String> item = iterator.next();
            strBul.append("<").append(item.getKey()).append(">");
            strBul.append("<![CDATA[").append(item.getValue()).append("]]>");
            strBul.append("</").append(item.getKey()).append(">");
        }
        strBul.append("</xml>");

        return strBul.toString();
    }

    // 统一支付接口用

    /**
     * 验证统一支付接口请求参数的正确性
     *
     * @param unifiedOrderMap 统一支付接口请求参数
     * @return true：正确/false：不正确
     */
    private boolean checkUnifiedOrderParams(
            SortedMap<String, String> unifiedOrderMap) {
        // 公众账号ID
        if (StringUtils.isEmpty(unifiedOrderMap.get("appid"))) {
            log.error("微信统一支付接口请求参数验证错误[公众账号ID]");
            return false;
        }
        // 商户号
        if (StringUtils.isEmpty(unifiedOrderMap.get("mch_id"))) {
            log.error("微信统一支付接口请求参数验证错误[商户号]");
            return false;
        }
        // 随机字符串
        if (StringUtils.isEmpty(unifiedOrderMap.get("nonce_str"))) {
            log.error("微信统一支付接口请求参数验证错误[随机字符串]");
            return false;
        }
        // 签名
        if (StringUtils.isEmpty(unifiedOrderMap.get("sign"))) {
            log.error("微信统一支付接口请求参数验证错误[签名]");
            return false;
        }
        // 商品描述
        if (StringUtils.isEmpty(unifiedOrderMap.get("body"))) {
            log.error("微信统一支付接口请求参数验证错误[商品描述]");
            return false;
        }
        // 商户订单号
        if (StringUtils.isEmpty(unifiedOrderMap.get("out_trade_no"))) {
            log.error("微信统一支付接口请求参数验证错误[商户订单号]");
            return false;
        }
        // 总金额
        if (StringUtils.isEmpty(unifiedOrderMap.get("total_fee"))) {
            log.error("微信统一支付接口请求参数验证错误[总金额]");
            return false;
        }
        // 终端IP
        if (StringUtils.isEmpty(unifiedOrderMap.get("spbill_create_ip"))) {
            log.error("微信统一支付接口请求参数验证错误[终端IP]");
            return false;
        }
        // 通知地址
        if (StringUtils.isEmpty(unifiedOrderMap.get("notify_url"))) {
            log.error("微信统一支付接口请求参数验证错误[通知地址]");
            return false;
        }
        // 交易类型
        if (StringUtils.isEmpty(unifiedOrderMap.get("trade_type"))) {
            log.error("微信统一支付接口请求参数验证错误[交易类型]");
            return false;
        }

        return true;
    }

    /**
     * 取得微信统一支付接口生成的预支付ID
     *
     * @param 微信响应内容
     * @return 微信生成的预支付ID
     */
    private String getPrePayId(String response) {
        int prepayIdStrartIndex = response.indexOf(PREPAY_ID_XML_START_PREFIX);
        if (prepayIdStrartIndex != -1) {
            int prepayIdEndIndex = response.indexOf(PREPAY_ID_XML_END_PREFIX);
            String prepayId = new String(response.substring(prepayIdStrartIndex
                    + PREPAY_ID_XML_START_PREFIX.length(), prepayIdEndIndex));
            return prepayId;
        }

        return null;
    }

    // 订单查询接口用

    /**
     * 验证订单查询接口请求参数的正确性
     *
     * @param orderQueryMap 订单查询接口请求参数
     * @return true：正确/false：不正确
     */
    private boolean checkOrderQueryParams(
            SortedMap<String, String> orderQueryMap) {
        // 公众账号ID
        if (StringUtils.isEmpty(orderQueryMap.get("appid"))) {
            log.error("微信订单查询接口请求参数验证错误[公众账号ID]");
            return false;
        }
        // 商户号
        if (StringUtils.isEmpty(orderQueryMap.get("mch_id"))) {
            log.error("微信订单查询接口请求参数验证错误[商户号]");
            return false;
        }
        // 随机字符串
        if (StringUtils.isEmpty(orderQueryMap.get("nonce_str"))) {
            log.error("微信订单查询接口请求参数验证错误[随机字符串]");
            return false;
        }
        // 签名
        if (StringUtils.isEmpty(orderQueryMap.get("sign"))) {
            log.error("微信订单查询接口请求参数验证错误[签名]");
            return false;
        }
        // 商户订单号
        if (StringUtils.isEmpty(orderQueryMap.get("out_trade_no"))) {
            log.error("微信订单查询接口请求参数验证错误[商户订单号]");
            return false;
        }

        return true;
    }

    /**
     * 取得微信订单查询接口的响应信息
     *
     * @param response 微信响应内容
     * @return 订单信息
     */
    private OrderInfo getOrderInfo(String response) {
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, OrderInfo.class});
        return (OrderInfo) xstream.fromXML(response);
    }

    // 退款申请接口用

    /**
     * 验证退款申请接口请求参数的正确性
     *
     * @param refundMap 退款申请接口请求参数
     * @return true：正确/false：不正确
     */
    private boolean checkRefundParams(SortedMap<String, String> refundMap) {
        // 公众账号ID
        if (StringUtils.isEmpty(refundMap.get("appid"))) {
            log.error("微信退款申请接口请求参数验证错误[公众账号ID]");
            return false;
        }
        // 商户号
        if (StringUtils.isEmpty(refundMap.get("mch_id"))) {
            log.error("微信退款申请接口请求参数验证错误[商户号]");
            return false;
        }
        // 随机字符串
        if (StringUtils.isEmpty(refundMap.get("nonce_str"))) {
            log.error("微信退款申请接口请求参数验证错误[随机字符串]");
            return false;
        }
        // 签名
        if (StringUtils.isEmpty(refundMap.get("sign"))) {
            log.error("微信退款申请接口请求参数验证错误[签名]");
            return false;
        }
        // 商户订单号
        if (StringUtils.isEmpty(refundMap.get("out_trade_no"))) {
            log.error("微信退款申请接口请求参数验证错误[商户订单号]");
            return false;
        }
        // 商户退款单号
        if (StringUtils.isEmpty(refundMap.get("out_refund_no"))) {
            log.error("微信退款申请接口请求参数验证错误[商户退款单号]");
            return false;
        }
        // 总金额
        if (StringUtils.isEmpty(refundMap.get("total_fee"))) {
            log.error("微信退款申请接口请求参数验证错误[总金额]");
            return false;
        }
        // 退款金额
        if (StringUtils.isEmpty(refundMap.get("refund_fee"))) {
            log.error("微信退款申请接口请求参数验证错误[退款金额]");
            return false;
        }
        // 操作员
        if (StringUtils.isEmpty(refundMap.get("op_user_id"))) {
            log.error("微信退款申请接口请求参数验证错误[操作员]");
            return false;
        }

        return true;
    }

    /**
     * 取得微信退款申请接口的响应信息
     *
     * @param response 微信响应内容
     * @return 退款信息
     */
    private RefundInfo getRefundInfo(String response) {
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, RefundInfo.class});
        return (RefundInfo) xstream.fromXML(response);
    }

    // 退款查询接口用

    /**
     * 验证退款查询接口请求参数的正确性
     *
     * @param refundQueryMap 退款查询接口请求参数
     * @return true：正确/false：不正确
     */
    private boolean checkRefundQueryParams(
            SortedMap<String, String> refundQueryMap) {
        // 公众账号ID
        if (StringUtils.isEmpty(refundQueryMap.get("appid"))) {
            log.error("微信退款查询接口请求参数验证错误[公众账号ID]");
            return false;
        }
        // 商户号
        if (StringUtils.isEmpty(refundQueryMap.get("mch_id"))) {
            log.error("微信退款查询接口请求参数验证错误[商户号]");
            return false;
        }
        // 随机字符串
        if (StringUtils.isEmpty(refundQueryMap.get("nonce_str"))) {
            log.error("微信退款查询接口请求参数验证错误[随机字符串]");
            return false;
        }
        // 签名
        if (StringUtils.isEmpty(refundQueryMap.get("sign"))) {
            log.error("微信退款查询接口请求参数验证错误[签名]");
            return false;
        }
        // 签名
        if (StringUtils.isEmpty(refundQueryMap.get("transaction_id"))
                && StringUtils.isEmpty(refundQueryMap.get("out_trade_no"))
                && StringUtils.isEmpty(refundQueryMap.get("out_refund_no"))
                && StringUtils.isEmpty(refundQueryMap.get("refund_id"))) {
            log.error("微信退款查询接口请求参数验证错误[微信退款单号refund_id、out_refund_no、out_trade_no、transaction_id 四个参数必填一个]");
            return false;
        }

        return true;
    }

    /**
     * 取得微信退款申请接口的响应信息
     *
     * @param response 微信响应内容
     * @return 退款单信息
     */
    private RefundQueryInfo getRefundQueryInfo(String response) {
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, RefundQueryInfo.class});
        return (RefundQueryInfo) xstream.fromXML(response);
    }
}
