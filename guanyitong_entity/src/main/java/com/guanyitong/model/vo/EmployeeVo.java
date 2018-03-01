package com.guanyitong.model.vo;

import com.guanyitong.model.Employee;
//员工的vo
public class EmployeeVo extends Employee {
    private String dname;//部门名称
    private String pname;//职位名称

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
