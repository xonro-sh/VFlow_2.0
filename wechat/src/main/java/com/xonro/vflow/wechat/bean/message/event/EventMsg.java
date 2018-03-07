package com.xonro.vflow.wechat.bean.message.event;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wechat.bean.message.ordinary.OrdinaryMsg;

/**
 * 公众号事件消息
 * @author louie
 * @date 2018-2-9
 */
public class EventMsg extends OrdinaryMsg {

    /**
     * 事件类型
     */
    @JSONField(name = "Event")
    private String event;

    /**
     * 事件KEY值
     */
    @JSONField(name = "EventKey")
    private String eventKey;

    /**
     * 扫码事件时二维码的ticket
     */
    @JSONField(name = "Ticket")
    private String ticket;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
}
