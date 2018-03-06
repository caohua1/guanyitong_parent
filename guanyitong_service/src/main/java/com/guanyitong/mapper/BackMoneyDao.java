package com.guanyitong.mapper;

import com.guanyitong.model.BackMoney;

import java.util.List;
import java.util.Map;
//后台管理
public interface BackMoneyDao {

    /**
     * (分页)查询所有某个用户的某个出借的回款详情
     * @param map
     * @return
     */
    public List<BackMoney> selectBackMoneyByDealId(Map map);
}
