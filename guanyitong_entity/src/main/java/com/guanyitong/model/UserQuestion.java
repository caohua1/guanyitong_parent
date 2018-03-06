package com.guanyitong.model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
//问卷实体类，用户的风险自评问卷（主题表）
public class UserQuestion implements Serializable,Comparable<UserQuestion> {
    private Integer id;
    private String question;
    private Date createTime;
    private Integer status;
    private List<UserQuestionContent> userQuestionContentList;

    public List<UserQuestionContent> getUserQuestionContentList() {
        return userQuestionContentList;
    }

    public void setUserQuestionContentList(List<UserQuestionContent> userQuestionContentList) {
        this.userQuestionContentList = userQuestionContentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    @Override
    public int compareTo(UserQuestion o) {
        return this.getId().compareTo(o.getId());//升序
    }
}
