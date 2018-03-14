package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.crypto.Data;
import java.io.Serializable;

/**
 * 用户银行卡管理
 */
public class UserBankcard implements Serializable {

    private Long id;//用户id
    private String realName;//用户真实姓名
    private String IDCardNumber;//用户身份证号码
    private String bankName;//用户开户银行
    private String cardNo;//用户银行卡号
    private Data submitTime;//用户提交时间
    private String openAccountRegion;//用户开户行地区
    private String userName;//用户名（唯一标识）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Data getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Data submitTime) {
        this.submitTime = submitTime;
    }



    public String getOpenAccountRegion() {
        return openAccountRegion;
    }

    public void setOpenAccountRegion(String openAccountRegion) {
        this.openAccountRegion = openAccountRegion;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserBankcard(Long id, String realName, String IDCardNumber, String bankName, String cardNo, Data submitTime, String openAccountRegion, String userName) {
        this.id = id;
        this.realName = realName;
        this.IDCardNumber = IDCardNumber;
        this.bankName = bankName;
        this.cardNo = cardNo;
        this.submitTime = submitTime;
        this.openAccountRegion = openAccountRegion;
        this.userName = userName;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}