package com.xonro.vflow.workflow.service.impl;

import com.xonro.vflow.workflow.bean.Tenant;
import com.xonro.vflow.workflow.dao.TenantRepository;
import com.xonro.vflow.workflow.service.TenantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;

/**
 * @author louie
 * @date created in 2018-3-22 17:15
 */
@Service
public class TenantServiceImpl implements TenantService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private TenantRepository tenantRepository;

    @Override
    public Tenant createTenant(String name, String password) {
        Tenant tenant = new Tenant();
        tenant.setName(name);
        tenant.setPassword(password);
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant deleteTenant(String tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId);
        if (tenant != null){
            tenantRepository.delete(tenant);
        }
        return tenant;
    }

    @Override
    public Tenant updateTenant(Tenant tenant) {
        String tenantId = tenant.getId();
        Assert.notNull(tenantId,"tenant's id must not be null!");
        return tenantRepository.save(tenant);
    }

    @Override
    public Tenant findById(String tenantId) {
        return tenantRepository.findById(tenantId);
    }
}