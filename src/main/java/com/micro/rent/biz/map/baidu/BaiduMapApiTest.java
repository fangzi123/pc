package com.micro.rent.biz.map.baidu;

import com.micro.rent.biz.map.baidu.direction.*;
import com.thoughtworks.xstream.XStream;

import java.io.File;


public class BaiduMapApiTest {

    public static void main(String[] args) {
        //BaiduMapAPI baiduMapApi =new BaiduMapAPI();
        //http%3A%2F%2F115.29.103.144%2Frental%2Frental%2Fmap%2FdisplayMap.html
        //System.out.println(URLEncoder.encode("http://115.29.103.144/rental/rental/map/displayMap.html"));

		/*String address = "上钢六村";
		String city = "上海";
		GeocoderSearchResponse geocoderSearchResponse = baiduMapApi.geocoder(address, city);
		System.out.println(geocoderSearchResponse.toString());*/
		

		/*
		String address2="上钢六村,地铁";
		BigDecimal lat=new BigDecimal(31.179159070787);
		BigDecimal lng=new BigDecimal(121.4974579953);
		
		String placeSearchResponse=baiduMapApi.searchPlace(address2, lat, lng);
		System.out.println(placeSearchResponse);
      
		
		String origins="峨山路";
		String destinations="长清路";
		String routeResponse=baiduMapApi.route(origins, destinations);
		System.out.println(routeResponse);
		
		*/

        XStream stream = new XStream();
        stream.processAnnotations(new Class[]{DirectionBusTransResponse.class, Destination.class, DirectionResult.class,
                Origin.class, Scheme.class, StepInfo.class, Vehicle.class});
        String filePath = "D:\\workspace-sts-2.8.1.RELEASE\\rent\\src\\test\\java\\test.xml";

        String xmlString = "<DirectionBusTransResponse>" +
                "<status>0</status>" +
                "<message>message</message>" +
                "<type>0</type>" +
                "<result>" +
                "<scheme>" +
                "<distance>3333</distance>" +
                "<duration>1000</duration>" +
                "<steps/>" +
                "</scheme>" +
                "<scheme>" +
                "<distance>3333</distance>" +
                "<duration>1000</duration>" +
                "<steps/>" +
                "</scheme>" +
                "<origin>" +
                "<originPt>" +
                "<lng>12.22222</lng>" +
                "<lat>12.22222</lat>" +
                "</originPt>" +
                "</origin>" +

                "</result>" +
                "</DirectionBusTransResponse>";
        DirectionBusTransResponse response = (DirectionBusTransResponse) stream.fromXML(new File(filePath));
        //DirectionBusTransResponse response = (DirectionBusTransResponse)stream.fromXML(xmlString);
        System.out.println(response.toString());
//		DirectionBusTransResponse response = new DirectionBusTransResponse();
//		response.setStatus(0);
//		response.setMessage("message");
//		//response
//		DirectionResult result = new DirectionResult();
//		Origin origin = new Origin();
//		MapPoint pt = new MapPoint();
//		pt.setLat(new BigDecimal("12.22222"));
//		pt.setLng(new BigDecimal("12.22222"));
//		origin.setOrigin(pt);
//		
//		Destination destination = new Destination();
//		destination.setDestination(pt);
//		
//		result.setDestination(destination);
//		result.setOrigin(origin);
//		
//		Scheme scheme = new Scheme();
//		scheme.setDistance("3333");
//		scheme.setDuration("1000");
//		
//		List<Scheme> schemes = new ArrayList<Scheme>();
//		schemes.add(scheme);
//		
//		result.setSchemes(schemes);
//		
//		response.setResult(result);
//		
//		System.out.println(stream.toXML(response));

        String origins = "上钢六村";
        String destinations = "陆家嘴软件园";
        String city2 = "上海";
        //String directResponse=baiduMapApi.direct(origins,destinations,city2);
        //System.out.println(directResponse);


    }
}
