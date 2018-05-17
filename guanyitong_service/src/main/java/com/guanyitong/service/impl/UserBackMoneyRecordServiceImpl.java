package com.guanyitong.service.impl;

import com.guanyitong.mapper.UserBackMoneyRecordDAO;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.service.UserBackMoneyRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBackMoneyRecordServiceImpl implements UserBackMoneyRecordService {

    @Autowired
    private UserBackMoneyRecordDAO userBackMoneyRecordDAO;

    /**
     * 查询某用户的回款记录
     * @param userId
     * @return
     */
    @Override
    public List<UserDealBackMoneyRecord> selectUserBackMoneyRecord(Long userId) {
        return userBackMoneyRecordDAO.selectUserBackMoneyRecord(userId);
    }
}
