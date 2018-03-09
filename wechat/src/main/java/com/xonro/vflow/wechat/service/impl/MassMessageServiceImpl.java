package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.massmessage.MassMessageResult;
import com.xonro.vflow.wechat.bean.massmessage.MassSpeedResult;
import com.xonro.vflow.wechat.enums.WechatEnums;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.MassMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Alex
 * @date 2018/1/12
 */
@Service
public class MassMessageServiceImpl implements MassMessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String OPEN_ID = "openid";
    private final String TAG_ID = "tagid";
    private final UrlBuilder urlBuilder;

    @Autowired
    public MassMessageServiceImpl(UrlBuilder urlBuilder) {
        this.urlBuilder = urlBuilder;
    }

    @Override
    public MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String mediaId, String msgType, Integer sendIgnoreReprint) throws IOException, VFlowException {
        //提交的参数
        Map<String, Object> filterParams = new HashMap<>(2);
        filterParams.put("is_to_all", false);
        filterParams.put("tag_id", tagId);
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        mpnewsParams.put("media_id", mediaId);
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("filter", filterParams);
        dataParams.put("mpnews", mpnewsParams);
        dataParams.put("msgtype", msgType);
        dataParams.put("send_ignore_reprint", sendIgnoreReprint);
        return getMassMessageResult(dataParams, TAG_ID);
    }

    /**
     * 群发消息(其他)
     *
     * @param isToAll 用于设定是否向全部用户发送，值为true或false，选择true该消息群发给所有用户，选择false可根据tag_id发送给指定群组的用户
     * @param tagId   群发到的标签的tag_id，参加用户管理中用户分组接口，若is_to_all值为true，可不填写tag_id
     * @param content 内容 也可为mediaId wxcardId
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，音乐为music，图片为image，视频为video，卡券为wxcard
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByTagId(boolean isToAll, String tagId, String content, String msgType) throws IOException, VFlowException {
        //提交的参数
        Map<String, Object> filterParams = new HashMap<>(2);
        filterParams.put("is_to_all", false);
        filterParams.put("tag_id", tagId);
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())) {
            mpnewsParams.put("card_id", content);
        } else {
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("filter", filterParams);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        return getMassMessageResult(dataParams, TAG_ID);
    }

    /**
     * 根据openID列表群发消息（图文消息）
     *
     * @param toUser            openID列表
     * @param sendIgnoreReprint 图文消息被判定为转载时，是否继续群发。 1为继续群发（转载），0为停止群发。 该参数默认为0。
     * @param mediaId           用于群发的消息的media_id
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByOpenId(List<String> toUser, Integer sendIgnoreReprint, String mediaId) throws IOException, VFlowException {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        mpnewsParams.put("media_id", mediaId);
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("touser", toUser);
        dataParams.put("mpnews", mpnewsParams);
        dataParams.put("msgtype", "mpnews");
        dataParams.put("send_ignore_reprint", sendIgnoreReprint);
        return getMassMessageResult(dataParams, OPEN_ID);
    }

    /**
     * 根据openID列表群发消息（文本消息，语音消息，图片消息，卡券消息 ）
     *
     * @param toUser  openID列表
     * @param msgType 群发的消息类型，文本消息为text，语音为voice，图片为image，卡券为wxcard
     * @param content 内容 media_id card_id
     * @return 群发结果
     */
    @Override
    public MassMessageResult sendAllByOpenId(List<String> toUser, String msgType, String content) throws IOException, VFlowException {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())){
            mpnewsParams.put("card_id", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_VIDEO.getValue()) || msgType.equals(WechatEnums.MSG_TYPE_IMAGE.getValue())){
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("touser", toUser);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        return getMassMessageResult(dataParams, OPEN_ID);
    }

    private MassMessageResult getMassMessageResult(TreeMap<String, Object> dataParams, String type) throws IOException, VFlowException {
        MassMessageResult massMessageResult;
        String url;
        if (OPEN_ID.equals(type)){
            url = urlBuilder.buildSendAllByOpenId();
        } else {
            url = urlBuilder.buildSendAllByTag();
        }
        try {
            massMessageResult = new RequestExecutor(url)
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    /**
     * 删除群发 （图文和视频消息）
     *
     * @param msgId      发送出去的消息ID
     * @param articleIdx 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @return 删除结果
     */
    @Override
    public MassMessageResult delMassMessage(String msgId, String articleIdx) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("msg_id", msgId);
        dataParams.put("article_idx", articleIdx);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildDelMassMessage())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MassMessageResult previewMassMessage(String toWxName, String msgType, String content) throws IOException, VFlowException {
        //提交的参数
        Map<String, Object> mpnewsParams = new HashMap<>(1);
        if (msgType.equals(WechatEnums.MSG_TYPE_TEXT.getValue())){
            mpnewsParams.put("content", content);
        } else if (msgType.equals(WechatEnums.MSG_TYPE_WXCARD.getValue())){
            mpnewsParams.put("card_id", content);
        } else {
            mpnewsParams.put("media_id", content);
        }
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("towxname", toWxName);
        dataParams.put(msgType, mpnewsParams);
        dataParams.put("msgtype", msgType);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildPreviewUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MassMessageResult getMassMessageState(String msgId) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("msg_id", msgId);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildGetStateUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MassSpeedResult getMassSpeed() throws IOException, VFlowException {
        try {
            return new RequestExecutor(urlBuilder.buildGetMassSpeedUrl()).execute().getResponseAsObject(MassSpeedResult.class);
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public MassMessageResult setMassSpeed(Integer speed) throws IOException, VFlowException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("speed", speed);
        MassMessageResult massMessageResult;
        try {
            massMessageResult = new RequestExecutor(urlBuilder.buildSetMassSpeedUrl())
                    .execute(JSON.toJSONString(dataParams))
                    .getResponseAsObject(MassMessageResult.class);
            return massMessageResult;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

}
