package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.Post;


public interface PostService {
    /**
     * 添加职位
     * @param post
     * @return
     */
    public Integer insertPost(Post post);

    /**
     * 查询所有的职位信息（分页）
     * @return
     */
    public PageInfo<Post> selectPost(Post post,Integer pageNum,Integer pageSize);

    /**
     * 根据id查询职位，回显数据，进行编辑
     * @param id
     * @return
     */
    public Post selectPostById(Long id);
    /**
     * 编辑职位信息
     * @param post
     * @return
     */
    public Integer updatePost(Post post);

    /**
     * 删除职位
     * @param id
     * @return
     */
    public Integer deletePost(Long id);
}
