package com.guanyitong.mapper;

import com.guanyitong.model.WithdrawalMoney;

import java.util.Map;

public interface WithdrawMoneyDao {
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
