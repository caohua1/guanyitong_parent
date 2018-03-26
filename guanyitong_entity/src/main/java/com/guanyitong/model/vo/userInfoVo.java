package com.guanyitong.model.vo;

import com.guanyitong.model.AccountManager;

import java.io.Serializable;

/**
 * 资金账户管理（手机号，银行卡，开户人，可用金额。）实体类
 */
public class userInfoVo extends AccountManager implements Serializable {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
