package com.guanyitong.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.PostDAO;
import com.guanyitong.model.Role;
import com.guanyitong.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    /**
     * 添加职位
     * @param post
     * @return
     */
    @Transactional
    @Override
    public Integer insertPost(Role post) {
        Integer i = postDAO.insertPost(post);
        return i;
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
    public Integer updatePost(Role post) {
        Integer i = postDAO.updatePost(post);
        return i;
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
