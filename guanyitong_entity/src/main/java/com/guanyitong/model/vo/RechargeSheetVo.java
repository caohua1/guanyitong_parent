package com.guanyitong.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.guanyitong.model.AccountManager;

import java.io.Serializable;
import java.util.Date;

/**
 * 充值明细实体类
 */
public class RechargeSheetVo extends AccountManager implements Serializable {


    private String rechargeMoney;//充值金额
    private String dzMoney;//实际到账金额
    private Date rechargeTime;//充值时间
    private String serial;//流水号
    private String username;//手机号
    private Long rid;//充值表主键id


    public String getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(String rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }

    public String getDzMoney() {
        return dzMoney;
    }

    public void setDzMoney(String dzMoney) {
        this.dzMoney = dzMoney;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Date rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}
