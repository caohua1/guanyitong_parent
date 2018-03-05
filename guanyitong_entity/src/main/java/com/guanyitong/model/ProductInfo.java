package com.guanyitong.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//出借的产品（每个种类有不同的产品）详情
public class ProductInfo implements Serializable {
    private Long id;
    private Long productId;
    private String NO;//编号
    private String backMoneyType;//回款方式（1按月还本付息、2先息后本、3一次性还本付息、4等额本息）
    private Integer monthNum;//期限
    private Integer ZMoney;//借款总额（所有的用户出借总额不超过100万元）
    private Integer SYMoney;//剩余可出借的金额
    private Integer yield;//收益率
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNO() {
        return NO;
    }

    public void setNO(String NO) {
        this.NO = NO;
    }

    public String getBackMoneyType() {
        return backMoneyType;
    }

    public void setBackMoneyType(String backMoneyType) {
        this.backMoneyType = backMoneyType;
    }

    public Integer getZMoney() {
        return ZMoney;
    }

    public void setZMoney(Integer ZMoney) {
        this.ZMoney = ZMoney;
    }

    public Integer getSYMoney() {
        return SYMoney;
    }

    public void setSYMoney(Integer SYMoney) {
        this.SYMoney = SYMoney;
    }

    public Integer getMonthNum() {
        return monthNum;
    }

    public void setMonthNum(Integer monthNum) {
        this.monthNum = monthNum;
    }

    public Integer getYield() {
        return yield;
    }

    public void setYield(Integer yield) {
        this.yield = yield;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
