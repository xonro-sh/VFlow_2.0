package com.xonro.vflow.console.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.BaseRequest;
import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.console.bean.TenantInfo;
import com.xonro.vflow.console.helper.ConsoleUrlBuilder;
import com.xonro.vflow.console.service.LoginService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Alex
 * @date 2018/4/12 13:48
 */
@Service
public class LoginServiceImpl implements LoginService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ConsoleUrlBuilder consoleUrlBuilder;
    private TenantInfo tenantInfo;
    @Autowired
    public LoginServiceImpl(ConsoleUrlBuilder consoleUrlBuilder) {
        this.consoleUrlBuilder = consoleUrlBuilder;
    }

    @Override
    public BaseResponse getTenantInfo(String account, String password) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        BaseRequest baseRequest;
        try {
            baseRequest = new RequestExecutor(consoleUrlBuilder.buildGetTenantInfoUrl(account, DigestUtils.md5Hex(password)))
                    .execute()
                    .getResponseAsObject(BaseRequest.class);
            if (baseRequest.isOk()){
                String tenantJson = JSON.toJSONString(baseRequest.getData());
                TenantInfo tenantInfo = JSON.parseObject(tenantJson, TenantInfo.class);
                if (tenantInfo.isActive()){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date nowdate = df.parse(df.format(System.currentTimeMillis()));
                    Date activeDate = df.parse(tenantInfo.getActiveTime());
                    if (nowdate.before(activeDate)){
                        baseResponse.setOk(true);
                        baseResponse.setData(baseRequest.getData());
                        cacheTenantInfo(tenantInfo);
                    } else {
                        baseResponse.setOk(false);
                        baseResponse.setMsg("您的账号激活时间已过期，请联系相关人员");
                        baseResponse.setCode("active time past");
                    }
                } else {
                    baseResponse.setOk(false);
                    baseResponse.setMsg("您的账号未激活，请联系相关人员");
                    baseResponse.setCode("account is not active");
                }

            } else {
                logger.error(baseRequest.getCode(),baseRequest.getMsg());
                baseResponse.setOk(false);
                baseResponse.setMsg(baseRequest.getMsg());
                baseResponse.setCode(baseRequest.getCode());
            }
        } catch (IOException | VFlowException | ParseException e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getTenantInfoById(String tenantId) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(true);
            setData("");
            setMsg("");
        }};
        BaseRequest baseRequest;
        try {
            baseRequest = new RequestExecutor(consoleUrlBuilder.buildGetTenantInfoByIdUrl(tenantId))
                    .execute()
                    .getResponseAsObject(BaseRequest.class);
            if (baseRequest.isOk()){
                String tenantJson = JSON.toJSONString(baseRequest.getData());
                TenantInfo tenantInfo = JSON.parseObject(tenantJson, TenantInfo.class);
                if (tenantInfo.isActive()){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date nowdate = df.parse(df.format(System.currentTimeMillis()));
                    Date activeDate = df.parse(tenantInfo.getActiveTime());
                    if (nowdate.before(activeDate)){
                        baseResponse.setOk(true);
                        baseResponse.setData(baseRequest.getData());
                        cacheTenantInfo(tenantInfo);
                    } else {
                        baseResponse.setOk(false);
                        baseResponse.setMsg("您的账号激活时间已过期，请联系相关人员");
                        baseResponse.setCode("active time past");
                    }
                } else {
                    baseResponse.setOk(false);
                    baseResponse.setMsg("您的账号未激活，请联系相关人员");
                    baseResponse.setCode("account is not active");
                }

            } else {
                logger.error(baseRequest.getCode(),baseRequest.getMsg());
                baseResponse.setOk(false);
                baseResponse.setMsg(baseRequest.getMsg());
                baseResponse.setCode(baseRequest.getCode());
            }
        } catch (IOException | VFlowException | ParseException e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Override
    public BaseResponse getTenantInfoFromCache(String account, String password) {

        if (tenantInfo == null){
            return getTenantInfo(account, password);
        } else {
            BaseResponse baseResponse = new BaseResponse(){{
                setOk(false);
                setData("");
                setMsg("");
            }};
            if (account.equals(tenantInfo.getAccount()) && DigestUtils.md5Hex(password).equals(tenantInfo.getPassword())){
                baseResponse.setOk(true);
                baseResponse.setData(tenantInfo);
            } else {
                baseResponse.setMsg("用户名或者密码不正确");
                baseResponse.setCode("login failed");
            }
            return baseResponse;
        }
    }

    @Override
    public BaseResponse getTenantInfoByIdFromCache(String tenantId) {
        if (tenantInfo == null){
            return getTenantInfoById(tenantId);
        } else {
            BaseResponse baseResponse = new BaseResponse(){{
                setOk(true);
                setData("");
                setMsg("");
                setData(tenantInfo);
            }};
            return baseResponse;
        }
    }

    @Override
    public BaseResponse changeHeadshot(MultipartFile photo) {
        BaseResponse baseResponse = new BaseResponse(){{
            setOk(false);
            setData("");
            setMsg("");
        }};
        try {
            String fileName = photo.getOriginalFilename();
            fileName = "adminphotoshot"+"."+fileName.split("\\.")[1];
            byte[] bytes = photo.getBytes();
            String filePath = "D:/IdeaProjects/VFlow_2.0_N/client/src/main/resources/static//img"+ fileName;
            FileUtils.writeByteArrayToFile(new File(URLDecoder.decode(filePath,"utf-8")),bytes);
            tenantInfo.setHeadshotUrl(filePath);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            baseResponse.setOk(false);
            baseResponse.setMsg(e.getMessage());
        }
        return baseResponse;
    }

    @Cacheable(value = "TenantInfo",key = "#tenantInfo.getId()")
    public void cacheTenantInfo(TenantInfo tenantInfo){
        this.tenantInfo = tenantInfo;
    }

    @Override
    public BaseResponse saveTenantInfo(TenantInfo tenantInfo) {
        return null;
    }
}
