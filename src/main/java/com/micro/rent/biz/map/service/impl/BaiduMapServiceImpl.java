package com.micro.rent.biz.map.service.impl;

import com.micro.rent.biz.enum_.ETranfficType;
import com.micro.rent.biz.map.baidu.BaiduMapAPI;
import com.micro.rent.biz.map.baidu.EResultCodeStatus;
import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.map.baidu.geocoder.GeocoderSearchResponse;
import com.micro.rent.biz.map.baidu.geocoder.Location;
import com.micro.rent.biz.map.service.DirectionReqParam;
import com.micro.rent.biz.map.service.MapService;
import com.micro.rent.common.exceptions.BizException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("baiduMapService")
public class BaiduMapServiceImpl implements MapService {

    private static int MSG_OK = 0;
    private transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${baiduAk}")
    private String baiduAk;

    public MapPoint getPoint(String address, String city) {
        GeocoderSearchResponse response = BaiduMapAPI.geocoder(address, city);
        if (EResultCodeStatus.SUCCESS.getCode() == response.getStatus()) {
            Location location = response.getResult().getLocation();

            return new MapPoint(location.getLng(), location.getLat());
        } else {
            log.error("result status:{},message:{}", new Object[]{response.getStatus()});
            throw new BizException("系统服务异常");
        }

    }

    public String crtStaticMapUrl(List<MapPoint> lstMark, MapPoint currPoint) {
        StringBuilder markersBuilder = new StringBuilder();
        if (lstMark == null || lstMark.isEmpty()) {
            lstMark = new ArrayList<MapPoint>();
            lstMark.add(currPoint);
        }
        for (MapPoint result : lstMark) {
            markersBuilder.append(result.getLng())
                    .append(",")
                    .append(result.getLat())
                    .append("|");
        }

        String markers = markersBuilder.toString();

        StringBuilder staticUrlBuilder = new StringBuilder("http://api.map.baidu.com/staticimage?");

        //添加center
        staticUrlBuilder.append("center=").append(currPoint.getLng()).append(",").append(currPoint.getLat());

        staticUrlBuilder.append("&zoom=").append(10);

        staticUrlBuilder.append("&markers=").append(markers.substring(0, markers.length() - 1));

        return staticUrlBuilder.toString();
    }

    public String doLeastTimeBetweenTwoPoint(DirectionReqParam reqParam) {

        String responseString = null;
        try {
            HttpClient httpclient = new HttpClient();

            StringBuilder baseUrlBuildString = new StringBuilder("http://api.map.baidu.com/direction/v1?");
            baseUrlBuildString.append("output=xml")
                    .append("&mode=")
                    .append(ETranfficType.getNameByCode(reqParam.getMode()))
                    .append("&origin=")
                    .append(URLEncoder.encode(reqParam.getOrigin(), "UTF-8"))
                    .append("&destination=")
                    .append(URLEncoder.encode(reqParam.getDestination(), "UTF-8"))
                    .append("&ak=")
                    .append(baiduAk)
                    .append("&tactics=11");

            if (ETranfficType.DRIVING.getCode().equals(reqParam.getMode())) {
                baseUrlBuildString.append("&origin_region=").append(URLEncoder.encode(reqParam.getOrigin_region(), "UTF-8"));
                baseUrlBuildString.append("&destination_region=").append(URLEncoder.encode(reqParam.getDestination_region(), "UTF-8"));
            } else {
                baseUrlBuildString.append("&region=").append(URLEncoder.encode(reqParam.getRegion(), "UTF-8"));
            }

            GetMethod getMethod = new GetMethod(baseUrlBuildString.toString());
            String charSet = "UTF-8";
            httpclient.getParams().setParameter(
                    HttpMethodParams.HTTP_CONTENT_CHARSET, charSet);

            int statusCode = httpclient.executeMethod(getMethod);
            if (statusCode < HttpURLConnection.HTTP_OK
                    || statusCode >= HttpURLConnection.HTTP_MULT_CHOICE) {
                log.info("失败返回码[" + statusCode + "]");
                return "0";
            } else {
                responseString = getMethod.getResponseBodyAsString();
            }


        } catch (HttpException e) {

            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
//		XStream xstream = null;
//		if (ETranfficType.DRIVING.getCode().equals(reqParam.getMode())) {
//			
//		} else if (ETranfficType.TRANSIT.getCode().equals(reqParam.getMode())) {
//			xstream = (XStream)ECache.CACHE_XSTREAM.getCacheEntity("baidu.direction.transit");
//			if (xstream == null) {
//				xstream = new XStream();
//				xstream.processAnnotations(new Class[]{DirectionBusTransResponse.class,
//						Destination.class,DirectionResult.class,
//						Origin.class,Scheme.class,StepInfo.class,Vehicle.class});
//				ECache.CACHE_XSTREAM.putCacheEntity("baidu.direction.transit", xstream);
//			}
//			
//			DirectionBusTransResponse response = (DirectionBusTransResponse)xstream.fromXML(responseString);
//			if (MSG_OK != response.getStatus()) {
//				log.error("baidu map redirect error:" + response.getMessage());
//			} else {
//				List<Scheme> lstScheme = response.getResult().getSchemes();
//				return lstScheme == null || lstScheme.isEmpty() ? "0" : lstScheme.get(0).getDuration();
//			}
//		} else {
//			xstream = (XStream)ECache.CACHE_XSTREAM.getCacheEntity("baidu.direction.walking");
//			if (xstream == null) {
//				xstream = new XStream();
//				xstream.processAnnotations(new Class[]{DirectionWalkingResponse.class,
//						Result.class,Route.class,Origin.class,Destination.class});
//				ECache.CACHE_XSTREAM.putCacheEntity("baidu.direction.walking", xstream);
//			}
//			
//			DirectionWalkingResponse response = (DirectionWalkingResponse)xstream.fromXML(responseString);
//			if (MSG_OK != response.getStatus()) {
//				log.error("baidu map redirect error:" + response.getMessage());
//			} else {
//				return response.getResult().getRoutes().getDuration();
//			}
//		}
        String status = StringUtils.substringBetween(responseString, "<status>", "</status>");
        log.debug(status);
        if (!StringUtils.isNumeric(status)) {
            return "0";
        }
        if (MSG_OK != Integer.valueOf(status).intValue()) {
            log.error("baidu map redirect error:" + StringUtils.substringBetween(responseString, "<message>", "</message>"));
        } else {
            return StringUtils.substringBetween(responseString, "<duration>", "</duration>");
        }
        return "0";
    }

    @Override
    public Map<String, String> getPlace(MapPoint mapPoint) {

        return BaiduMapAPI.geocoderReverse(mapPoint.getLat(), mapPoint.getLng());
    }
}
