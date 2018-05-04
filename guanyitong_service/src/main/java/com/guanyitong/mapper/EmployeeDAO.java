package com.guanyitong.mapper;

import com.guanyitong.model.Employee;
import com.guanyitong.model.vo.EmployeeVo;

import java.util.List;

public interface EmployeeDAO {

    /**
     * 添加员工信息
     * @param employee
     * @return
     */
    public Integer insertEmployee(Employee employee);
    /**
     * 分页查询员工信息（条件查询）
     * @param employeeVo
     * @return
     */
    public List<EmployeeVo> selectEmployee(EmployeeVo employeeVo);

    /**
     * 查询员工总数量
     * @param employeeVo
     * @return
     */
    public Integer selectEmployeeCount(EmployeeVo employeeVo);

    /**
     * 查询某个员工的信息，编辑页面回显数据
     * @param id
     * @return
     */
    public EmployeeVo selectEmployeeById(Long id);

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
     * 登陆
     * @param employee
     * @return
     */
    public Employee login(Employee employee);

}
