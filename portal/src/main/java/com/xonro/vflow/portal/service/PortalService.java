package com.xonro.vflow.portal.service;

import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.portal.bean.PortalResponse;
import com.xonro.vflow.workflow.bean.PortalMenu;

import java.util.List;

/**
 * @author Alex
 * @date 2018/4/27 16:38
 */
public interface PortalService {
    List<PortalResponse> getPortalMenuByUserId(String userId) throws VFlowException;
}
