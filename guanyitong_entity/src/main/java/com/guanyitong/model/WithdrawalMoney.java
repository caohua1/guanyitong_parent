package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//用户提现管理实体类
public class WithdrawalMoney implements Serializable {
    private Long id;
    private Long userId;//出借人id
    private String borrowMoneyUserId;//借款人id
    private Integer userType;//提现人类型 0 出借人 1 借款人
    private String txNumber;//提现账号（银行卡）
    private String txBank;//提现银行
    private String txMoney;//提现金额
    private String dzMoney;//到账金额
    private String sxf;//手续费
    private Date txTime;//提现时间
    private Integer status;//(0失败 1已经提现，转帐中 2审核中)
    private String sqUser;//申请人（借款人）

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

    public String getBorrowMoneyUserId() {
        return borrowMoneyUserId;
    }

    public void setBorrowMoneyUserId(String borrowMoneyUserId) {
        this.borrowMoneyUserId = borrowMoneyUserId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getDzMoney() {
        return dzMoney;
    }

    public void setDzMoney(String dzMoney) {
        this.dzMoney = dzMoney;
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


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSqUser() {
        return sqUser;
    }

    public void setShUser(String sqUser) {
        this.sqUser = sqUser;
    }

}
