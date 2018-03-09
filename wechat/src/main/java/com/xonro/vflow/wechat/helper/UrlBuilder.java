package com.xonro.vflow.wechat.helper;

import com.xonro.vflow.wechat.enums.WechatEnums;
import com.xonro.vflow.wechat.service.TokenService;
import com.xonro.vflow.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 微信公众平台请求服务url构建器
 * @author Alex
 * @date 2018/1/8
 */
@Component
public class UrlBuilder {

    private final TokenService tokenService;

    private final WechatService wechatService;

    @PostConstruct
    public void init() {
        tokenService.setUrlBuilder(this);
    }

    @Autowired
    public UrlBuilder(TokenService tokenService, WechatService wechatService) {
        this.tokenService = tokenService;
        this.wechatService = wechatService;
    }


    /**
     * 构建获取accessTocken的请求url
     * @return 构建完成的请求url
     */
    public String buildGetTokenUrl(){
        return WechatEnums.URL_TOKEN.getValue()+"&appid="+ wechatService.getConfFromCache().getAppId() +"&secret="+wechatService.getConfFromCache().getAppSecret();
    }

    /**
     * 构建获取jsapi_ticket临时票据的请求url
     * @return 构建完成的请求url
     */
    public String buildJsApiTicketUrl(){
        return WechatEnums.URL_JSAPI_TICKET.getValue()+"access_token="+tokenService.getTokenFromCache()+"&type=jsapi";
    }

    /**
     * 构建添加客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomServiceAdd(){
        return WechatEnums.URL_CUSTOMSERVICE_ADD.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建修改客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomServiceUpdate(){
        return WechatEnums.URL_CUSTOMSERVICE_UPDATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建删除改客服账号的请求url
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @param nickName  客服昵称
     * @param password  客服账号登录密码，格式为密码明文的32位加密MD5值。该密码仅用于在公众平台官网的多客服功能中使用，若不使用多客服功能，则不必设置密码
     * @return 构建完成的请求url
     */
    public String buildCustomServiceDel(String kfAccount,String nickName, String password){
        return WechatEnums.URL_CUSTOMSERVICE_DEL.getValue()+"access_token="+tokenService.getTokenFromCache()+"&kf_account="+kfAccount+"&nickname="+nickName+"&password="+password;
    }

    /**
     * 构建设置客服账号头像的请求url
     * @param kfAccount 完整的客服账号 格式为：账号前缀@公众号微信号
     * @return 构建完成的请求url
     */
    public String buildCustomServiceHeadImg(String kfAccount){
        return WechatEnums.URL_CUSTOMSERVICE_UPLOADHEADIMG.getValue()+"access_token="+tokenService.getTokenFromCache()+"&kf_account="+kfAccount;
    }

    /**
     * 构建获取所有客服账号的请求url
     * @return 构建完成的请求url
     */
    public String buildGetKfList(){
        return WechatEnums.URL_CUSTOMSERVICE_GETKFLIST.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建客服发消息的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomSend(){
        return WechatEnums.URL_CUSTOMSERVICE_SEND.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 客服输入状态的请求url
     * @return 构建完成的请求url
     */
    public String buildCustomTyping(){
        return WechatEnums.URL_CUSTOMSERVICE_TYPING.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取用户信息请求url
     * @param openId 普通用户的标识，对当前公众号唯一
     * @return
     */
    public String buildUserInfoUrl(String openId){
        return WechatEnums.URL_USER_INFO.getValue()+"access_token="+tokenService.getTokenFromCache()+"&openid="+openId+"&lang=zh_CN";
    }

    /**
     * 构建获取用户信息请求url
     * @param openId 普通用户的标识，对当前公众号唯一
     * @param lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public String buildUserInfoUrl(String openId,String lang){
        return WechatEnums.URL_USER_INFO.getValue()+"access_token="+tokenService.getTokenFromCache()+"&openid="+openId+"&lang="+lang;
    }

    /**
     * 构建根据标签进行群发的请求url
     * @return 构建完成的请求url
     */
    public String buildSendAllByTag(){
        return WechatEnums.URL_MASSMESSAGE_SENDALL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建根据openid进行群发的请求url
     * @return 构建完成的请求url
     */
    public String buildSendAllByOpenId(){
        return WechatEnums.URL_MASSMESSAGE_SENDBYOPENID.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建删除消息的请求url
     * @return 构建完成的请求url
     */
    public String buildDelMassMessage(){
        return WechatEnums.URL_MASSMESSAGE_DELETE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建预览群发消息的请求url
     * @return 构建完成的请求url
     */
    public String buildPreviewUrl(){
        return WechatEnums.URL_MASSMESSAGE_PREVIEW.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建查询群发消息发送状态的请求url
     * @return 构建完成的请求url
     */
    public String buildGetStateUrl(){
        return WechatEnums.URL_MASSMESSAGE_GET.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取群发速度的请求url
     * @return 构建完成的请求url
     */
    public String buildGetMassSpeedUrl(){
        return WechatEnums.URL_MASSMESSAGE_GETMASSSPEED.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建设置群发速度的请求url
     * @return 构建完成的请求url
     */
    public String buildSetMassSpeedUrl(){
        return WechatEnums.URL_MASSMESSAGE_SETMASSSPEED.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建菜单创建的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuCreateUrl(){
        return WechatEnums.URL_MENU_CREATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建查询菜单的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuGetUrl(){
        return WechatEnums.URL_MENU_GET.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建删除菜单的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuDelUrl(){
        return WechatEnums.URL_MENU_DEL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建新增个性化菜单的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuConditionalUrl(){
        return WechatEnums.URL_MENU_ADDCONDITIONAL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建删除个性化菜单的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuConditionalDelUrl(){
        return WechatEnums.URL_MENU_DELCONDITIONAL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建测试个性化菜单的请求url
     * @return 构建完成的请求url
     */
    public String buildMenuTryMatch(){
        return WechatEnums.URL_MENU_TRYMATCH.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取自定义菜单配置的请求url
     * @return 构建完成的请求url
     */
    public String buildGetCurrentSelfMenuUrl(){
        return WechatEnums.URL_MENU_CURRENTSELFMENU.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建新增临时素材的请求url
     * @param type 	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return 构建完成的请求url
     */
    public String buildUploadTemMediaUrl(String type){
        return WechatEnums.URL_TEMPORARY_UPLOADMEDIA.getValue()+"access_token="+tokenService.getTokenFromCache()+"&type="+type;
    }

    /**
     * 构建获取临时素材的请求url
     * @param mediaId 构建完成的请求url
     * @return 构建完成的请求url
     */
    public String buildGetTemMediaUrl(String mediaId){
        return WechatEnums.URL_TEMPORARY_GETMEDIA.getValue()+"access_token="+tokenService.getTokenFromCache()+"&media_id="+mediaId;
    }

    /**
     * 构建新增永久图文素材的请求url
     * @return 构建完成的请求url
     */
    public String buildMaterialAddNewsUrl(){
        return WechatEnums.URL_MATERIAL_ADDNEWS.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 上传图文消息内的图片获取URL
     * @return 构建完成的请求url
     */
    public String buildUpLoadImg(){
        return WechatEnums.URL_MATERIAL_UPLOADIMG.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建新增其他类型永久素材url
     * @param type 构建完成的请求url
     * @return 构建完成的请求url
     */
    public String buildMaterialAddUrl(String type){
        return WechatEnums.URL_MATERIAL_ADD.getValue()+"access_token="+tokenService.getTokenFromCache()+"&type="+type;
    }

    /**
     * 构建删除永久素材url
     * @return 构建完成的请求url
     */
    public String buildMaterialDelUrl(){
        return WechatEnums.URL_MATERIAL_DEL.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建修改永久图文素材url
     * @return 构建完成的请求url
     */
    public String buildMaterialUpdateNewsUrl(){
        return WechatEnums.URL_MATERIAL_UPDATENEWS.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取素材总数url
     * @return 构建完成的请求url
     */
    public String buildMaterialCountUrl(){
        return WechatEnums.URL_MATERIAL_COUNT.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取素材列表url
     * @return 构建完成的请求url
     */
    public String buildGetMaterialBatch(){
        return WechatEnums.URL_MATERIAL_BATCHGET.getValue()+"access_token="+tokenService.getTokenFromCache();
    }
    /**
     * 构建创建含有用户信息二维码的请求url
     * @return 构建完成的url
     */
    public String buildCreateQrCodeUrl(){
        return WechatEnums.URL_QRCODE_CREATE.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建获取二维码图片的请求url
     * @param ticket 临时票据
     * @return 构建完成的url
     */
    public String buildQrCodeImageUrl(String ticket){
        return WechatEnums.URL_QRCODE_IMAGE.getValue()+"ticket="+ticket;
    }

    /**
     * 构建获取用户列表的请求url
     * @param openId 起始的openId
     * @return 构建完成的url
     */
    public String buildGetUserListUrl(String openId){
        return WechatEnums.URL_USER_LIST.getValue()+"access_token="+tokenService.getTokenFromCache()+"&next_openid="+openId;
    }

    /**
     * 构建获取用户列表信息的请求url
     * @return 构建完成的url
     */
    public String buildGetUserInfoBatchUrl(){
        return WechatEnums.URL_USER_BATCHINFO.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

    /**
     * 构建更新用户备注的请求url
     * @return 构建完成的url
     */
    public String buildUpdateRemarkUrl(){
        return WechatEnums.URL_USER_UPDATEREMARK.getValue()+"access_token="+tokenService.getTokenFromCache();
    }

}
