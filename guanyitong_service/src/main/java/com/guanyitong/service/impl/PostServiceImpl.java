package com.guanyitong.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.PostDAO;
import com.guanyitong.model.Post;
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
    public Integer insertPost(Post post) {
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
    public PageInfo<Post> selectPost(Post post, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Post> posts = postDAO.selectPost(post);
        PageInfo<Post> pageInfo = new PageInfo<Post>(posts);
        return pageInfo;
    }

    /**
     * 进入编辑页面，回显数据
     * @param id
     * @return
     */
    @Override
    public Post selectPostById(Long id) {
        Post post = postDAO.selectPostById(id);
        return post;
    }

    /**
     * 编辑职位
     * @param post
     * @return
     */
    @Transactional
    @Override
    public Integer updatePost(Post post) {
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
