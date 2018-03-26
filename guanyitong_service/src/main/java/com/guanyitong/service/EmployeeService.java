package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Employee;


public interface EmployeeService {
    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    public Integer insertEmployee(Employee employee);
    /**
     * 分页查询员工信息（条件查询）
     * @param employee
     * @return
     */
    public PageInfo<Employee> selectEmployee(Employee employee,Integer pageNum,Integer pageSize);
    /**
     * 查询某个员工的信息，编辑页面回显数据
     * @param id
     * @return
     */
    public Employee selectEmployeeById(Long id);
    /**
     * 编辑员工信息
     * @param employee
     * @return
     */
    public Integer updateEmployee(Employee employee);
    /**
     * 删除员工信息
     * @param id
     * @return
     */
    public Integer deleteEmployee(Long id);

    /**
     * 登陆查询
     */
    public boolean register(Long edeptno,String epassword);
}
