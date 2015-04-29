package com.micro.rent.biz.weixin.message.send.passive;

import com.micro.rent.biz.weixin.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

@XStreamAlias("xml")
public class PicTextMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private int articleCount;
    @XStreamAlias("Articles")
    private List<Article> articles;

    public int getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(int articleCount) {
        this.articleCount = articleCount;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

}
