package com.guanyitong.model.vo;

import com.guanyitong.model.AccountManager;

import java.io.Serializable;

//出借人银行卡信息
public class LenderManagementVo extends AccountManager implements Serializable {

    private  String username;//出借人账号（用户手机号）

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
