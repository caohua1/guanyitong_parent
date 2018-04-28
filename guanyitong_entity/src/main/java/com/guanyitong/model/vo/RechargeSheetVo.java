package com.guanyitong.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.guanyitong.model.RechargeMoney;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值明细实体类
 */
public class RechargeSheetVo extends RechargeMoney implements Serializable {

    private String bankAddress;//开户地址
    private String bankName;//开户银行名称
    private String bankNum;//银行卡号
    private Date createTime;//绑定时间
    private String idCard;//身份证号码
    private String realName;//用户真实姓名
    private String username;//用户名

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}