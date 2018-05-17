package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.HelpCenterDao;
import com.guanyitong.model.HelpCenter;
import com.guanyitong.service.HelpCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HelpCenterServiceImpl implements HelpCenterService {
    @Autowired
    private HelpCenterDao helpCenterDao;

    /**
     * 添加帮助中心
     * @param helpCenter
     * @return
     */
    @Override
    @Transactional
    public Integer insertHelpCenter(HelpCenter helpCenter) {
        return helpCenterDao.insertHelpCenter(helpCenter);
    }

    @Override
    public PageInfo<HelpCenter> selectHelpCenter(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<HelpCenter> helpCenters = helpCenterDao.selectHelpCenter();
        PageInfo<HelpCenter> pageInfo = new PageInfo<HelpCenter>(helpCenters);
        return pageInfo;
    }

    @Override
    public Integer selectHelpCenterCount() {
        return helpCenterDao.selectHelpCenterCount();
    }

    @Override
    @Transactional
    public Integer updateHelpCenter(HelpCenter helpCenter) {
        return helpCenterDao.updateHelpCenter(helpCenter);
    }

    @Override
    public HelpCenter selectHelpCenterById(Long id) {
        return helpCenterDao.selectHelpCenterById(id);
    }
}
