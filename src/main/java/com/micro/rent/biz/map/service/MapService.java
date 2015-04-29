package com.micro.rent.biz.map.service;

import com.micro.rent.biz.map.baidu.MapPoint;

import java.util.List;
import java.util.Map;

public interface MapService {

    MapPoint getPoint(String address, String city);

    Map<String, String> getPlace(MapPoint mapPoint);

    String crtStaticMapUrl(List<MapPoint> lstMark, MapPoint currPoint);

    String doLeastTimeBetweenTwoPoint(DirectionReqParam reqParam);


}
