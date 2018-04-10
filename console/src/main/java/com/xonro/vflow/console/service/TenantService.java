package com.xonro.vflow.console.service;


import com.xonro.vflow.console.bean.Tenant;

/**
 * 租户相关服务接口
 * @author louie
 * @date created in 2018-3-22 15:58
 */
public interface TenantService {

    /**
     * 创建租户
     * @param name
     * @param password
     * @return 创建后的租户信息
     */
    Tenant createTenant(String name, String password);

    /**
     * 删除租户
     * 租户存在则删除，若不存在则不做任何操作
     * @param tenantId 租户id
     * @return 被删除的租户
     */
    Tenant deleteTenant(String tenantId);

    /**
     * 更新租户信息
     * @param tenant 需更新的租户信息
     * @return
     */
    Tenant updateTenant(Tenant tenant);

    /**
     * 根据id获取租户信息
     * @param tenantId
     * @return
     */
    Tenant findById(String tenantId);
}
