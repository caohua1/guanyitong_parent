package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//员工实体类
public class Employee implements Serializable {
    private Long id;
    private String ename;
    private String esex;
    private Date ebirth;
    private String eidcard;
    private String ephone;
    private String email;
    private Long edeptno;
    private String epostno;
    private Date ecreateTime;
    private Date eupdateTime;
    private Integer estatus;
    private String epassword;
    private List<Role> roleList;//一个用户具有多个角色

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEsex() {
        return esex;
    }

    public void setEsex(String esex) {
        this.esex = esex;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEbirth() {
        return ebirth;
    }

    public void setEbirth(Date ebirth) {
        this.ebirth = ebirth;
    }

    public String getEidcard() {
        return eidcard;
    }

    public void setEidcard(String eidcard) {
        this.eidcard = eidcard;
    }

    public String getEphone() {
        return ephone;
    }

    public void setEphone(String ephone) {
        this.ephone = ephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getEdeptno() {
        return edeptno;
    }

    public void setEdeptno(Long edeptno) {
        this.edeptno = edeptno;
    }

    public String getEpostno() {
        return epostno;
    }

    public void setEpostno(String epostno) {
        this.epostno = epostno;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEcreateTime() {
        return ecreateTime;
    }

    public void setEcreateTime(Date ecreateTime) {
        this.ecreateTime = ecreateTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getEupdateTime() {
        return eupdateTime;
    }

    public void setEupdateTime(Date eupdateTime) {
        this.eupdateTime = eupdateTime;
    }

    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    public String getEpassword() {
        return epassword;
    }

    public void setEpassword(String epassword) {
        this.epassword = epassword;
    }
}
