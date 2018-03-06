package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BackMoney;

import java.util.Map;

public interface BackMoneyService {

    /**
     * (分页)查询所有某个用户的某个出借的回款详情
     * @param map
     * @return
     */
    public PageInfo<BackMoney> selectBackMoneyByDealId(Map map,Integer pageNum,Integer pageSize);
}
