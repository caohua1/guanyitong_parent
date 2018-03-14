package com.guanyitong.model.vo;

import com.guanyitong.model.AccountManager;

import java.io.Serializable;

public class LenderManagementVo extends AccountManager implements Serializable {

    private  String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
