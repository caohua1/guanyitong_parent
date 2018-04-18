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
    private String startTime;
    private String endTime;
    private Integer PmenType;
    private Integer type;
    private int condition;

    public Integer getPmenType() {
        return PmenType;
    }

    public void setPmenType(Integer pmenType) {
        PmenType = pmenType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
