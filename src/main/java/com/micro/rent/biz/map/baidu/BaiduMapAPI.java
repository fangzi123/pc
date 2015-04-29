package com.micro.rent.biz.map.baidu;

import com.micro.rent.biz.map.baidu.direction.*;
import com.micro.rent.biz.map.baidu.geocoder.GeocoderSearchResponse;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class BaiduMapAPI {
    // 逆地址解析，根据经纬坐标,返回城市、地址名称
    public static Map<String, String> geocoderReverse(BigDecimal latitude, BigDecimal longitude) {
        String responseString = null;
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod getMethod = new GetMethod(
                    "http://api.map.baidu.com/geocoder/v2/?coordtype=bd09ll"//wgs84ll
                            + "&location=" + latitude.toString() + "," + longitude.toString()
                            + "&output=xml&ak=A122a2bb3e8ff58a9490406760e978f9&callback=renderReverse&pois=0");
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("失败返回码[" + statusCode + "]");
            }

            responseString = getMethod.getResponseBodyAsString();
            System.out.println("responseString>>>" + responseString);
        } catch (HttpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Map<String, String> hm = new HashMap<String, String>();

        String address = StringUtils.substringBetween(responseString, "<formatted_address>", "</formatted_address>");
        hm.put("address", address);
        String city = StringUtils.substringBetween(responseString, "<city>", "</city>");
        hm.put("city", city);
        return hm;
    }

    // 根据城市、地址名称，返回经纬坐标
    public static GeocoderSearchResponse geocoder(String address, String city) {
        String responseString = null;
        XStream stream = new XStream();
        stream.processAnnotations(new Class[]{GeocoderSearchResponse.class, Result.class, Location.class});
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod getMethod = new GetMethod(
                    "http://api.map.baidu.com/geocoder/v2/?address="
                            + URLEncoder.encode(address, "UTF-8")
                            + "&city="
                            + URLEncoder.encode(city, "UTF-8")
                            + "&output=xml&ak=A122a2bb3e8ff58a9490406760e978f9&callback=showLocation");
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("失败返回码[" + statusCode + "]");
            }
            responseString = getMethod.getResponseBodyAsString();
        } catch (HttpException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return (GeocoderSearchResponse) stream.fromXML(responseString);
    }

    /**
     * 两点之间路线导航
     *
     * @param origins
     * @param destinations
     * @param city
     * @return
     */
    public static DirectionBusTransResponse direct(String origins, String destinations, String city) {
        String responseString = null;
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod getMethod = new GetMethod(
                    "http://api.map.baidu.com/direction/v1?output=xml&mode=transit&origin="
                            + URLEncoder.encode(origins, "UTF-8")
                            + "&destination="
                            + URLEncoder.encode(destinations, "UTF-8")
                            + "&region=" + URLEncoder.encode(city, "UTF-8")
                            + "&ak=A122a2bb3e8ff58a9490406760e978f9");
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("失败返回码[" + statusCode + "]");
            }

            responseString = getMethod.getResponseBodyAsString();

        } catch (HttpException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        XStream stream = new XStream();
        stream.processAnnotations(new Class[]{DirectionBusTransResponse.class, Destination.class, DirectionResult.class,
                Origin.class, Scheme.class, StepInfo.class, Vehicle.class});
        DirectionBusTransResponse response = (DirectionBusTransResponse) stream.fromXML(responseString);
        return response;
    }

    /**
     * 经纬度转换
     *
     * @param log
     * @param lat
     * @return
     */
    public static String geoconv(String log, String lat) {
        String http = "http://api.map.baidu.com/geoconv/v1/?coords=%s&from=1&ak=A122a2bb3e8ff58a9490406760e978f9";
        http = String.format(http, log + "," + lat);
        String responseString = null;
        try {
            HttpClient httpclient = new HttpClient();
            GetMethod getMethod = new GetMethod(
                    http);
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                System.out.println("失败返回码[" + statusCode + "]");
            }

            responseString = getMethod.getResponseBodyAsString();

        } catch (HttpException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return responseString;

    }

    /**
     * 圆形区域查询，返回感兴趣的点
     *
     * @return
     */
/*	public String searchPlace(String address, BigDecimal lat, BigDecimal lng) {
        String responseString = null;
		XStream stream = new XStream();
		try {
			HttpClient httpclient = new HttpClient();
			GetMethod getMethod = new GetMethod(
					"http://api.map.baidu.com/place/v2/search?&query="
							+ URLEncoder.encode(address, "UTF-8")
							+ "&location="
							+ lat.floatValue()
							+ ","
							+ lng.floatValue()
							+ "&radius=2000&output=xml&ak=A122a2bb3e8ff58a9490406760e978f9");
			String charSet = "UTF-8";
			httpclient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode < HttpURLConnection.HTTP_OK
					|| statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				System.out.println("失败返回码[" + statusCode + "]");
			}

			responseString = getMethod.getResponseBodyAsString();

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return (PlaceSearchResponse)stream.fromXML(responseString);
		return responseString;
	}
*/
    /**
     * 返回两点之间的距离时间
     * @param origins
     * @param destinations
     * @return
     */
/*	public String route(String origins, String destinations) {
		String responseString = null;
		try {
			HttpClient httpclient = new HttpClient();
			GetMethod getMethod = new GetMethod(
					"http://api.map.baidu.com/direction/v1/routematrix?output=xml&origins="
							+ URLEncoder.encode(origins, "UTF-8")
							+ "&destinations="
							+ URLEncoder.encode(destinations, "UTF-8")
							+"&mode=driving"
							+ "&ak=A122a2bb3e8ff58a9490406760e978f9");
			String charSet = "UTF-8";
			httpclient.getParams().setParameter(
					HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

			int statusCode = httpclient.executeMethod(getMethod);
			if (statusCode < HttpURLConnection.HTTP_OK
					|| statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
				System.out.println("失败返回码[" + statusCode + "]");
			}

			responseString = getMethod.getResponseBodyAsString();

		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return responseString;
	}
	*/
}
