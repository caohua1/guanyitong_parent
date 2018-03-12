package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

//认证项设置
public class Apprrove implements Serializable{

    private Long id;
    private String apprroveName;//认证名称
    private String apprroveType;//认证选项
    private String options;//选项
    private Date createTime;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApprroveName() {
        return apprroveName;
    }

    public void setApprroveName(String apprroveName) {
        this.apprroveName = apprroveName;
    }

    public String getApprroveType() {
        return apprroveType;
    }

    public void setApprroveType(String apprroveType) {
        this.apprroveType = apprroveType;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
