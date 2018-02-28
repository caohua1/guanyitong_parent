package com.guanyitong.model;

import java.io.Serializable;
import java.util.Date;

public class Dept implements Serializable {
    private Long id;
    private String dname;
    private Date dcreateTime;
    private Date dupdateTime;
    private Integer dstatus;
    private String describes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Date getDcreateTime() {
        return dcreateTime;
    }

    public void setDcreateTime(Date dcreateTime) {
        this.dcreateTime = dcreateTime;
    }

    public Date getDupdateTime() {
        return dupdateTime;
    }

    public void setDupdateTime(Date dupdateTime) {
        this.dupdateTime = dupdateTime;
    }

    public Integer getDstatus() {
        return dstatus;
    }

    public void setDstatus(Integer dstatus) {
        this.dstatus = dstatus;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }
}
