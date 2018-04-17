package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;


import java.util.Map;

public interface AccountDetailsService {

    //查询出借人充值记录
    public PageInfo<RechargeMoney> selectRechargeList(Map rechargeMap,Integer pageNum,Integer pageSize);

    //查询出借人出借记录
    public PageInfo<UserDealMoney> selectUserDealMoneyList(Map UserDealMoneyMap,Integer pageNum,Integer pageSize);

    //查询出借人回款记录和收益记录
    public PageInfo<UserDealBackMoneyRecord> selectReturnedEarningsMoney(Map ReturnedEarningsMap,Integer pageNum,Integer pageSize);

    /**
     * 充值记录总数量
     * @param map
     * @return
     */
    public Integer selectRechargeCount(Map map);
    /**
     * 出借记录总数量
     * @param map
     * @return
     */
    public Integer selectUserDealMoneyCount(Map map);

    /**
     * 回款记录总数量
     * @param map
     * @return
     */
    public Integer selectReturnedEarningsMoneyCount(Map map);
}
