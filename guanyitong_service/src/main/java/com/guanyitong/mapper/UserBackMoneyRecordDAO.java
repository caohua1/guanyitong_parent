package com.guanyitong.mapper;

import com.guanyitong.model.UserDealBackMoneyRecord;

import java.util.List;

public interface UserBackMoneyRecordDAO {
    /**
     * 添加出借人的回款记录
     * @param userDealBackMoneyRecord
     * @return
     */
    public Integer insertUserBackMoneyRecord(UserDealBackMoneyRecord userDealBackMoneyRecord);

    /**
     * 资金账户管理
     * 查询某用户的累计收益
     * @param userId
     * @return
     */
    public Double selectUserBackMoney(Long userId);

    /**
     * 查询某用户的回款记录
     * @param userId
     * @return
     */
    public List<UserDealBackMoneyRecord> selectUserBackMoneyRecord(Long userId);
}
