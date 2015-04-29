package com.micro.rent.weixin.service;

import com.micro.rent.biz.weixin.message.WeixinPayResponseBaseInfo;
import com.micro.rent.biz.weixin.message.receive.pay.OrderInfo;
import com.micro.rent.biz.weixin.message.receive.pay.RefundInfo;
import com.micro.rent.biz.weixin.message.receive.pay.UnifyInfo;
import com.micro.rent.common.support.XstreamHelper;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

@WebAppConfiguration
public class MyServiceTestcase extends AbstractSpringContextTestSupport {

    @Test
    public void findCoverProject() throws Exception {
        HttpClient httpClient = new HttpClient();
        // String url = "http://localhost:8080/rental/rent/entry.html";
        String url = "http://localhost:8080/rental/pay/unify";
        PostMethod postMethod = new PostMethod(url);
        String charSet = "UTF-8";
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);
        String a = "<xml><a>1</a><return_code>SUCCESS</return_code><return_msg></return_msg><appid>wx8276ac869116d5aa</appid><mch_id>10058488</mch_id><nonce_str></nonce_str><sign></sign><result_code>SUCCESS</result_code><err_code></err_code><err_code_des></err_code_des><openid>o00MJjxDIObA_dpqLSnnewODPVx8</openid><is_subscribe>N</is_subscribe><trade_type>JSAPI</trade_type><bank_type>bankType</bank_type><total_fee>1000</total_fee><coupon_fee></coupon_fee><fee_type></fee_type><transaction_id>12345678901234567890123456789012</transaction_id><out_trade_no>test7764262942</out_trade_no><time_end>20141231100001</time_end></xml>";
        postMethod.setRequestBody(a);
        int statusCode = httpClient.executeMethod(postMethod);
        System.out.println(postMethod.getResponseBodyAsString());
    }

    @Test
    public void xmlToObject() throws Exception {
        String a = "<xml><appid><![CDATA[wx76fdba75e3e97eb6]]></appid><bank_type><![CDATA[CMB_CREDIT]]></bank_type><cash_fee><![CDATA[1]]></cash_fee><fee_type><![CDATA[CNY]]></fee_type><is_subscribe><![CDATA[Y]]></is_subscribe><mch_id><![CDATA[10058488]]></mch_id><nonce_str><![CDATA[8c8a58fa97c205ff222de3685497742c]]></nonce_str><openid><![CDATA[oWn6vjqCG1ViU1WhmYhyVEPNgF2g]]></openid><out_trade_no><![CDATA[test7578490097]]></out_trade_no><result_code><![CDATA[SUCCESS]]></result_code><return_code><![CDATA[SUCCESS]]></return_code><sign><![CDATA[81EA34650B2459F17C93CC4D9C589AA4]]></sign><time_end><![CDATA[20150113184329]]></time_end><total_fee>1</total_fee><trade_type><![CDATA[JSAPI]]></trade_type><transaction_id><![CDATA[1000660760201501130009967699]]></transaction_id></xml>";
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, UnifyInfo.class});
        UnifyInfo unifyInfo = new UnifyInfo();
        unifyInfo = (UnifyInfo) xstream.fromXML(a);
    }

    @Test
    public void xmlToObject1() throws Exception {
        String a = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx76fdba75e3e97eb6]]></appid><mch_id><![CDATA[10058488]]></mch_id><sub_mch_id><![CDATA[]]></sub_mch_id><nonce_str><![CDATA[KplZkc5758L1NEo5]]></nonce_str><sign><![CDATA[5E877BB818468A6378FF3643DC34E872]]></sign><result_code><![CDATA[SUCCESS]]></result_code><transaction_id><![CDATA[1000660760201501130009967737]]></transaction_id><out_trade_no><![CDATA[test1915173920]]></out_trade_no><out_refund_no><![CDATA[Rtest1915173920]]></out_refund_no><refund_id><![CDATA[2000660760201501130000473201]]></refund_id><refund_channel><![CDATA[]]></refund_channel><refund_fee>1</refund_fee><coupon_refund_fee>0</coupon_refund_fee></xml>";
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, RefundInfo.class});
        RefundInfo b = (RefundInfo) xstream.fromXML(a);
    }

    @Test
    public void xmlToObject21() throws Exception {
        String a = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx76fdba75e3e97eb6]]></appid><mch_id><![CDATA[10058488]]></mch_id><nonce_str><![CDATA[TJqcLCJfNeJR432U]]></nonce_str><sign><![CDATA[2992DD7357DF51BDAF3A72050ED3F2F5]]></sign><result_code><![CDATA[SUCCESS]]></result_code><openid><![CDATA[oWn6vjqCG1ViU1WhmYhyVEPNgF2g]]></openid><is_subscribe><![CDATA[Y]]></is_subscribe><trade_type><![CDATA[JSAPI]]></trade_type><bank_type><![CDATA[CMB_CREDIT]]></bank_type><total_fee>1</total_fee><fee_type><![CDATA[CNY]]></fee_type><transaction_id><![CDATA[1000660760201501130009968717]]></transaction_id><out_trade_no><![CDATA[test6627566635]]></out_trade_no><attach><![CDATA[]]></attach><time_end><![CDATA[20150113190831]]></time_end><trade_state><![CDATA[SUCCESS]]></trade_state><cash_fee>1</cash_fee></xml>";
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{
                WeixinPayResponseBaseInfo.class, OrderInfo.class});
        xstream.fromXML(a);
    }

}
