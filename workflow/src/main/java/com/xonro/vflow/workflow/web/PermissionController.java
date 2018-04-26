package com.xonro.vflow.workflow.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.workflow.bean.Permission;
import com.xonro.vflow.workflow.bean.PermissionView;
import com.xonro.vflow.workflow.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Alex
 * @date 2018/4/25 13:40
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;



    /**
     * 新建权限组
     * @param permission 角色名称
     * @return 角色信息
     */
    @RequestMapping(value = "/create")
    public List<Permission> create(@Valid @RequestBody Permission permission) throws VFlowException {
        return permissionService.create(permission);
    }

    /**
     * 根据资源ID获取所有权限组
     * @param resourceId 资源ID
     * @return 结果
     */
    @RequestMapping(value = "/get")
    public List<PermissionView> getByResourceId(@NotBlank(message = "resourceId can not be empty") String resourceId){
        return permissionService.getByResourceId(resourceId);
    }

    /**
     * 根据资源ID获取所有权限组（layui）
     * @param resourceId 资源ID
     * @return 结果
     */
    @RequestMapping(value = "/get_table")
    public String getPermissionByTable(@NotBlank(message = "resourceId can not be empty") String resourceId){
        return JSON.toJSONString(permissionService.getPermissionByTable(resourceId));
    }

    /**
     * 删除权限
     * @param permissionId 权限组ID
     * @return 被删除的权限信息
     */
    @RequestMapping(value = "/delete")
    public Permission delete(@NotBlank(message = "resourceId can not be empty")String permissionId){
        return permissionService.delete(permissionId);
    }
}
