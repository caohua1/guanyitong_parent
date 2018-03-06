package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//用户提现管理实体类
public class WithdrawalMoney implements Serializable {
    private Long id;
    private Long userId;
    private String txNumber;//提现账号（银行卡）
    private String txBank;//提现银行
    private String txMoney;//提现金额
    private String zzMoney;//转账金额
    private String sxf;//手续费
    private Date txTime;//提现时间
    private Date zzTime;//转账时间
    private Integer status;//(0失败 1已经提现，转帐中 2审核中)
    private String shUser;//审核人
    private Date shTime;//审核时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTxNumber() {
        return txNumber;
    }

    public void setTxNumber(String txNumber) {
        this.txNumber = txNumber;
    }

    public String getTxBank() {
        return txBank;
    }

    public void setTxBank(String txBank) {
        this.txBank = txBank;
    }

    public String getTxMoney() {
        return txMoney;
    }

    public void setTxMoney(String txMoney) {
        this.txMoney = txMoney;
    }

    public String getZzMoney() {
        return zzMoney;
    }

    public void setZzMoney(String zzMoney) {
        this.zzMoney = zzMoney;
    }

    public String getSxf() {
        return sxf;
    }

    public void setSxf(String sxf) {
        this.sxf = sxf;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getTxTime() {
        return txTime;
    }

    public void setTxTime(Date txTime) {
        this.txTime = txTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getZzTime() {
        return zzTime;
    }

    public void setZzTime(Date zzTime) {
        this.zzTime = zzTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getShUser() {
        return shUser;
    }

    public void setShUser(String shUser) {
        this.shUser = shUser;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getShTime() {
        return shTime;
    }

    public void setShTime(Date shTime) {
        this.shTime = shTime;
    }
}
