package com.xonro.vflow.wechat.dao;

import com.xonro.vflow.wechat.bean.user.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据仓储
 * @author louie
 * @date 2018-1-12
 */
@Repository
public interface UserRepository extends JpaRepository<UserInfo,Long> {
    /**
     * 分页查找所有
     * @param pageable
     * @return
     */
    @Override
    Page<UserInfo> findAll(Pageable pageable);

    UserInfo findByOpenid(String openid);

}
