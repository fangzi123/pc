package com.micro.rent.common.comm;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.micro.rent.common.support.JsonUtils;

public class IPUtil {

    static String IP_URL = "http://ip.taobao.com/service/getIpInfo2.php?ip=myip";
    static String REGION = "region_id";

    public static String getRegion() {
        String region = null;
        String responseString = "";
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod getMethod = new GetMethod(IP_URL);
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("失败返回码[" + statusCode + "]");
            }

            responseString = getMethod.getResponseBodyAsString();
            Map data = JsonUtils.jsonString2Map(responseString);
            data = (Map) data.get("data");
            region = data.get(REGION).toString();
            System.out.println("responseString>>>" + responseString);
        } catch (Exception e) {
            region="110000";
        } 
        return region;
    }

    public static void main(String[] args) {
        String region = getRegion();
        System.out.println(region);
    }

}
