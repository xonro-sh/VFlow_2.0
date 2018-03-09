package com.xonro.vflow.wechat.service.impl;

import com.thoughtworks.xstream.XStream;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.message.Message;
import com.xonro.vflow.wechat.bean.message.event.EventMsg;
import com.xonro.vflow.wechat.bean.message.event.LocationEvent;
import com.xonro.vflow.wechat.bean.message.ordinary.*;
import com.xonro.vflow.wechat.bean.user.UserInfo;
import com.xonro.vflow.wechat.dao.MessageRepository;
import com.xonro.vflow.wechat.dao.UserRepository;
import com.xonro.vflow.wechat.enums.WechatEnums;
import com.xonro.vflow.wechat.helper.MessageParser;
import com.xonro.vflow.wechat.service.MessageService;
import com.xonro.vflow.wechat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author Alex
 * @date 2018/1/9
 */
@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(UserService userService, UserRepository userRepository, MessageRepository messageRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * 解析微信平台推送的消息
     * @param xmlMessage 微信平台post的消息xml
     * @return 是否成功 返回""或者SUCCESS都为成功
     */
    @Override
    public String parseMessage(String xmlMessage) {
        try {
            OrdinaryMsg ordinaryMsg = new MessageParser(xmlMessage).parse();
            String msgType = ordinaryMsg.getMsgType();

            switch (msgType){
                //文本消息
                case "text":{
                    return accessTextMessage(ordinaryMsg);
                }

                //图片消息
                case "image":{
                    return accessImageMessage((ImageMsg) ordinaryMsg);
                }

                //语音消息
                case "voice":{
                    return accessVoiceMessage((VoiceMsg) ordinaryMsg);
                }

                //视频消息
                case "video":{
                    return accessVideoMessage((VideoMsg) ordinaryMsg);
                }

                //地理位置消息
                case "location":{
                    return accessLocationMessage((LocationMsg) ordinaryMsg);
                }

                //链接消息
                case "link":{
                    return accessLinkMessage((LinkMsg) ordinaryMsg);
                }

                //事件推送
                case "event":{
                    return accessEventMessage((EventMsg) ordinaryMsg);
                }
                default:return "";
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return "";
    }

    @Override
    public String replyMessage(OrdinaryMsg ordinaryMsg) {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream.toXML(ordinaryMsg);
    }

    /**
     * 处理文本消息，后续将扩展根据一定规则响应对应信息
     * 消息匹配规则将在后台系统实现
     * @param ordinaryMsg
     * @return
     */
    private String accessTextMessage(OrdinaryMsg ordinaryMsg){
        // TODO: 2018-2-11 接收文本消息并做处理
        return "";
    }

    /**
     * 处理图片消息
     * @param imageMsg
     * @return
     */
    private String accessImageMessage(ImageMsg imageMsg){
        // TODO: 2018-2-11 接收图片消息并做处理
        return "";
    }

    /**
     * 处理语音消息
     * @param voiceMsg
     * @return
     */
    private String accessVoiceMessage(VoiceMsg voiceMsg){
        // TODO: 2018-2-11 接收语音消息并处理

        return "";
    }

    /**
     * 处理视频消息
     * @param videoMsg
     * @return
     */
    private String accessVideoMessage(VideoMsg videoMsg){
        // TODO: 2018-2-11 接收视频消息并做响应处理

        return "";
    }

    /**
     * 处理位置消息
     * @param locationMsg
     * @return
     */
    private String accessLocationMessage(LocationMsg locationMsg){
        // TODO: 2018-2-11 接收位置消息并做处理

        return "";
    }

    /**
     * 处理链接消息
     * @param linkMsg
     * @return
     */
    private String accessLinkMessage(LinkMsg linkMsg){
        // TODO: 2018-2-11 接收链接消息并做处理
        
        return "";
    }

    /**
     * 处理事件消息
     * @param eventMessage
     * @return
     */
    private String accessEventMessage(EventMsg eventMessage) throws IOException, VFlowException {
        String eventType = eventMessage.getEvent();
        String userOpenId = eventMessage.getFromUserName();

        //用户订阅事件：1、保存用户信息；2、根据配置响应关注后信息
        if (eventType.equals(WechatEnums.EVENT_SUBSCRIBE.getValue())){
            userRepository.save(userService.getUserInfo(userOpenId));

            //获取系统配置消息
            List<Message> messages = messageRepository.findByType(WechatEnums.MSG_TYPE_SECOND.getValue());
            if (messages.size()!=0){
                //如果有效
                if ( messages.get(0).isActive()){
                    String wechatId = "gh_5438f14fcf6f";

                    OrdinaryMsg ordinaryMsg = new OrdinaryMsg();
                    ordinaryMsg.setContent(messages.get(0).getContent());
                    ordinaryMsg.setCreateTime((System.currentTimeMillis()/1000)+"");
                    ordinaryMsg.setFromUserName(wechatId);
                    ordinaryMsg.setMsgType(WechatEnums.MSG_TYPE_TEXT.getValue());
                    ordinaryMsg.setToUserName(userOpenId);

                    return replyMessage(ordinaryMsg);
                }
            }
        }

        //取消订阅，更新用户状态
        if (eventType.equals(WechatEnums.EVENT_UNSUBSCRIBE.getValue())){
            UserInfo userInfo = userRepository.findByOpenid(userOpenId);
            if (userInfo != null){
                userInfo.setSubscribe(0);
                userRepository.save(userInfo);
            }
        }

        //上报地理位置事件
        if (eventType.equals(WechatEnums.EVENT_LOCATION.getValue())){
            LocationEvent locationEvent = (LocationEvent) eventMessage;

            // TODO: 2018-2-11 处理地理位置信息
        }

        // TODO: 2018-2-11 其他类型事件信息均可从eventMessage中获取

        return "";
    }



}
