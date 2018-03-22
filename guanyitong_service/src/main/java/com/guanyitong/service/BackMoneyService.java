package com.guanyitong.service;

import com.guanyitong.model.BackMoney;

import java.util.List;

public interface BackMoneyService {
    /**
     * 批量插入（还款计划）
     * @param list
     * @return
     */
    public Integer insertBatchBackMoney(List<BackMoney> list);
}
