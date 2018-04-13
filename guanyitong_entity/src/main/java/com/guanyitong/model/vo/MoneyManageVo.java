package com.guanyitong.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
//资金账户管理
public class MoneyManageVo implements Serializable {
    private Long userId;//用户
    private String username;//手机号
    private String bankNum;//银行卡号
    private String realName;//开户人
    private Double ZMoney;//账户总金额
    private Double yuE;//可用余额
    private Double BHMoney;//保护期金额
    private Double TQZMoney;//提取中的金额
    private Double DHKMoney;//待回款金额
    private Double LJSY;//累计收益
    private Date createTime;//开通时间id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Double getZMoney() {
        return ZMoney;
    }

    public void setZMoney(Double ZMoney) {
        this.ZMoney = ZMoney;
    }

    public Double getYuE() {
        return yuE;
    }

    public void setYuE(Double yuE) {
        this.yuE = yuE;
    }

    public Double getBHMoney() {
        return BHMoney;
    }

    public void setBHMoney(Double BHMoney) {
        this.BHMoney = BHMoney;
    }

    public Double getTQZMoney() {
        return TQZMoney;
    }

    public void setTQZMoney(Double TQZMoney) {
        this.TQZMoney = TQZMoney;
    }

    public Double getDHKMoney() {
        return DHKMoney;
    }

    public void setDHKMoney(Double DHKMoney) {
        this.DHKMoney = DHKMoney;
    }

    public Double getLJSY() {
        return LJSY;
    }

    public void setLJSY(Double LJSY) {
        this.LJSY = LJSY;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
