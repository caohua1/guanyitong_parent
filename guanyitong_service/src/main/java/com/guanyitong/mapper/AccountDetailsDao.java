package com.guanyitong.mapper;

import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;

import java.util.List;
import java.util.Map;

/**
 * 账户明细Dao
 */
public interface AccountDetailsDao {

    //查询出借人充值记录
    public List<RechargeMoney> selectRechargeList(Map rechargeMap);

    //查询出借人出借记录
    public List<UserDealMoney> selectUserDealMoneyList(Map UserDealMoneyMap);

    //查询出借人回款记录和收益记录
    public List<UserDealBackMoneyRecord> selectReturnedEarningsMoney(Map ReturnedEarningsMap);




}
