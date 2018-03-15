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
     * 分页查询银行卡信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserBankcard> selectUserBankcardDao(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<UserBankcard> userBankcards = userBankcardDao.selectUserBankcardDao();
        PageInfo<UserBankcard> pageInfo = new PageInfo<UserBankcard>(userBankcards);
        return pageInfo;
    }

    /**
     * 模糊查询
     * @param conditionMap
     * @return
     */
    @Override
    public UserBankcard selectByUserBankcard(Map conditionMap) {
        return userBankcardDao.selectByUserBankcard(conditionMap);
    }

    /**
     * 按条件查询（根据userName）
     * @param userNameMap
     * @return
     */
    @Override
    public UserBankcard seelctByUserName(Map userNameMap) {
        return userBankcardDao.seelctByUserName(userNameMap);
    }

    /**
     * 绑定借款用户银行卡
     * @param userBankcard
     * @return
     */
    @Override
    public int insertUserBankcardDao(UserBankcard userBankcard) {
        return userBankcardDao.insertUserBankcardDao(userBankcard);
    }
}
