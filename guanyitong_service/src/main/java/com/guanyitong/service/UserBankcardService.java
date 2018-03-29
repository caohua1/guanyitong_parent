package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;

import java.util.Map;


public interface UserBankcardService {

    /**
     * 分页查询借款人银行卡信息（条件查询）
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<UserBankcard> selectByUserBankcard(Map map,Integer pageNum, Integer pageSize);

    /**
     * 添加借款人银行卡信息
     * @param userBankcard
     * @return
     */
    public int insertUserBankcardDao(UserBankcard userBankcard);
    /**
     * 根据id查看详情
     * @param id
     * @return
     */
    public UserBankcard selectUserBankcardById(Long id);
}
