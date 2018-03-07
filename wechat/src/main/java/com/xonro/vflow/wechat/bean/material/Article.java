package com.xonro.vflow.wechat.bean.material;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author Alex
 * @date 2018/1/17
 */
public class Article {
    /**
     * 标题
     */
    private String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    private String thumbMediaId;
    /**
     * 作者
     */
    private String author;
    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
     */
    private String digest;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    private String showCoverPic;
    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     */
    private String content;
    /**
     * 	图文消息的原文地址，即点击“阅读原文”后的URL
     */
    private String contentSourceUrl;

    @JSONField(name = "title")
    public String getTitle() {
        return title;
    }

    @JSONField(name = "thumb_media_id")
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    @JSONField(name = "author")
    public String getAuthor() {
        return author;
    }

    @JSONField(name = "digest")
    public String getDigest() {
        return digest;
    }

    @JSONField(name = "show_cover_pic")
    public String getShowCoverPic() {
        return showCoverPic;
    }

    @JSONField(name = "content")
    public String getContent() {
        return content;
    }

    @JSONField(name = "content_source_url")
    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public static class Builder {
        /**
         * 标题
         */
        private String title;
        /**
         * 标题
         */
        private String thumbMediaId;
        /**
         * 标题
         */
        private String author;
        /**
         * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
         */
        private String digest;
        /**
         * 是否显示封面，0为false，即不显示，1为true，即显示
         */
        private String showCoverPic;
        /**
         * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
         */
        private String content;
        /**
         * 	图文消息的原文地址，即点击“阅读原文”后的URL
         */
        private String contentSourceUrl;

        public Builder(String title, String thumbMediaId, String showCoverPic, String content, String contentSourceUrl){
            this.title = title;
            this.thumbMediaId = thumbMediaId;
            this.showCoverPic = showCoverPic;
            this.content = content;
            this.contentSourceUrl = contentSourceUrl;
        }

        public Builder(String title, String thumbMediaId, String showCoverPic, String content, String contentSourceUrl,String author,String digest){
            this.title = title;
            this.thumbMediaId = thumbMediaId;
            this.showCoverPic = showCoverPic;
            this.content = content;
            this.contentSourceUrl = contentSourceUrl;
            this.author = author;
            this.digest = digest;
        }

        public Builder author(String val){
            author = val;
            return this;
        }

        public Builder digest(String val){
            digest = val;
            return this;
        }

        public Article build() {
            return new Article(this);
        }
    }

    private Article(Builder builder) {
        title = builder.title;
        thumbMediaId = builder.thumbMediaId;
        author = builder.author;
        digest = builder.digest;
        showCoverPic = builder.showCoverPic;
        content = builder.content;
        contentSourceUrl = builder.contentSourceUrl;
    }
}
