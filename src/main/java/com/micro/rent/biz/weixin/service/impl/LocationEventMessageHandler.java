package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.map.baidu.BaiduMapAPI;
import com.micro.rent.biz.map.baidu.MapPoint;
import com.micro.rent.biz.weixin.message.BaseMessage;
import com.micro.rent.biz.weixin.message.receive.event.LocationEventMessage;
import com.micro.rent.biz.weixin.service.MessageHandler;
import com.micro.rent.common.support.JsonUtils;
import com.micro.rent.common.support.XstreamHelper;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.collections.map.LRUMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("locationHandler")
public class LocationEventMessageHandler implements MessageHandler {
    public static LRUMap lrumap = new LRUMap();
    private transient Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String handleMessage(String source, Object... strings) {
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{BaseMessage.class,
                LocationEventMessage.class});
        LocationEventMessage eventMsg = (LocationEventMessage) xstream
                .fromXML(source);
        log.debug("locationHandler=" + eventMsg.toString());

        lrumap.put(eventMsg.getFromUserName(), formatPoint(eventMsg.getLongitude(), eventMsg.getLatitude()));
        return "";
    }

    private MapPoint formatPoint(String lng, String lat) {
        String result = BaiduMapAPI.geoconv(lng, lat);
        Map<Object, Object> map = JsonUtils.jsonString2Map(result);
        Integer s = 0;
        if (map.containsKey("status") && s.equals(map.get("status"))) {
            List<Map> list = (List) map.get("result");
            Map rs = list.get(0);
            lng = "" + rs.get("x");
            lat = "" + rs.get("y");
        }
        MapPoint point = new MapPoint(lng, lat);
        return point;
    }


}
