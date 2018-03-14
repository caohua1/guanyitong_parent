package com.guanyitong.model.vo;

import com.guanyitong.model.ProductInfo;

import java.io.Serializable;
import java.util.Date;
//查看投标数（所有的用户投标）
public class UserProductInfoVo extends ProductInfo implements Serializable {
    private String username;//所有出借用户
    private Integer dealMoney;//出借金额（交易）
    private String name;//投资类型（产品）
    private Double money;//盈利金额


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


    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }


}
