package com.guanyitong.model;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
public class UserSignDetail implements Serializable {
    private Long id;
    private Long userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date signDate;

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

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }
}
