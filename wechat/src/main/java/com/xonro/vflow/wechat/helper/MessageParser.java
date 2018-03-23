package com.xonro.vflow.wechat.helper;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.wechat.bean.message.event.EventMsg;
import com.xonro.vflow.wechat.bean.message.event.LocationEvent;
import com.xonro.vflow.wechat.bean.message.ordinary.*;
import com.xonro.vflow.wechat.enums.WechatEnums;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息解析器
 * @author louie
 * @date 2018-2-8
 */
public class MessageParser {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 微信推送的xml格式消息全文
     */
    private String xmlMessage;

    /**
     * 解析后的消息数据
     */
    private Map<String,String> messageData;

    /**
     * 解析消息数据
     * @return
     */
    public OrdinaryMsg parse() throws DocumentException {
        messageData = new HashMap<>(16);
        try {
            Document document = DocumentHelper.parseText(xmlMessage);
            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();

            for (Element element : elements) {
                messageData.put(element.getName(),element.getStringValue());
            }

            return parseMessage();
        } catch (DocumentException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 解析消息
     * @return
     */
    private OrdinaryMsg parseMessage(){
        String msgType = messageData.get("MsgType");

        switch (msgType){
            //文本消息
            case "text":{
                return JSON.parseObject(JSON.toJSONString(messageData),OrdinaryMsg.class);
            }

            //图片消息
            case "image":{
                return JSON.parseObject(JSON.toJSONString(messageData),ImageMsg.class);
            }

            //语音消息
            case "voice":{
                return JSON.parseObject(JSON.toJSONString(messageData),VoiceMsg.class);
            }

            //视频消息
            case "video":{
                return JSON.parseObject(JSON.toJSONString(messageData),VoiceMsg.class);
            }

            //地理位置消息
            case "location":{
                return JSON.parseObject(JSON.toJSONString(messageData),LocationMsg.class);
            }

            //链接消息
            case "link":{
                return JSON.parseObject(JSON.toJSONString(messageData),LinkMsg.class);
            }

            //事件推送
            case "event":{
                String event = messageData.get("Event");

                //上报地理位置
                if (event.equals(WechatEnums.EVENT_LOCATION.getValue())){
                    return JSON.parseObject(JSON.toJSONString(messageData), LocationEvent.class);
                }else {
                    return JSON.parseObject(JSON.toJSONString(messageData), EventMsg.class);
                }
            }
            default :return null;
        }
    }

    public MessageParser(String xmlMessage){
        this.xmlMessage = xmlMessage;
    }

}
