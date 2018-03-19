package com.guanyitong.service.impl;

import com.guanyitong.mapper.WithdrawMoneyDao;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class WithdrawMoneyServiceImpl implements WithdrawMoneyService{
    @Autowired
    private WithdrawMoneyDao withdrawMoneyDao;

    /**
     * 提现，添加数据
     * @param withdrawalMoney
     * @return
     */
    @Transactional
    @Override
    public Integer insertWithdrawMoney(WithdrawalMoney withdrawalMoney) {
        return withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);
    }

    /**
     * 确认提现成功、失败（修改状态）,添加审核时间
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Integer updateStatus(Map map) {
        return withdrawMoneyDao.updateStatus(map);
    }
}
