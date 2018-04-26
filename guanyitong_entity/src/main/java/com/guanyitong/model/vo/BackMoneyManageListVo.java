package com.guanyitong.model.vo;

import com.guanyitong.model.BackMoney;

//财务故哪里模块（还款管理列表）
public class BackMoneyManageListVo extends BackMoney {

    private String realName;//还款人的真实姓名
    private String IDCardNumber;//身份证号
    private String bankName;//开户行
    private String cardNo;//银行卡号
    private Integer productinfoStatus;//标的状态

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIDCardNumber() {
        return IDCardNumber;
    }

    public void setIDCardNumber(String IDCardNumber) {
        this.IDCardNumber = IDCardNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getProductinfoStatus() {
        return productinfoStatus;
    }

    public void setProductinfoStatus(Integer productinfoStatus) {
        this.productinfoStatus = productinfoStatus;
    }
}
