package com.guanyitong.model;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
//用户获取冠豆的实体类
public class UserGuanDou implements Serializable {

   private Long id;
   private Long userId;
   private Integer guanDou;
   private String content;
   @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
   private Date creatTime;

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

    public Integer getGuanDou() {
        return guanDou;
    }

    public void setGuanDou(Integer guanDou) {
        this.guanDou = guanDou;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
