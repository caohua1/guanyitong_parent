package com.guanyitong.model;

import java.io.Serializable;
import java.util.Date;

public class UserTreasure implements Serializable {
    private Long id;
    private Long userId;
    private Long treasureId;
    private Integer num;
    private Date startTime;
    private Date endTime;
    private Treasure treasure;

    public Treasure getTreasure() {
        return treasure;
    }

    public void setTreasure(Treasure treasure) {
        this.treasure = treasure;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTreasureId() {
        return treasureId;
    }

    public void setTreasureId(Long treasureId) {
        this.treasureId = treasureId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
