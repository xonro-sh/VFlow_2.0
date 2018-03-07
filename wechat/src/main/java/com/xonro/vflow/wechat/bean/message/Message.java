package com.xonro.vflow.wechat.bean.message;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 消息管理
 * @author Alex
 * @date 2018/2/6
 */
@Entity
@Table(name="b_xr_wechat_message")
public class Message {
    private String id;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否开启
     */
    private boolean isActive;

    /**
     * 消息类型（大类） 收到消息回复 1  被关注回复 2
     */
    private String type;

    /**
     * 消息类型（小类）
     */
    private String messageType;
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @Column(name = "is_active")
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "message_type")
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}
