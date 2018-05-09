package com.xonro.vflow.portal.web;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.portal.bean.PortalResponse;
import com.xonro.vflow.portal.service.PortalService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/27 16:37
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/portal")
public class PortalController {
    private final PortalService portalService;

    @Autowired
    public PortalController(PortalService portalService) {
        this.portalService = portalService;
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public String getPortalMenuByUserId(@NotBlank(message = "userId can not be empty") String userId) throws VFlowException {
        return JSON.toJSONString(portalService.getPortalMenuByUserId(userId));
    }
}
