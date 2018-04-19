package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Role;


public interface PostService {
    /**
     * 添加职位
     * @param post
     * @return
     */
    public Integer insertPost(Role post);

    /**
     * 查询所有的职位信息（分页）
     * @return
     */
    public PageInfo<Role> selectPost(Role post, Integer pageNum, Integer pageSize);

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
