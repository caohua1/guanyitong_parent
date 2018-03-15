package com.guanyitong.mapper;

import com.guanyitong.model.UserBankcard;

import java.util.List;
import java.util.Map;


/**
 * 银行卡管理DAO
 */
public interface UserBankcardDao {
    /**
     * 查询所有用户银行卡信息
     * @return  所有用户银行卡信息
     */
    public List<UserBankcard> selectUserBankcardDao();

    /**
     * 模糊查询
     */
    public UserBankcard selectByUserBankcard(Map conditionMap);

    /**
     * 按条件查询（根据userName）
     */
    public UserBankcard seelctByUserName(Map userNameMap);

    /**
     * 用户添加银行卡信息
     */
    public int insertUserBankcardDao(UserBankcard userBankcard);


}
