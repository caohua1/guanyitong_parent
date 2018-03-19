package com.guanyitong.service;

import com.guanyitong.model.WithdrawalMoney;

import java.util.Map;

public interface WithdrawMoneyService {
    /**
     * 提现，添加数据
     * @param withdrawalMoney
     * @return
     */
    public Integer insertWithdrawMoney(WithdrawalMoney withdrawalMoney);

    /**
     * 确认提现成功、失败（修改状态）,添加审核时间
     * @param map
     * @return
     */
    public Integer updateStatus(Map map);
}
