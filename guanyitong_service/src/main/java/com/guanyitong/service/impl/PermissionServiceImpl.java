package com.guanyitong.service.impl;

import com.guanyitong.mapper.PermissionDao;
import com.guanyitong.model.vo.PermissionVo;
import com.guanyitong.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    /**
     * 查询某员工的权限
     * @param employeeId
     * @return
     */
    @Override
    public List<PermissionVo> permissions(Long employeeId) {
        return permissionDao.permissions(employeeId);
    }

    /**
     * 查询某父级菜单下的所有子菜单
     * @param parentId
     * @return
     */
    @Override
    public List<PermissionVo> child_permissions(Long parentId) {
        return permissionDao.child_permissions(parentId);
    }
}
