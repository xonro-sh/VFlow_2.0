package com.xonro.vflow.wechat.bean.material;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 图文素材请求数据模型
 * @author Alex
 * @date 2018/1/17
 */
public class NewsResponse {
    private List<Article> articles;

    public NewsResponse(List<Article> articles) {
        this.articles = articles;
    }

    @JSONField(name = "articles")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
