package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserBankcard;

import java.util.List;
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
     * @param borrowMoneyUserId
     * @return
     */
    public UserBankcard selectUserBankcardById(Long borrowMoneyUserId);



    /**
     * 模糊查询borrowMoneyUserId
     * @param dimId
     * @return
     */
    public List<UserBankcard> selectDimId(Long dimId);
}
