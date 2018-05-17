package com.guanyitong.service;

import com.guanyitong.model.UserDealBackMoneyRecord;

import java.util.List;

public interface UserBackMoneyRecordService {

    /**
     * 查询某用户的回款记录
     * @param userId
     * @return
     */
    public List<UserDealBackMoneyRecord> selectUserBackMoneyRecord(Long userId);
}
