package com.guanyitong.service;

import com.guanyitong.model.BorrowMoneyUser;

public interface BorrowMoneyUserService {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);

}
