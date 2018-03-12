package com.guanyitong.mapper;

import com.guanyitong.model.BorrowMoneyUser;

public interface BorrowMoneyUserDao {
    /**
     * 添加借款人信息
     * @param borrowMoneyUser
     * @return
     */
    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);

    /**
     * 查询借款人信息
     */
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);
}
