package com.guanyitong.model.vo;

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
}
