package com.guanyitong.model;

import java.io.Serializable;
//问卷选项表
public class UserQuestionContent implements Serializable,Comparable<UserQuestionContent>{
    private Integer id;
    private Integer questionId;
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(UserQuestionContent o) {
        return this.getId().compareTo(o.getId());//升序
    }
}
