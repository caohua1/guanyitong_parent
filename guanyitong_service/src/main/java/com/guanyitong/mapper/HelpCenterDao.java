package com.guanyitong.mapper;

import com.guanyitong.model.HelpCenter;

import java.util.List;

public interface HelpCenterDao {

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
    public List<HelpCenter> selectHelpCenter();

    public Integer selectHelpCenterCount();

    /**
     * 修改或删除
     * @param helpCenter
     * @return
     */
    public Integer updateHelpCenter(HelpCenter helpCenter);

    public HelpCenter selectHelpCenterById(Long id);
}
