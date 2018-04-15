package com.xonro.vflow.console.service.impl;


import com.xonro.vflow.console.bean.Tenant;
import com.xonro.vflow.console.dao.TenantRepository;
import com.xonro.vflow.console.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author louie
 * @date created in 2018-3-22 17:15
 */
@Service
public class TenantServiceImpl implements TenantService{
    @Autowired
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
