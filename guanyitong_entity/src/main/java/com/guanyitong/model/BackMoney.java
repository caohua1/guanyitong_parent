package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class BackMoney implements Serializable {
    private Long id;
    private Long dealMoneyId;
    private String bj;//本金
    private String lx;//利息
    private String backMoney;//本金加利息
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

    public String getBj() {
        return bj;
    }

    public void setBj(String bj) {
        this.bj = bj;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(String backMoney) {
        this.backMoney = backMoney;
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
