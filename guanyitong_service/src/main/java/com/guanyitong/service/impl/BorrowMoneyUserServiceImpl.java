package com.guanyitong.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.BorrowMoneyUserDao;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.service.BorrowMoneyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    /**
     * 借款人认证资料审核列表，（分页）0待审核 (1 3 审核未通过) 4审核通过
     * @param map
     * @return
     */
    @Override
    public PageInfo<BorrowMoneyUser> selectAllBorrowUser(Map map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BorrowMoneyUser> borrowMoneyUsers = borrowMoneyUserDao.selectAllBorrowUser(map);
        PageInfo<BorrowMoneyUser> pageInfo = new PageInfo<BorrowMoneyUser>(borrowMoneyUsers);
        return pageInfo;
    }

    /**
     * 审核借款人的基本信息
     * 0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款待审核
     * 3 借款审核失败  4 借款审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Integer updateStatus(Map map) {
        return borrowMoneyUserDao.updateStatus(map);
    }

    /**
     * 查询总用户数（列表展示）
     * @param map
     * @return
     */
    @Override
    public int selectCount(Map map) {
        return borrowMoneyUserDao.selectCount(map);
    }


}
