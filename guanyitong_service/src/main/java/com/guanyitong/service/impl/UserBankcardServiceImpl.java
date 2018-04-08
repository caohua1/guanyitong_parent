package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.UserBankcardDao;
import com.guanyitong.model.UserBankcard;
import com.guanyitong.service.UserBankcardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 银行卡管理业务层
 */
@Service
public class UserBankcardServiceImpl implements UserBankcardService{

    @Autowired
    private UserBankcardDao userBankcardDao;



    /**
     * 条件查询查询（借款人银行卡信息）
     * @param conditionMap
     * @return
     */
    @Override
    public PageInfo<UserBankcard> selectByUserBankcard(Map conditionMap,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<UserBankcard> userBankcards = userBankcardDao.selectByUserBankcard(conditionMap);
        PageInfo<UserBankcard> pageInfo = new PageInfo<UserBankcard>(userBankcards);
        return pageInfo;
    }

    /**
     * 绑定借款人银行卡
     * @param userBankcard
     * @return
     */
    @Transactional
    @Override
    public int insertUserBankcardDao(UserBankcard userBankcard) {
        return userBankcardDao.insertUserBankcardDao(userBankcard);
    }

    /**
     * 根据id查看详情
     * @param id
     * @return
     */
    @Override
    public UserBankcard selectUserBankcardById(Long id) {
        return userBankcardDao.selectUserBankcardById(id);
    }


    /**
     * 模糊查询borrowMoneyUserId
     * @param dimId
     * @return
     */
    @Override
    public List<UserBankcard> selectDimId(Long dimId) {
        return userBankcardDao.selectDimId(dimId);
    }
}
