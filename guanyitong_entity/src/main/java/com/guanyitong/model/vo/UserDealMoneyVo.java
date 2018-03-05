package com.guanyitong.model.vo;

import java.io.Serializable;
//查询所有的用户出借情况
public class UserDealMoneyVo implements Serializable{
    private Long id;
    private String username;
    private String realName;
    private String name;//产品的类型
    private String NO;//标的编号
    private String monthNum;//期限
    private Integer yield;//利率
    private Integer dealMoney;//出借金额
    private Integer status;//状态（0未审核 1后台审核成功 2已还款）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(String monthNum) {
        this.monthNum = monthNum;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Integer getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Integer dealMoney) {
        this.dealMoney = dealMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
