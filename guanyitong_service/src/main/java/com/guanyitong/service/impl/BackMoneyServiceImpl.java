package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.BackMoneyDao;
import com.guanyitong.model.BackMoney;
import com.guanyitong.service.BackMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BackMoneyServiceImpl implements BackMoneyService{

    @Autowired
    private BackMoneyDao backMoneyDao;

    /**
     * (分页)查询所有某个用户的某个出借的回款详情
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<BackMoney> selectBackMoneyByDealId(Map map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BackMoney> backMonies = backMoneyDao.selectBackMoneyByDealId(map);
        PageInfo<BackMoney> pageInfo = new PageInfo<BackMoney>(backMonies);
        return pageInfo;
    }
}
