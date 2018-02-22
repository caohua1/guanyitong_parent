package com.guanyitong.service.impl;

import com.guanyitong.mapper.UserSignDao;
import com.guanyitong.model.UserSignCalc;
import com.guanyitong.model.UserSignDetail;
import com.guanyitong.service.UserSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserSignServiceImpl implements UserSignService{
    @Autowired
    private UserSignDao userSignDao;

    /**
     * 签到，添加表数据
     * @param userSignDetail
     * @return
     */
    @Transactional
    @Override
    public int insertUserSignDetail(UserSignDetail userSignDetail) {
        int i = userSignDao.insertUserSignDetail(userSignDetail);
        UserSignCalc userSignCalc = userSignDao.selectUserSignCalc(userSignDetail.getUserId());
        int i1=0;
        int i2=0;
        if(userSignCalc==null){//这个月第一次签到，添加签到天数默认是1
            UserSignCalc userSignCalc1 = new UserSignCalc();
            userSignCalc1.setUserId(userSignDetail.getUserId());
            userSignCalc1.setContinueDays(1);
            i1 = userSignDao.insertUserSignCalc(userSignCalc1);
        }else{//不是第一签到就修改签到天数
            i2 = userSignDao.updateSignDays(userSignDetail.getUserId());
        }
        return (i>0&&(i1>0||i2>0))?1:0;
    }

    /**
     * 查询用户最近一天签到的日期,看是否今天已经签到
     * @param userId
     * @return
     */
    @Override
    public UserSignDetail selectUserBeforeSignDate(Long userId) {
        return userSignDao.selectUserBeforeSignDate(userId);
    }

    /**
     * 查询user_sign_detail表有没有此用户信息
     * @param userId
     * @return
     */
    @Override
    public UserSignDetail selectUserSignDetail(Long userId) {
        return userSignDao.selectUserSignDetail(userId);
    }

    /**
     * 每月1号清空user_sign_detail
     * @return
     */
    @Override
    public int deleteUserSignDetail() {
        return userSignDao.deleteUserSignDetail();
    }

    /**
     * 查询user_sign_calc表有没有此用户信息
     * @param userId
     * @return
     */
    @Override
    public UserSignCalc selectUserSignCalc(Long userId) {
        return userSignDao.selectUserSignCalc(userId);
    }

    /**
     * 清空user_sign_calc
     * @return
     */
    @Override
    public int deleteUserSignCalc() {
        return userSignDao.deleteUserSignCalc();
    }

    /**
     * 查询所有用户的签到次数
     * @return
     */
    @Override
    public List<UserSignCalc> selectAllUserSignNums() {
        return userSignDao.selectAllUserSignNums();
    }
}
