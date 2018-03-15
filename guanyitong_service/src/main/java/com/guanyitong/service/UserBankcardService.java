package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;

import java.util.Map;


public interface UserBankcardService {

    public PageInfo<UserBankcard> selectUserBankcardDao(Integer pageNum, Integer pageSize);

    public UserBankcard selectByUserBankcard(Map conditionMap);

    public UserBankcard seelctByUserName(Map userNameMap);

    public int insertUserBankcardDao(UserBankcard userBankcard);
}
