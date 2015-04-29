package com.micro.rent.biz.weixin.service.impl;

import com.micro.rent.biz.weixin.MsgTransformer;
import com.micro.rent.biz.weixin.message.BaseMessage;
import com.micro.rent.biz.weixin.message.EMsgType;
import com.micro.rent.biz.weixin.message.receive.normal.BaseOrdinaryMessage;
import com.micro.rent.biz.weixin.message.receive.normal.TextMessage;
import com.micro.rent.biz.weixin.message.send.passive.Article;
import com.micro.rent.biz.weixin.service.MessageHandler;
import com.micro.rent.biz.weixin.support.WeixinUtil;
import com.micro.rent.common.comm.ConfigUtil;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文本消息处理
 *
 * @author liqianxi
 * @date 2014-12-14
 */
@Service("textHandler")
public class TextMessageHandler implements MessageHandler {

    private transient static final Logger log = LoggerFactory
            .getLogger(TextMessageHandler.class);

    /**
     * 响应文本消息
     *
     * @param source  请求内容
     * @param strings 服务器根URL
     */
    @Override
    public String handleMessage(String source, Object... serverRootUrl) {
        log.debug("source:" + source);
        XStream xstream = MsgTransformer.xstream;
        xstream.processAnnotations(new Class[]{BaseMessage.class,
                BaseOrdinaryMessage.class, TextMessage.class});
        TextMessage eventMsg = (TextMessage) xstream.fromXML(source);
        String replyContent = "";
        String content = eventMsg.getContent();
        Map<String, String> activityMap = getActivityMap();
        // 回复活动信息
        if (activityMap.containsKey(content)) {
            replyContent = replyActivityInfo(xstream, eventMsg, content,
                    activityMap.get(content), (String) serverRootUrl[0]);
        }

        log.debug("replyContent:" + replyContent);
        return replyContent;
    }

    /**
     * 回复活动信息
     *
     * @param xstream       xstream
     * @param receivedMsg   接收到的消息
     * @param activityId    活动ID
     * @param replyMsgType  回复消息类型
     * @param serverRootUrl 服务根URL
     */
    private String replyActivityInfo(XStream xstream, TextMessage receivedMsg,
                                     String activityId, String replyMsgType, String serverRootUrl) {
        // 回复活动信息
        if (EMsgType.NEWS.getCode().equals(replyMsgType)) {
            List<Article> articleList = getActivityArticleList(activityId,
                    serverRootUrl);
            return WeixinUtil.replyPicTextMsg(xstream,
                    receivedMsg.getToUserName(), receivedMsg.getFromUserName(),
                    System.currentTimeMillis() / 1000, articleList.size(),
                    articleList, false);

        } else if (EMsgType.TEXT.equals(replyMsgType)) {
            return WeixinUtil.replyTextMsg(xstream,
                    receivedMsg.getToUserName(), receivedMsg.getFromUserName(),
                    System.currentTimeMillis() / 1000,
                    getActivityTextContent(activityId), false);
        }

        return "";
    }

    /**
     * 取得配置文件中的活动列表信息
     *
     * @return 活动列表信息(key：活动ID，value：回复消息类型)
     */
    private Map<String, String> getActivityMap() {
        Map<String, String> activityMap = new HashMap<String, String>();
        try {
            String activity = ConfigUtil.getConfig("activity.list");
            if (activity == null || "".equals(activity)) {
                return activityMap;
            }

            String[] activityArray = activity.split(",");
            for (String activityItem : activityArray) {
                String[] items = activityItem.split(":");
                activityMap.put(items[0], items[1]);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return activityMap;
    }

    /**
     * 取得活动图文信息
     *
     * @param activityId    活动ID
     * @param serverRootUrl 服务器根URL
     * @return 活动图文信息
     */
    private List<Article> getActivityArticleList(String activityId,
                                                 String serverRootUrl) {
        String configKeyPrefix = "activity." + activityId;
        List<Article> articleList = new ArrayList<Article>();
        try {
            // 标题
            String titles = ConfigUtil.getConfig(configKeyPrefix + ".titles");
            // 描述
            String descriptions = ConfigUtil.getConfig(configKeyPrefix
                    + ".descriptions");
            // 图片链接
            String picurls = ConfigUtil.getConfig(configKeyPrefix + ".picurls");
            // 跳转链接
            String urls = ConfigUtil.getConfig(configKeyPrefix + ".urls");

            String[] titleArray = titles.split("\\+\\+");
            String[] descriptionArray = descriptions.split("\\+\\+");
            String[] picurlArray = picurls.split("\\+\\+");
            String[] urlArray = urls.split("\\+\\+");

            for (int i = 0; i < titleArray.length; i++) {
                Article article = new Article();
                // 标题
                article.setTitle(titleArray[i]);
                // 描述
                article.setDescription(descriptionArray[i]);
                // 图片链接
                article.setPicUrl(serverRootUrl + picurlArray[i]);
                // 点击跳转链接
                // article.setUrl(serverRootUrl + urlArray[i]);
                article.setUrl(getShiliheRedirectUrl());
                articleList.add(article);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return articleList;
    }

    /**
     * 取得活动文本信息
     *
     * @param activityId 活动ID
     * @return 活动图文信息
     */
    private String getActivityTextContent(String activityId) {
        try {
            // 内容
            return ConfigUtil.getConfig("activity." + activityId + ".content");

        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return "";
    }

    /**
     * 编辑十里河活动微信重定向URL
     *
     * @param request
     * @return 编辑后的URL
     */
    private String getShiliheRedirectUrl() {
        String url = "";
        try {
            String redirectURI = ConfigUtil.getConfig("balancer.server.url")
                    + "/rental/rental/activity/shilihe_trade?activityId=1";
            url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                    + ConfigUtil.getConfig("appid")
                    + "&redirect_uri="
                    + redirectURI
                    + "&response_type=code&scope=snsapi_userinfo&state=shilihe#wechat_redirect";
        } catch (Exception e) {
            log.error("取配置信息失败![appid或者balancer.server.url]");
        }
        return url;
    }
}
