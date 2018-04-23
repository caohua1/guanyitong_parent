package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
//出借的产品（每个种类有不同的产品）详情
public class ProductInfo implements Serializable {
    /*borrowMoneyUserId:00000006
    NO:程度v
    yield:87.87
    productId:3
    ZMoney:1000000
    backMoneyType:按月还本付息
    YesNo:否
    raiseMoneyMonth:6
    monthNum:6
    moneyUse:反弹效果不错
    QSUse:许多v
    coverImage:/summary/png/2018-04-23/7fa394ff-e38f-409b-9dc5-06a3ff892638.png
    startRaiseTime:2018-04-05
    endRaiseTime:2018-07-05
    startBorrowTime:2018-04-05
    endBorrowTime:2018-07-05
    Name
    upload.do
            7fa394ff-e38f-409b-9dc5-06a3ff892638.png
    insertProductinfo.do
    toproductInfo_add.do?file=908319749846166606.jpg.png
    jquery-1.8.3.min.js
    mydate.js
    productInfo_add.js*/

    private Long id;
    private Long productId;
    private String borrowMoneyUserId;//借款主体id
    private String NO;//编号，名称
    private String backMoneyType;//回款方式（1按月还本付息、2先息后本、3一次性还本付息、4等额本息）
    private Integer monthNum;//借款期限
    private Integer raiseMoneyMonth;//筹钱期限
    private Integer ZMoney;//借款总额（所有的用户出借总额不超过100万元）
    private Integer SYMoney;//剩余可出借的金额
    private Double yield;//收益率
    private Date createTime;
    private Date startRaiseTime;//开始筹钱的时间
    private Date endRaiseTime;//结束筹钱的时间
    private Date startBorrowTime;//开始借款时间
    private Date endBorrowTime;//结束借款时间
    private String coverImage;//封面路径
    private Date updateTime;
    private Integer status;
    private String YesNo;//筹标期间是否还息
    private String moneyUse;//资金用途
    private String QSUse;//起诉用途
    private String fangqiCauseBy;//放弃原因

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

    public String getBorrowMoneyUserId() {
        return borrowMoneyUserId;
    }

    public void setBorrowMoneyUserId(String borrowMoneyUserId) {
        this.borrowMoneyUserId = borrowMoneyUserId;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Integer getRaiseMoneyMonth() {
        return raiseMoneyMonth;
    }

    public void setRaiseMoneyMonth(Integer raiseMoneyMonth) {
        this.raiseMoneyMonth = raiseMoneyMonth;
    }

    public Double getYield() {
        return yield;
    }

    public void setYield(Double yield) {
        this.yield = yield;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getStartRaiseTime() {
        return startRaiseTime;
    }

    public void setStartRaiseTime(Date startTime) {
        this.startRaiseTime = startTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndRaiseTime() {
        return endRaiseTime;
    }

    public void setEndRaiseTime(Date endTime) {
        this.endRaiseTime = endTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getStartBorrowTime() {
        return startBorrowTime;
    }

    public void setStartBorrowTime(Date startBorrowTime) {
        this.startBorrowTime = startBorrowTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndBorrowTime() {
        return endBorrowTime;
    }

    public void setEndBorrowTime(Date endBorrowTime) {
        this.endBorrowTime = endBorrowTime;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getYesNo() {
        return YesNo;
    }

    public void setYesNo(String yesNo) {
        YesNo = yesNo;
    }

    public String getMoneyUse() {
        return moneyUse;
    }

    public void setMoneyUse(String moneyUse) {
        this.moneyUse = moneyUse;
    }

    public String getQSUse() {
        return QSUse;
    }

    public void setQSUse(String QSUse) {
        this.QSUse = QSUse;
    }

    public String getFangqiCauseBy() {
        return fangqiCauseBy;
    }

    public void setFangqiCauseBy(String fangqiCauseBy) {
        this.fangqiCauseBy = fangqiCauseBy;
    }
}
