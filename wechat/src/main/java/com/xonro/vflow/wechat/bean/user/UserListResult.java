package com.xonro.vflow.wechat.bean.user;

import com.alibaba.fastjson.annotation.JSONField;
import com.xonro.vflow.wechat.bean.user.UserData;

/**
 * 用户列表返回实体
 * @author Alex
 * @date 2018/2/1
 */
public class UserListResult {
    /**
     * 关注该公众账号的总用户数
     */
    private Integer total;
    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private Integer count;
    /**
     * 列表数据，OPENID的列表
     */
    private UserData data;
    /**
     * 拉取列表的最后一个用户的OPENID
     */
    private String nextOpenId;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    @JSONField(name = "next_openid")
    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }
}
