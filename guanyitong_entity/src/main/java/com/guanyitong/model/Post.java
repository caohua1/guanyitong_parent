package com.guanyitong.model;

import java.io.Serializable;
import java.util.Date;
//职位表实体类
public class Post implements Serializable {
    private Long id;
    private String pname;
    private Date pcreateTime;
    private Date pupdateTime;
    private Integer pstatus;
    private String pdescribe;//描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPcreateTime() {
        return pcreateTime;
    }

    public void setPcreateTime(Date pcreateTime) {
        this.pcreateTime = pcreateTime;
    }

    public Date getPupdateTime() {
        return pupdateTime;
    }

    public void setPupdateTime(Date pupdateTime) {
        this.pupdateTime = pupdateTime;
    }

    public Integer getPstatus() {
        return pstatus;
    }

    public void setPstatus(Integer pstatus) {
        this.pstatus = pstatus;
    }

    public String getPdescribe() {
        return pdescribe;
    }

    public void setPdescribe(String pdescribe) {
        this.pdescribe = pdescribe;
    }
}
