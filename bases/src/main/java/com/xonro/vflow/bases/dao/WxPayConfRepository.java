package com.xonro.vflow.bases.dao;

import com.xonro.vflow.bases.bean.WxPayConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author louie
 * @Description: 微信支付基础配置仓库
 * @date 2018-3-5 15:31
 */

@Repository
public interface WxPayConfRepository extends JpaRepository<WxPayConf,Long>{

    /**
     * 获取微信支付基础配置
     * @return
     */
    public WxPayConf findFirstByIdIsNotNull();

}
