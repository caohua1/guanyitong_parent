package com.guanyitong.model.vo;

/**
 * 资金账户管理实体类
 */
public class CapitalAccountVo {

    private Long userId;//出借人id

    private String username;//手机号

    private String bankNum;//银行卡号

    private String realName;//银行开户人

    private double accountAmount;//账户总金额

    private double yuE;//可用金额

    private double amountProtection;//保护期金额

    private double withdrawalOf;//提现中金额

    private double receivable;//待回款金额

    private double accumulatedIncome;//累计收益

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(double accountAmount) {
        this.accountAmount = accountAmount;
    }

    public double getYuE() {
        return yuE;
    }

    public void setYuE(double yuE) {
        this.yuE = yuE;
    }

    public double getAmountProtection() {
        return amountProtection;
    }

    public void setAmountProtection(double amountProtection) {
        this.amountProtection = amountProtection;
    }

    public double getWithdrawalOf() {
        return withdrawalOf;
    }

    public void setWithdrawalOf(double withdrawalOf) {
        this.withdrawalOf = withdrawalOf;
    }

    public double getReceivable() {
        return receivable;
    }

    public void setReceivable(double receivable) {
        this.receivable = receivable;
    }

    public double getAccumulatedIncome() {
        return accumulatedIncome;
    }

    public void setAccumulatedIncome(double accumulatedIncome) {
        this.accumulatedIncome = accumulatedIncome;
    }
}
