package com.guanyitong.mapper;
import com.guanyitong.model.UserBankcard;

import java.util.List;
import java.util.Map;


/**
 * 银行卡管理DAO
 */
public interface UserBankcardDao {

    /**
     * 条件查询(查询所有用户银行卡信息)
     */
    public List<UserBankcard> selectByUserBankcard(Map conditionMap);

    /**
     * 分页查询借款人总数量
     * @param map
     * @return
     */
    public Integer selectUserBankcardAcount(Map map);


    /**
     * 用户添加银行卡信息
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
