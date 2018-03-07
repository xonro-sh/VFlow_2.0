package com.xonro.vflow.wechat.bean.message.ordinary;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * 图文消息
 * @author louie
 * @date 2018-2-11
 */
@XStreamAlias("xml")
public class NewsMsg extends OrdinaryMsg{

    /**
     * 图文消息个数，限制为8条以内
     */
    @XStreamAlias("ArticleCount")
    private Integer articleCount;

    /**
     * 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过8，则将会无响应
     */
    @XStreamAlias("Articles")
    private List<NewsItem> articles;

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public List<NewsItem> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsItem> articles) {
        this.articles = articles;
    }
}
