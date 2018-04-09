package com.xonro.vflow.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.xonro.vflow.bases.bean.TableResponse;
import com.xonro.vflow.bases.exception.VFlowException;
import com.xonro.vflow.bases.helper.RequestExecutor;
import com.xonro.vflow.wechat.bean.user.UserInfo;
import com.xonro.vflow.wechat.bean.user.UserListResult;
import com.xonro.vflow.wechat.dao.UserRepository;
import com.xonro.vflow.wechat.helper.UrlBuilder;
import com.xonro.vflow.wechat.service.WechatUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * @author louie
 * @date 2018-1-12
 * 用户管理
 */
@Service
public class WechatUserServiceImpl implements WechatUserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UrlBuilder urlBuilder;

    private final UserRepository userRepository;

    @Autowired
    public WechatUserServiceImpl(UrlBuilder urlBuilder, UserRepository userRepository) {
        this.urlBuilder = urlBuilder;
        this.userRepository = userRepository;
    }

    @Override
    public UserInfo getUserInfo(String openId) throws VFlowException, IOException {
        String url = urlBuilder.buildUserInfoUrl(openId);
        try {
            return new RequestExecutor(url).execute().getResponseAsObject(UserInfo.class);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userRepository.save(userInfo);
    }

    @Override
    public void deleteUser(UserInfo userInfo) {
        userRepository.delete(userInfo);
    }

    @Override
    public TableResponse getUserInfoList(String openId, Integer page, Integer rows) {
        Sort sort = new Sort(Sort.Direction.DESC,"subscribeTime");
        Pageable pages = PageRequest.of(page-1, rows, sort);
        Page<UserInfo> userInfos = userRepository.findAll(pages);

        if (userInfos.getTotalElements() == 0 ){
            List<UserInfo> userInfosAll = null;
            try {
                userInfosAll = getUserInfoFromWechat(openId);
            } catch (IOException | VFlowException e) {
                return new TableResponse(1, e.getMessage(), userInfos.getTotalElements(),userInfos.getContent());
            }
            assert userInfosAll != null;
            userRepository.saveAll(userInfosAll);
            //再执行一遍查询
            userInfos = userRepository.findAll(pages);
        }
        return new TableResponse(0, "", userInfos.getTotalElements(),userInfos.getContent());

    }

    /**
     * 从微信获取用户信息（批量分批获取）
     * @return
     * @param openId
     */
    private List<UserInfo> getUserInfoFromWechat(String openId) throws IOException, VFlowException {
        UserListResult userListResults;
        String userInfos;
        List<UserInfo> userInfosAll = new ArrayList<>();
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        try {
            userListResults = new RequestExecutor(urlBuilder.buildGetUserListUrl(openId)).execute().getResponseAsObject(UserListResult.class);
            List<Map<String, String>> openList = new ArrayList<>();
            if (userListResults.getData().getOpenId().size()!=0){
                for (String openid: userListResults.getData().getOpenId()){
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("openid", openid);
                    openList.add(map);
                    if (openList.size()==100){
                        dataParams.put("user_list", openList);
                        //每次只能获取100条
                        userInfos = new RequestExecutor(urlBuilder.buildGetUserInfoBatchUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
                        //清空
                        openList.clear();
                        userInfosAll.addAll(JSON.parseArray(JSON.parseObject(userInfos).getString("user_info_list"), UserInfo.class));
                    }
                }
            }
            //最后一次执行
            dataParams.put("user_list", openList);
            userInfos = new RequestExecutor(urlBuilder.buildGetUserInfoBatchUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
            userInfosAll.addAll(JSON.parseArray(JSON.parseObject(userInfos).getString("user_info_list"), UserInfo.class));
            Collections.sort(userInfosAll, (o1, o2) -> {
                //降序
                return o2.getSubscribeTime().compareTo(o1.getSubscribeTime());
            });
            return userInfosAll;
        } catch (IOException | VFlowException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }


    @Override
    public void updateRemark(String openId, String remark) throws VFlowException, IOException {
        //提交的参数
        TreeMap<String,Object> dataParams = new TreeMap<>();
        dataParams.put("openid", openId);
        dataParams.put("remark", remark);
        try {
            UserInfo s = getUserInfo(openId);
            if (StringUtils.isNotEmpty(s.getRemark()) && s.getRemark().equals(remark)){
                throw new VFlowException("1", "请勿重复提交");
            } else {
                new RequestExecutor(urlBuilder.buildUpdateRemarkUrl()).execute(JSON.toJSONString(dataParams)).getResponseAsObject(String.class);
                s.setRemark(remark);
            }
            userRepository.save(s);
        } catch (VFlowException | IOException e) {
            logger.error(e.getMessage(),e);
            throw e;
        }
    }

}
