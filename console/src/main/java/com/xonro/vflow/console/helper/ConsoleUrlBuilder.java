package com.xonro.vflow.console.helper;

import com.xonro.vflow.console.enums.ConsoleEnums;
import org.springframework.stereotype.Component;

/**
 * @author Alex
 * @date 2018/4/12 13:48
 */
@Component
public class ConsoleUrlBuilder {
    /**
     * 构建获取租户信息的请求url
     * @return 构建完成的请求url
     */
    public String buildGetTenantInfoUrl(String account, String password){
        return ConsoleEnums.URL_TENANT_GET.getValue()+"account="+ account +"&password="+password;
    }

    /**
     * 构建获取租户信息的请求url(租户id)
     * @param tenantId 构建完成的请求url
     * @return
     */
    public String buildGetTenantInfoByIdUrl(String tenantId){
        return ConsoleEnums.URL_TENANT_GETBYID.getValue()+"tenantId="+ tenantId ;
    }

    /**
     * 构建保存租户信息的请求url
     * @return 构建完成的请求url
     */
    public String buildSaveTenantInfoUrl(){
        return ConsoleEnums.URL_TENANT_SAVE.getValue();
    }
}
