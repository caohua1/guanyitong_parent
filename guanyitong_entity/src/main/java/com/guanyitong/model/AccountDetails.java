package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.util.Date;

/**
 * AccountDetailsController参数实体类
 */
public class AccountDetails {

    /**
     * Date startTime,
     Date endTime,
     int PmenType,
     String type,
     int condition
     */
    private Date startTime;
    private Date endTime;
    private String PmenType;
    private String type;
    private int condition;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPmenType() {
        return PmenType;
    }

    public void setPmenType(String pmenType) {
        PmenType = pmenType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }
}
