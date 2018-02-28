package com.guanyitong.mapper;

import com.guanyitong.model.Dept;

import java.util.List;


public interface DeptDAO {
    /**
     * 添加部门
     * @param dept
     * @return
     */
    public Integer insertDept(Dept dept);

    /**
     * 查询所有的部门
     * @return
     */
    public List<Dept> selectAllDept(Dept dept);

    /**
     * 修改部门信息
     * @param dept
     * @return
     */
    public Integer updateDept(Dept dept);

    /**
     * 删除某个部门
     * @param id
     * @return
     */
    public Integer deleteDept(Long id);
}
