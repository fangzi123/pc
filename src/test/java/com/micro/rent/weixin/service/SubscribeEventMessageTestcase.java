package com.micro.rent.weixin.service;

import com.micro.rent.biz.weixin.service.MessageHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;
import org.springframework.test.context.web.WebAppConfiguration;
import support.AbstractSpringContextTestSupport;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebAppConfiguration
public class SubscribeEventMessageTestcase extends
        AbstractSpringContextTestSupport {

    @Resource
    private MessageHandler subscribeHandler;

    private static String buildStrSubscribeEventMsg() {
        return "<xml><ToUserName><![CDATA[mqweixin]]></ToUserName>"
                + "<FromUserName><![CDATA[jack]]></FromUserName>"
                + "<CreateTime>1405935544057</CreateTime>"
                + "<MsgType><![CDATA[event]]></MsgType>"
                + "<Event><![CDATA[subscribe]]></Event></xml>";
    }

    private static String buildStrLocationEventMsg() {
        return "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[LOCATION]]></Event><Latitude>23.137466</Latitude><Longitude>113.352425</Longitude><Precision>119.385040</Precision></xml>";
    }

    private static String buildJsonMenu() throws UnsupportedEncodingException {
        //测试url
        String testUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx520c15f417810387&redirect_uri=http%3A%2F%2Fchong.qq.com%2Fphp%2Findex.php%3Fd%3D%26c%3DwxAdapter%26m%3DmobileDeal%26showwxpaytitle%3D1%26vb2ctag%3D4_2030_5_1194_60&response_type=code&scope=snsapi_base&state=123#wechat_redirect";
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx91b46ab482dcf480%26redirect_uri=";
        //http://115.29.103.144/rental/rental/map/displayMap.html
        String redirectUrl = "http://115.29.103.144/rental/rental/map/displayMap.html";

        String enCodeUrl = URLEncoder.encode(redirectUrl);

        url = url + enCodeUrl + "%26response_type=code%26scope=snsapi_base#wechat_redirect";
        String menu = "{"
                + "\"button\": ["
                + "    {"
                + "        \"name\": \"我要租房\","
                + "        \"sub_button\": ["
                + "            {"
                + "                \"type\": \"view\","
                + "                \"name\": \"智能匹配\","
                + "                \"url\": \"http://115.29.103.144/rental/rental/match/displayMatch.html\","
                + "                \"sub_button\": [ ]"
                + "            }, "
                + "            {"
                + "                \"type\": \"view\","
                + "                \"name\": \"租房地图\","
                + "                \"url\":\"" + url + "\","
                + "                \"sub_button\": [ ]" + "            }"
                + "        ]" + "    }," + "    {"
                + "        \"name\": \"个人中心\"," + "        \"sub_button\": ["
                + "            {" + "                \"type\": \"click\", "
                + "                \"name\": \"我的收藏\", "
                + "                \"key\": \"btn_003\","
                + "                \"sub_button\": [ ]" + "            },"
                + "            {" + "                \"type\": \"click\", "
                + "                \"name\": \"我的订单\", "
                + "                \"key\": \"btn_004\","
                + "                \"sub_button\": [ ]" + "            },"
                + "            {" + "                \"type\": \"click\", "
                + "                \"name\": \"个人信息\", "
                + "                \"key\": \"btn_005\","
                + "                \"sub_button\": [ ]" + "            }"
                + "        ]" + "    }," + "    {"
                + "        \"name\": \"咨询\"," + "        \"sub_button\": ["
                + "            {" + "                \"type\": \"view\", "
                + "                \"name\": \"公司介绍\", "
                + "                \"url\": \"http://www.baidu.com/\","
                + "                \"sub_button\": [ ]" + "            },"
                + "            {" + "                \"type\": \"view\", "
                + "                \"name\": \"联系我们\", "
                + "                \"url\": \"http://www.baidu.com/\","
                + "                \"sub_button\": [ ]" + "            },"
                + "            {" + "                \"type\": \"view\", "
                + "                \"name\": \"优惠活动\", "
                + "                \"url\": \"http://www.baidu.com/\","
                + "                \"sub_button\": [ ]" + "            },"
                + "            {" + "                \"type\": \"view\", "
                + "                \"name\": \"下载客户端\", "
                + "                \"url\": \"http://www.soso.com/\","
                + "                \"sub_button\": [ ]" + "            }"
                + "        ]" + "    }" + "]" + "}";

        return menu;

    }

    public static void main(String[] args) throws HttpException, IOException {
        //System.out.println(URLEncoder.encode("&"));  %26

        HttpClient httpClient = new HttpClient();
        // String url = "http://localhost:8080/rental/rent/entry.html";
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=_2PJwNfNgd5ehCpowLFpT5gVLXTwMDiF6kSovc9nXOAXph1IeUxMJkVGniH7Z0FgE1Is3z6YLSHknqHZEAfgCA";
        PostMethod postMethod = new PostMethod(url);
        String charSet = "UTF-8";
        httpClient.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

        // 填入各个表单域的值
        // postMethod.setRequestBody(buildStrSubscribeEventMsg());
        postMethod.setRequestBody(buildJsonMenu());
        // 将表单的值放入postMethod中
        // postMethod.setRequestBody(data);
        // 执行postMethod
        int statusCode = httpClient.executeMethod(postMethod);
        // HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
        // 301或者302

        System.out.println(postMethod.getResponseBodyAsString());
    }

    @Test
    public void handleMessage() {

        subscribeHandler.handleMessage(buildStrSubscribeEventMsg());
    }
}
