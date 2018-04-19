package com.guanyitong.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

//职位表实体类
public class Role implements Serializable {
    private Long id;
    private String pname;
    private Date pcreateTime;
    private Date pupdateTime;
    private Integer pstatus;
    private String pdescribe;//描述
    private List<Permission> permissionList;//一个角色对应多个权限
    private List<Employee> userList;//一个角色对应多个用户

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getPcreateTime() {
        return pcreateTime;
    }

    public void setPcreateTime(Date pcreateTime) {
        this.pcreateTime = pcreateTime;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<Employee> getUserList() {
        return userList;
    }

    public void setUserList(List<Employee> userList) {
        this.userList = userList;
    }
}
