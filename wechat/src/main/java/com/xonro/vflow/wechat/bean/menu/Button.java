package com.xonro.vflow.wechat.bean.menu;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author Alex
 * @date 2018/1/16
 */
public class Button {
    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<Object> button;
    /**
     * 菜单匹配规则 (创建个性化菜单)
     */
    private MatchRule matchRule;

    public Button(List<Object> button) {
        this.button = button;
    }

    public Button(List<Object> button, MatchRule matchRule) {
        this.button = button;
        this.matchRule = matchRule;
    }

    public List<Object> getButton() {
        return button;
    }

    public void setButton(List<Object> button) {
        this.button = button;
    }

    @JSONField(name = "matchrule")
    public MatchRule getMatchRule() {
        return matchRule;
    }

    public void setMatchRule(MatchRule matchRule) {
        this.matchRule = matchRule;
    }
}
