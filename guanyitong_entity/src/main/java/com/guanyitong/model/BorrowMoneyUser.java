package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
//借款主体信息实体类
public class BorrowMoneyUser implements Serializable {

    /* `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
            `companyName` varchar(100) DEFAULT NULL COMMENT '公司名称',
            `charterNum` varchar(50) DEFAULT NULL COMMENT '营业执照号',
            `charterImage` varchar(100) DEFAULT NULL COMMENT '营业执照图片路径',
            `legalPersonName` varchar(50) DEFAULT NULL COMMENT '法人代表',
            `legalPhone` varchar(20) DEFAULT NULL COMMENT '法人手机号',
            `companyCreateTime` datetime DEFAULT NULL COMMENT '公司成立时间',
            `registerMoney` varchar(50) DEFAULT NULL COMMENT '注册资金',
            `registerAddress` varchar(50) DEFAULT NULL COMMENT '注册地址',
            `borrowMoney` varchar(50) DEFAULT NULL COMMENT '借款金额',
            `apprroveName` varchar(50) DEFAULT NULL COMMENT '身份认证（真实姓名）',
            `legalIDCard` varchar(50) DEFAULT NULL COMMENT '身份认证（法人身份认证）',
            `legalIDCardImageZ` varchar(100) DEFAULT NULL COMMENT '身份认证（法人身份证正面图片）',
            `legalIDCardImageF` varchar(100) DEFAULT NULL COMMENT '身份认证（法人身份证反面图片）',
            `address` varchar(100) DEFAULT NULL COMMENT '居住地认证',
            `companyDescribe` varchar(2000) DEFAULT NULL COMMENT '企业介绍',
            `borrowUse` varchar(2000) DEFAULT NULL COMMENT '借款用途',
            `status` int(10) DEFAULT NULL COMMENT '状态（0审核失败 1审核成功）',*/
    private Long id;
    private String companyName;
    private String charterNum;
    private String charterImage;
    private String legalPersonName;
    private String legalPhone;
    private Date companyCreateTime;
    private String registerMoney;
    private String registerAddress;
    private String borrowMoney;
    private String apprroveName;
    private String legalIDCard;
    private String legalIDCardImageZ;
    private String legalIDCardImageF;
    private String XYJF;//信用积分
    private String XYJFDescribe;
    private String address;
    private String companyDescribe;
    private String borrowUse;
    private Date createTime;//申请时间
    private String auditUserName;//审核人姓名
    private String causeBy;//审核未通过原因
    private String contractImage;//合同照片
    private String moneyInfo;//资产信息
    private String ensureInfo;//保证信息
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCharterNum() {
        return charterNum;
    }

    public void setCharterNum(String charterNum) {
        this.charterNum = charterNum;
    }

    public String getCharterImage() {
        return charterImage;
    }

    public void setCharterImage(String charterImage) {
        this.charterImage = charterImage;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getRegisterMoney() {
        return registerMoney;
    }

    public void setRegisterMoney(String registerMoney) {
        this.registerMoney = registerMoney;
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(String borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public String getApprroveName() {
        return apprroveName;
    }

    public void setApprroveName(String apprroveName) {
        this.apprroveName = apprroveName;
    }

    public String getLegalIDCard() {
        return legalIDCard;
    }

    public void setLegalIDCard(String legalIDCard) {
        this.legalIDCard = legalIDCard;
    }

    public String getLegalIDCardImageZ() {
        return legalIDCardImageZ;
    }

    public void setLegalIDCardImageZ(String legalIDCardImageZ) {
        this.legalIDCardImageZ = legalIDCardImageZ;
    }

    public String getLegalIDCardImageF() {
        return legalIDCardImageF;
    }

    public void setLegalIDCardImageF(String legalIDCardImageF) {
        this.legalIDCardImageF = legalIDCardImageF;
    }

    public String getXYJF() {
        return XYJF;
    }

    public void setXYJF(String XYJF) {
        this.XYJF = XYJF;
    }

    public String getXYJFDescribe() {
        return XYJFDescribe;
    }

    public void setXYJFDescribe(String XYJFDescribe) {
        this.XYJFDescribe = XYJFDescribe;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyDescribe() {
        return companyDescribe;
    }

    public void setCompanyDescribe(String companyDescribe) {
        this.companyDescribe = companyDescribe;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuditUserName() {
        return auditUserName;
    }

    public void setAuditUserName(String auditUserName) {
        this.auditUserName = auditUserName;
    }

    public String getCauseBy() {
        return causeBy;
    }

    public void setCauseBy(String causeBy) {
        this.causeBy = causeBy;
    }

    public String getContractImage() {
        return contractImage;
    }

    public void setContractImage(String contractImage) {
        this.contractImage = contractImage;
    }

    public String getMoneyInfo() {
        return moneyInfo;
    }

    public void setMoneyInfo(String moneyInfo) {
        this.moneyInfo = moneyInfo;
    }

    public String getEnsureInfo() {
        return ensureInfo;
    }

    public void setEnsureInfo(String ensureInfo) {
        this.ensureInfo = ensureInfo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
