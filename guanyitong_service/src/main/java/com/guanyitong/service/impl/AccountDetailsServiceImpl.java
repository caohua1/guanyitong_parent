package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<RechargeMoney> selectRechargeList(Map rechargeMap,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<RechargeMoney> rechargeMonies = accountDetailsDao.selectRechargeList(rechargeMap);
        PageInfo<RechargeMoney> pageInfo = new PageInfo<RechargeMoney>(rechargeMonies);
        return pageInfo;
    }

    /**
     * 查询出借人出借记录
     * @param UserDealMoneyMap
     * @return
     */
    @Override
    public PageInfo<UserDealMoney> selectUserDealMoneyList(Map UserDealMoneyMap,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserDealMoney> userDealMonies = accountDetailsDao.selectUserDealMoneyList(UserDealMoneyMap);
        PageInfo<UserDealMoney> pageInfo = new PageInfo<UserDealMoney>(userDealMonies);
        return pageInfo;
    }

    /**
     * 查询出借人回款记录和收益记录
     * @param ReturnedEarningsMap
     * @return
     */
    @Override
    public PageInfo<UserDealBackMoneyRecord> selectReturnedEarningsMoney(Map ReturnedEarningsMap,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<UserDealBackMoneyRecord> userDealBackMoneyRecords = accountDetailsDao.selectReturnedEarningsMoney(ReturnedEarningsMap);
        PageInfo<UserDealBackMoneyRecord> pageInfo = new PageInfo<UserDealBackMoneyRecord>(userDealBackMoneyRecords);
        return pageInfo;
    }

    /**
     * 充值记录总数量
     * @param map
     * @return
     */
    @Override
    public Integer selectRechargeCount(Map map) {
        return accountDetailsDao.selectRechargeCount(map);
    }

    /**
     * 出借记录总数量
     * @param map
     * @return
     */
    @Override
    public Integer selectUserDealMoneyCount(Map map) {
        return accountDetailsDao.selectUserDealMoneyCount(map);
    }

    /**
     * 回款记录总数量
     * @param map
     * @return
     */
    @Override
    public Integer selectReturnedEarningsMoneyCount(Map map) {
        return accountDetailsDao.selectReturnedEarningsMoneyCount(map);
    }
}
