package com.guanyitong.service.impl;

import com.guanyitong.mapper.AccountManagerDao;
import com.guanyitong.mapper.WithdrawMoneyDao;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class WithdrawMoneyServiceImpl implements WithdrawMoneyService{
    @Autowired
    private WithdrawMoneyDao withdrawMoneyDao;
    @Autowired
    private AccountManagerDao accountManagerDao;

    /**
     * 提现，添加数据
     * @param withdrawalMoney
     * @return
     */
    @Transactional
    @Override
    public Boolean insertWithdrawMoney(WithdrawalMoney withdrawalMoney) {
        if(withdrawalMoney.getBorrowMoneyUserId()!=null && !("").equals(withdrawalMoney.getBorrowMoneyUserId())){
            Integer i = withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);//借款人申请提现
            return i>0;
        }
        if(withdrawalMoney.getUserId()!=null){
            Integer i = withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);//出借人申请提现，余额减少
            Map map = new HashMap();
            map.put("txMoney",0-Integer.parseInt(withdrawalMoney.getTxMoney()));
            map.put("userId",withdrawalMoney.getUserId());
            Integer j = accountManagerDao.updateYuE(map);
            return i>0 && j>0;
        }
        return null;
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
