package com.xonro.vflow.wechat.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.wechat.bean.massmessage.MassMessageResult;
import com.xonro.vflow.wechat.bean.massmessage.MassSpeedResult;

import java.io.IOException;
import java.util.List;

/**
 * 群发消息业务相关接口
 * @author Alex
 * @date 2018/1/12
 */
public interface MassMessageService {
    /**
     * 群发消息(图文消息)
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param mediaId 用于群发的消息的media_id
     * @param msgType 群发的消息类型，图文消息为mpnew
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 群发结果
     */
    MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String mediaId, String msgType, Integer sendIgnoreReprint) throws IOException, VFlowException;

    /**
     * 群发消息(其他)
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId 群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param content 内容 也可为mediaId wxcardId
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 群发结果
     */
    MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String content, String msgType) throws IOException, VFlowException;

    /**
     * 根据openID列表群发消息（图文消息）
     * @param toUser openID列表
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @param mediaId 用于群发的消息的media_id
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 群发结果
     */
    MassMessageResult sendAllByOpenId(List<String> toUser, Integer sendIgnoreReprint, String mediaId) throws IOException, VFlowException;

    /**
     * 根据openID列表群发消息（文本消息，语音消息，图片消息，卡券消息 ）
     * @param toUser openID列表
     * @param msgType  群发的消息类型，文本消息为text，语音为voice，图片为image，卡券为wxcard
     * @param content 内容 media_id card_id
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 群发结果
     */
    MassMessageResult sendAllByOpenId(List<String> toUser, String msgType, String content) throws IOException, VFlowException;

    /**
     * 删除群发 （图文和视频消息）
     * @param msgId 发送出去的消息ID
     * @param articleIdx 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 删除结果
     */
    MassMessageResult delMassMessage(String msgId, String articleIdx) throws IOException, VFlowException;

    /**
     * 预览群发
     * @param toWxName 微信号
     * @param msgType 群发的消息类型，图文消息为mpnews，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @param content 用于群发的消息的media_id或者发送文本消息时文本的内容
     * @throws VFlowException 异常
     * @throws IOException 异常
     * @return 预览群发结果
     */
    MassMessageResult previewMassMessage(String toWxName, String msgType, String content) throws IOException, VFlowException;

    /**
     * 查询群发消息发送状态
     * @param msgId 群发消息后返回的消息id
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 消息状态
     */
    MassMessageResult getMassMessageState(String msgId) throws IOException, VFlowException;

    /**
     * 查询群发速度
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 群发速度
     */
    MassSpeedResult getMassSpeed() throws IOException, VFlowException;

    /**
     * 设置群发速度
     * @param speed 速度 0-4
     * @throws IOException 异常
     * @throws VFlowException 异常
     * @return 群发速度
     */
    MassMessageResult setMassSpeed(Integer speed) throws IOException, VFlowException;
}
