package com.guanyitong.model.vo;

import com.guanyitong.model.UserDealMoney;

import java.io.Serializable;
//出借用户统计
public class UserDealMoneyVo extends UserDealMoney implements Serializable{
    private String username;
    private String realName;
    private String name;//产品的类型
    private String NO;//标的编号
    private Integer yield;//利率
    private Integer Zmoney;
    private String idCard;



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

    public Integer getZmoney() {
        return Zmoney;
    }

    public void setZmoney(Integer zmoney) {
        Zmoney = zmoney;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
