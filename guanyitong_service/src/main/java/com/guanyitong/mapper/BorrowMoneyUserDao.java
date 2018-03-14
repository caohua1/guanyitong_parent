package com.guanyitong.mapper;
import com.guanyitong.model.BorrowMoneyUser;

import java.util.List;
import java.util.Map;

public interface BorrowMoneyUserDao {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);
    public List<BorrowMoneyUser> selectAllBorrowUser(BorrowMoneyUser borrowMoneyUser);
    public Integer updateStatus(Map map);

}
