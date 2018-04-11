package com.guanyitong.mapper;
import com.guanyitong.model.BorrowMoneyUser;

import java.util.List;
import java.util.Map;

public interface BorrowMoneyUserDao {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);
    public List<BorrowMoneyUser> selectAllBorrowUser(Map map);
    public Integer updateStatus(Map map);
    public int selectCount(Map map);
    public List<BorrowMoneyUser> selectDimId(Long dimId);

}
