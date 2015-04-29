/**
 *
 */
package com.micro.rent.biz.weixin.support;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.micro.rent.biz.weixin.message.EMsgType;
import com.micro.rent.biz.weixin.message.receive.normal.TextMessage;
import com.micro.rent.biz.weixin.message.send.passive.Article;
import com.micro.rent.biz.weixin.message.send.passive.PicTextMessage;
import com.thoughtworks.xstream.XStream;

import java.util.List;

/**
 * 微信通用工具类
 *
 * @author liqianxi
 * @date 2014-12-14
 */
public class WeixinUtil {

    /**
     * 回复文本消息
     *
     * @param xStream
     * @param from       发送源
     * @param to         发送目标
     * @param createTime 消息创建时间
     * @param content    回复内容
     * @param isEncrypt  是否加密回复
     * @return 文本消息
     */
    public static String replyTextMsg(XStream xStream,
                                      String from, String to, long createTime,
                                      String content, boolean isEncrypt) {
        TextMessage textMsg = new TextMessage();
        textMsg.setFromUserName(from);
        textMsg.setToUserName(to);
        textMsg.setCreateTime(createTime);
        textMsg.setMsgType(EMsgType.TEXT.getCode());
        textMsg.setContent(content);

        xStream.processAnnotations(new Class[]{BaseMessage.class, TextMessage.class});
        return xStream.toXML(textMsg).replace("<MsgId>|</MsgId>", "");
    }

    /**
     * 回复图文消息
     *
     * @param xstream
     * @param from         发送源
     * @param to           发送目标
     * @param createTime   消息创建时间
     * @param articleCount 图文消息个数
     * @param articleList  多条图文消息信息
     * @param isEncrypt    是否加密回复
     * @return 图文消息
     */
    public static String replyPicTextMsg(XStream xstream,
                                         String from, String to, long createTime,
                                         int articleCount, List<Article> articleList, boolean isEncrypt) {
        PicTextMessage picTextMsg = new PicTextMessage();
        picTextMsg.setFromUserName(from);
        picTextMsg.setToUserName(to);
        picTextMsg.setCreateTime(createTime);
        picTextMsg.setMsgType(EMsgType.NEWS.getCode());
        picTextMsg.setArticleCount(articleCount);
        picTextMsg.setArticles(articleList);

        xstream.processAnnotations(new Class[]{
                BaseMessage.class, PicTextMessage.class, Article.class});
        return xstream.toXML(picTextMsg);
    }

}
