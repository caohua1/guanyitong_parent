package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Permission;
import com.guanyitong.model.Role;
import com.guanyitong.model.RolePermission;

import java.util.List;
import java.util.Map;


public interface PostService {
    /**
     * 添加职位
     * 批量添加角色的权限（中间表role_permission）
     * @param post
     * @return
     */
    public Boolean insertPost(Role post,String permissionIds);

    /**
     * 查询某角色得权限
     * @param postId
     * @return
     */
    public List<Permission> selectPermissionRole(Long postId);



    /**
     * 查询所有的职位信息（分页）
     * @return
     */
    public PageInfo<Role> selectPost(Role post, Integer pageNum, Integer pageSize);

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
    public Boolean updatePost(Role post,String permissionIds);

    /**
     * 删除职位
     * @param id
     * @return
     */
    public Integer deletePost(Long id);
}
