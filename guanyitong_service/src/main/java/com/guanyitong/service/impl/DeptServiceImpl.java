package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.DeptDAO;
import com.guanyitong.model.Dept;
import com.guanyitong.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDAO deptDAO;
    @Transactional
    @Override
    public Integer insertDept(Dept dept) {
        Integer i = deptDAO.insertDept(dept);
        return i;
    }

    @Override
    public PageInfo<Dept> selectAllDept(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<Dept> depts =  deptDAO.selectAllDept();
        PageInfo<Dept> pageInfo = new PageInfo<Dept>(depts);
        return pageInfo;
    }

    @Override
    public List<Dept> selectAllDept() {
        List<Dept> depts = deptDAO.selectAllDept();
        return depts;
    }

    @Override
    public Dept selectDeptById(Long id) {
        Dept dept = deptDAO.selectDeptById(id);
        return dept;
    }

    @Transactional
    @Override
    public Integer updateDept(Dept dept) {
        Integer i = deptDAO.updateDept(dept);
        return i;
    }

    @Transactional
    @Override
    public Integer deleteDept(Long id) {
        Integer i = deptDAO.deleteDept(id);
        return i;
    }
}
