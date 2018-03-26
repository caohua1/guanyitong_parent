package com.guanyitong.mapper;

import com.guanyitong.model.UserDealBackMoneyRecord;

public interface UserBackMoneyRecordDAO {
    /**
     * 添加出借人的回款记录
     * @param userDealBackMoneyRecord
     * @return
     */
    public Integer insertUserBackMoneyRecord(UserDealBackMoneyRecord userDealBackMoneyRecord);
}
