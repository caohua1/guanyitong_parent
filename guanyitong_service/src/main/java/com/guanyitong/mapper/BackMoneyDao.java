package com.guanyitong.mapper;

import com.guanyitong.model.BackMoney;

import java.util.List;

public interface BackMoneyDao {
    /**
     * 批量插入（还款计划）
     * @param list
     * @return
     */
    public Integer insertBatchBackMoney(List<BackMoney> list);
}
