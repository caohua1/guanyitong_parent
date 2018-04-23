package com.guanyitong.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//借款人的还款计划
public class BackMoney implements Serializable {
    private Long id;
    private String borrowMoneyUserId;
    private Long productInfoId;
    private String bj;//本金
    private String lx;//利息
    private String backMoney;
    private Date backTime;//应该还款时间
    private Date realBackTime;//真实还款时间
    private Integer status;
    private Integer count;//还款次数

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrowMoneyUserId() {
        return borrowMoneyUserId;
    }

    public void setBorrowMoneyUserId(String borrowMoneyUserId) {
        this.borrowMoneyUserId = borrowMoneyUserId;
    }

    public Long getProductInfoId() {
        return productInfoId;
    }

    public void setProductInfoId(Long productInfoId) {
        this.productInfoId = productInfoId;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getRealBackTime() {
        return realBackTime;
    }

    public void setRealBackTime(Date realBackTime) {
        this.realBackTime = realBackTime;
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
}
