package com.guanyitong.service;

import com.guanyitong.model.BackMoney;
import com.guanyitong.model.vo.BackMoneyVo;

import java.util.List;
import java.util.Map;

public interface BackMoneyService {
    /**
     * 批量插入（还款计划）
     * @param list
     * @return
     */
    public Integer insertBatchBackMoney(List<BackMoney> list);
    /**
     * 根据borrowMoneyUserId和productInfoId查询用户的还款计划
     * @param map
     * @return
     */
    public BackMoneyVo selectBackMoney(Map map);
    /**
     * 借款人还款
     * @param map
     * @return
     */
    public Boolean updateStatus(BackMoney backMoney);
}
