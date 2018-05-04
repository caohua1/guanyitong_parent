package com.guanyitong.mapper;

import com.guanyitong.model.Permission;
import com.guanyitong.model.Role;
import com.guanyitong.model.RolePermission;

import java.util.List;
import java.util.Map;

public interface PostDAO {
    /**
     * 添加职位
     * @param post
     * @return
     */
    public Integer insertPost(Role post);

    /**
     * 批量添加角色的权限（中间表role_permission）
     * @param map
     * @return
     */
    public Integer insertPermissionRole(Map map);

    /**
     * 查询某角色得权限
     * @param postId
     * @return
     */
    public List<Permission> selectPermissionRole(Long postId);

    /**
     * 删除某角色得权限
     * @param postId
     * @return
     */
    public Integer deletePermissionRole(Long postId);
    /**
     * 查询所有的职位信息（分页）
     * @return
     */
    public List<Role> selectPost(Role post);

    /**
     * 查询角色得总数量
     * @param role
     * @return
     */
    public Integer selectPostCount(Role role);

    /**
     * 根据id查询职位，回显数据，进行编辑
     * @param id
     * @return
     */
    public Role selectPostById(Long id);
    /**
     * 编辑职位信息
     * @param post
     * @return
     */
    public Integer updatePost(Role post);

    /**
     * 删除职位
     * @param id
     * @return
     */
    public Integer deletePost(Long id);
}
