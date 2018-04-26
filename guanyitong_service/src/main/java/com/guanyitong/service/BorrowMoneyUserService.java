package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BorrowMoneyUser;

import java.util.List;
import java.util.Map;

public interface BorrowMoneyUserService {

    public Integer insertUser(BorrowMoneyUser borrowMoneyUser);
    public BorrowMoneyUser selectBorrowMoneyUser(Long id);
    public PageInfo<BorrowMoneyUser> selectAllBorrowUser(Map map,Integer pageNum,Integer pageSize);
    public Integer updateStatus(Map map);
    public int selectCount(Map map);
    public List<BorrowMoneyUser> selectDimId(Long dimId);
    public Integer updateBorrowUser(BorrowMoneyUser borrowMoneyUser);

}
