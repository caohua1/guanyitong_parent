package com.guanyitong.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.PostDAO;
import com.guanyitong.model.Permission;
import com.guanyitong.model.Role;
import com.guanyitong.model.RolePermission;
import com.guanyitong.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    /**
     * 添加职位
     * 批量添加角色的权限（中间表role_permission）
     * @param post
     * @return
     */
    @Transactional
    @Override
    public Boolean insertPost(Role post,String permissionIds) {
        Integer j = postDAO.insertPost(post);
        Map map = new HashMap();
        map.put("postId",post.getId());
        int n=0;
        if(permissionIds!=null && permissionIds.length()>0){
            String[] ids = permissionIds.split(",");
            for(int i=0;i<ids.length;i++){
                map.put("permissionId",Long.valueOf(ids[i]));
                n += postDAO.insertPermissionRole(map);
            }
        }
        return n==permissionIds.split(",").length && j>0;
    }

    /**
     * 查询某角色得权限
     * @param postId
     * @return
     */
    @Override
    public List<Permission> selectPermissionRole(Long postId) {
        return postDAO.selectPermissionRole(postId);
    }




    /**
     * 分页查询职位（条件查询）
     * @param post
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<Role> selectPost(Role post, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Role> posts = postDAO.selectPost(post);
        PageInfo<Role> pageInfo = new PageInfo<Role>(posts);
        return pageInfo;
    }

    /**
     * 查询总数量
     * @param role
     * @return
     */
    @Override
    public Integer selectPostCount(Role role) {
        return postDAO.selectPostCount(role);
    }

    /**
     * 进入编辑页面，回显数据
     * @param id
     * @return
     */
    @Override
    public Role selectPostById(Long id) {
        Role post = postDAO.selectPostById(id);
        return post;
    }

    /**
     * 编辑职位
     * @param post
     * @return
     */
    @Transactional
    @Override
    public Boolean updatePost(Role post,String permissionIds) {
        //先删除之前的权限
        Integer m = postDAO.deletePermissionRole(post.getId());
        //修改权限名称
        Integer j = postDAO.updatePost(post);
        //重新添加权限
        Map map = new HashMap();
        map.put("postId",post.getId());
        int n=0;
        if(permissionIds!=null && permissionIds.length()>0){
            String[] ids = permissionIds.split(",");
            for(int i=0;i<ids.length;i++){
                map.put("permissionId",Long.valueOf(ids[i]));
                n += postDAO.insertPermissionRole(map);
            }
        }
        return m>0 && j>0 &&  n==permissionIds.split(",").length;
    }

    /**
     * 删除职位
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Integer deletePost(Long id) {
        Integer i = postDAO.deletePost(id);
        return i;
    }
}
