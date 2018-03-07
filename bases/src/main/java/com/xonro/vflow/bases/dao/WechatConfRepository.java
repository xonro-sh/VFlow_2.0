package com.xonro.vflow.bases.dao;

import com.xonro.vflow.bases.bean.WechatConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louie
 * @Description: 微信公众号配置仓库
 * @date 2018-3-2 16:21
 */
@Repository
public interface WechatConfRepository extends JpaRepository<WechatConf,Long>{
    /**
     * 获取企业号配置
     * @return
     */
    public WechatConf findDistinctFirstByIdIsNotNull();

}
