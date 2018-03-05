package com.guanyitong.model.vo;

import java.io.Serializable;
import java.util.Date;
//查看投标数（所有的用户投标）
public class UserProductInfo implements Serializable {
    private String username;
    private Integer dealMoney;
    private String name;//投资类型（产品）
    private String backMoneyType;//回款方式（盈利方式）
    private Double money;//盈利金额
    private Date createTime;
    private Integer monthNum;//还款期限

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(Integer dealMoney) {
        this.dealMoney = dealMoney;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackMoneyType() {
        return backMoneyType;
    }

    public void setBackMoneyType(String backMoneyType) {
        this.backMoneyType = backMoneyType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(Integer monthNum) {
        this.monthNum = monthNum;
    }
}
