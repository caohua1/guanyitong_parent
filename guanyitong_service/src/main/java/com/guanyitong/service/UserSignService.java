package com.guanyitong.service;

import com.guanyitong.model.UserSignCalc;
import com.guanyitong.model.UserSignDetail;

import java.util.List;

public interface UserSignService {
    /**
     * 签到，添加表数据
     * @param userSignDetail
     * @return
     */
    public int insertUserSignDetail(UserSignDetail userSignDetail);

    /**
     * 查询用户最近一天签到的日期,看是否今天已经签到
     * @param userId
     * @return
     */
    public UserSignDetail selectUserBeforeSignDate(Long userId);

    /**
     * 查询user_sign_detail表有没有此用户信息
     * @param userId
     * @return
     */
    public UserSignDetail selectUserSignDetail(Long userId);

    /**
     * 每月1号清空user_sign_detail
     * @return
     */
    public int deleteUserSignDetail();

    /**
     * 查询user_sign_calc表有没有此用户信息
     * @param userId
     * @return
     */
    public UserSignCalc selectUserSignCalc(Long userId);

    /**
     * 每月1号清空user_sign_calc
     * @return
     */
    public int deleteUserSignCalc();

    /**
     * 查询所有用户的签到次数
     * @return
     */
    public List<UserSignCalc> selectAllUserSignNums();
}
