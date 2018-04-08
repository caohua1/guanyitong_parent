package com.guanyitong.service.impl;

import com.guanyitong.mapper.AccountDetailsDao;
import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.service.AccountDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {

    @Autowired
    private AccountDetailsDao accountDetailsDao;

    /**
     * 查询出借人充值记录
     * @param rechargeMap
     * @return
     */
    @Override
    public List<RechargeMoney> selectRechargeList(Map rechargeMap) {
        return accountDetailsDao.selectRechargeList(rechargeMap);
    }

    /**
     * 查询出借人出借记录
     * @param UserDealMoneyMap
     * @return
     */
    @Override
    public List<UserDealMoney> selectUserDealMoneyList(Map UserDealMoneyMap) {
        return accountDetailsDao.selectUserDealMoneyList(UserDealMoneyMap);
    }

    /**
     * 查询出借人回款记录和收益记录
     * @param ReturnedEarningsMap
     * @return
     */
    @Override
    public List<UserDealBackMoneyRecord> selectReturnedEarningsMoney(Map ReturnedEarningsMap) {
        return accountDetailsDao.selectReturnedEarningsMoney(ReturnedEarningsMap);
    }
}
