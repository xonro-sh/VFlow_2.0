package com.xonro.vflow.wechat.dao;

import com.xonro.vflow.wechat.bean.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alex
 * @date 2018/2/6
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * 根据类型获取消息
     * @param type 类型
     * @return
     */
    List<Message> findByType(String type);
}
