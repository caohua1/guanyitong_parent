package com.guanyitong.service.impl;

import com.guanyitong.mapper.PermissionDao;
import com.guanyitong.model.Permission;
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

    /**
     * 查询所有得父类得权限
     * @return
     */
    @Override
    public List<Permission> selectParentPermission() {
        return null;
    }

    /**
     * 查询某父类权限下得所有权限
     * @return
     */
    @Override
    public List<Permission> selectChildPermission(Long parentId) {
        return null;
    }


}
