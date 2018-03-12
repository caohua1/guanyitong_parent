package com.guanyitong.mapper;

import com.guanyitong.model.BorrowMoneyUser;

import java.util.Map;

public interface BorrowMoneyUserDao {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public Integer updateStatus(Map map);
}
