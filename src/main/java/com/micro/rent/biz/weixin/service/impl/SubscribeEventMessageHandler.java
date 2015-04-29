package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.micro.rent.biz.weixin.message.receive.event.SubscribeEventMessage;
import com.micro.rent.biz.weixin.service.MessageHandler;
import com.micro.rent.biz.weixin.support.WeixinUtil;
import com.micro.rent.common.comm.ConfigUtil;
import com.micro.rent.common.support.XstreamHelper;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 关注事件处理
 *
 * @author liqianxi
 */
@Service("subscribeHandler")
public class SubscribeEventMessageHandler implements MessageHandler {
    protected transient Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${webServerUri}")
    private String webServerUri;

    @Override
    public String handleMessage(String source, Object... strings) {
        XStream xstream = XstreamHelper.crtXstream();
        xstream.processAnnotations(new Class[]{BaseMessage.class, SubscribeEventMessage.class
        });
        SubscribeEventMessage eventMsg = (SubscribeEventMessage) xstream.fromXML(source);

        String resMsg = replyTextMsg(xstream, eventMsg);
        log.debug("subscribeHandler=" + resMsg);
        return resMsg;
    }

    private String replyTextMsg(XStream xstream, SubscribeEventMessage eventMsg) {
        String content = "";
        try {
            content = ConfigUtil.getConfig("weixin.welcome");
        } catch (Exception e) {
            content = "感谢关注窝牛租房，我们是国内首家公寓B2C平台。租房不操心，窝牛伴你行。来试试吧。咨询或反馈可直接回复哦。";
        }

        return WeixinUtil.replyTextMsg(xstream, eventMsg.getToUserName(),
                eventMsg.getFromUserName(), System.currentTimeMillis() / 1000,
                content, false);
    }
}
