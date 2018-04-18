package com.guanyitong.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guanyitong.model.UserDealMoney;

import java.io.Serializable;
import java.util.Date;

//出借用户统计
public class UserDealMoneyVo extends UserDealMoney implements Serializable{
    private String username;
    private String realName;
    private String name;//产品的类型
    private String NO;//产品的名称
    private Integer yield;//利率
    private Integer ZMoney;
    private String idCard;
    private Date pCreateTime;//产品发布时间
    private Integer pStatus;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }


    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Integer getZMoney() {
        return ZMoney;
    }

    public void setZmoney(Integer zmoney) {
        ZMoney = zmoney;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getpCreateTime() {
        return pCreateTime;
    }

    public void setpCreateTime(Date pCreateTime) {
        this.pCreateTime = pCreateTime;
    }

    public Integer getpStatus() {
        return pStatus;
    }

    public void setpStatus(Integer pStatus) {
        this.pStatus = pStatus;
    }
}
