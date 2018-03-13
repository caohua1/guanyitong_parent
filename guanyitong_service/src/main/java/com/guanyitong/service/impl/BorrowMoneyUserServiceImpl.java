package com.guanyitong.service.impl;
import com.guanyitong.mapper.BorrowMoneyUserDao;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.service.BorrowMoneyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BorrowMoneyUserServiceImpl implements BorrowMoneyUserService {

    @Autowired
    private BorrowMoneyUserDao borrowMoneyUserDao;
    /**
     * 添加新用户
     * @param borrowMoneyUser
     */
    @Transactional
    @Override
    public Integer insertUser(BorrowMoneyUser borrowMoneyUser) {
        return borrowMoneyUserDao.insertUser(borrowMoneyUser);
    }

    /**
     * 查询认证审核信息
     */
    @Override
    public BorrowMoneyUser selectBorrowMoneyUser(Long id) {
        return borrowMoneyUserDao.selectBorrowMoneyUser(id);
    }



}
