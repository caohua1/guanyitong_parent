package com.guanyitong.mapper;

import com.guanyitong.model.Permission;
import com.guanyitong.model.vo.PermissionVo;

import java.util.List;

public interface PermissionDao {

    /**
     * 查询某员工的权限
     * @param employeeId
     * @return
     */
    public List<PermissionVo> permissions(Long employeeId);

    /**
     * 查询某父级菜单下的所有子菜单
     * @param parentId
     * @return
     */
    public List<PermissionVo> child_permissions(Long parentId);

    /**
     * 查询所有得父类得权限
     * @return
     */
    public List<Permission> selectParentPermission();

    /**
     * 查询某父类权限下得所有权限
     * @return
     */
    public List<Permission> selectChildPermission(Long parentID);
}
