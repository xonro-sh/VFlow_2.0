package com.xonro.vflow.console.service;

import com.xonro.vflow.bases.bean.BaseResponse;
import com.xonro.vflow.console.bean.TenantInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Alex
 * @date 2018/4/12 13:46
 */
public interface LoginService {
    BaseResponse getTenantInfo(String account, String password);

    BaseResponse getTenantInfoById(String tenantId);

    BaseResponse saveTenantInfo(TenantInfo tenantInfo);

    BaseResponse getTenantInfoFromCache(String account, String password);

    BaseResponse getTenantInfoByIdFromCache(String tenantId);
    /**
     * 更改用户头像
     * @param photo
     * @return
     */
    BaseResponse changeHeadshot(MultipartFile photo);
}
