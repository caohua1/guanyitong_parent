package com.guanyitong.mapper;
import com.guanyitong.model.BorrowMoneyUser;

public interface BorrowMoneyUserDao {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);

}
