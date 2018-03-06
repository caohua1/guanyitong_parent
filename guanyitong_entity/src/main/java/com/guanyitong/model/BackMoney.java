package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BackMoney implements Serializable {
    private Long id;
    private Long dealMoneyId;
    private Date backTime;
    private Integer status;//（0回款，1回款）
    private Integer count;//回款次数（第几个月）
    private UserDealMoney userDealMoney;//回款的哪一个出借
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDealMoneyId() {
        return dealMoneyId;
    }

    public void setDealMoneyId(Long dealMoneyId) {
        this.dealMoneyId = dealMoneyId;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public UserDealMoney getUserDealMoney() {
        return userDealMoney;
    }

    public void setUserDealMoney(UserDealMoney userDealMoney) {
        this.userDealMoney = userDealMoney;
    }
}
