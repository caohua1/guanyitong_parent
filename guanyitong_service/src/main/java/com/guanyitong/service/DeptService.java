package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Dept;

public interface DeptService  {
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
    public PageInfo<Dept> selectAllDept(Dept dept, Integer pageNum, Integer pageSize);

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
