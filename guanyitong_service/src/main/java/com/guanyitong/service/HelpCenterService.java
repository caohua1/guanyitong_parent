package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.HelpCenter;

public interface HelpCenterService {
    /**
     * 添加帮助中心
     * @param helpCenter
     * @return
     */
    public Integer insertHelpCenter(HelpCenter helpCenter);

    /**
     * 分页查询所有的问题
     * @return
     */
    public PageInfo<HelpCenter> selectHelpCenter(Integer pageNum,Integer pageSize);
    public Integer selectHelpCenterCount();


    /**
     * 修改或删除
     * @param helpCenter
     * @return
     */
    public Integer updateHelpCenter(HelpCenter helpCenter);
    public HelpCenter selectHelpCenterById(Long id);
}
