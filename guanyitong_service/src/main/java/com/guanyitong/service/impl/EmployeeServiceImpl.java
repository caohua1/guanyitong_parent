package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.EmployeeDAO;
import com.guanyitong.model.Employee;
import com.guanyitong.model.vo.EmployeeVo;
import com.guanyitong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.MD5Util;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    @Transactional
    @Override
    public Integer insertEmployee(Employee employee) {
        //employee.setEpassword(MD5Util.MD5(employee.getEpassword()));//对密码进行加密
        Integer i = employeeDAO.insertEmployee(employee);
        return i;
    }


    /**
     * 查询所有员工的总数
     * @param employeeVo
     * @return
     */
    @Override
    public Integer selectEmployeeCount(EmployeeVo employeeVo) {
        return employeeDAO.selectEmployeeCount(employeeVo);
    }

    /**
     * 分页查询员工信息（条件查询）
     * @param employeeVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<EmployeeVo> selectEmployee(EmployeeVo employeeVo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<EmployeeVo> employees = employeeDAO.selectEmployee(employeeVo);
        PageInfo<EmployeeVo> pageInfo = new PageInfo<EmployeeVo>(employees);
        return pageInfo;
    }

    /**
     * 编辑页面，回显员工信息
     * @param id
     * @return
     */
    @Override
    public EmployeeVo selectEmployeeById(Long id) {
        EmployeeVo employee = employeeDAO.selectEmployeeById(id);
        return employee;
    }

    /**
     * 修改员工信息
     * @param employee
     * @return
     */
    @Transactional
    @Override
    public Integer updateEmployee(Employee employee) {
        Integer i = employeeDAO.updateEmployee(employee);
        return i;
    }

    /**
     * 删除员工信息
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Integer deleteEmployee(Long id) {
        Integer i = employeeDAO.deleteEmployee(id);
        return i;
    }

    /**
     * 登陆
     * @param employee
     * @return
     */
    @Override
    public Employee login(Employee employee) {
        return employeeDAO.login(employee);
    }


}
