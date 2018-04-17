package com.guanyitong.mapper;

import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.UserDealMoneyVo;
import com.guanyitong.model.vo.UserProductInfoVo;

import java.util.List;
import java.util.Map;

public interface UserDealDao {
    /**
     * 用户出借（添加）
     * @param userDealMoney
     * @return
     */
    public Integer insertUserDealMoney(UserDealMoney userDealMoney);

    /**
     * (分页)查询所有用户的出借情况
     * @param map
     * @return
     */
    public List<UserDealMoneyVo> selectAllUserDeal(Map map);

    /**
     * (分页)查询所有出借用户的数量
     * @param map
     * @return
     */
    public Integer selectAllUserDealCount(Map map);

    /**
     * 查询某放弃的标下的所有出借用户，退款
     * @param productInfoId
     * @return
     */
    public List<UserDealMoney> selectUserDealByProductInfoId(Long productInfoId);

    /**
     * 修改状态（0退款 1出借中 2已还款）
     * @param map
     * @return
     */
    public Integer updateUserDealMoneyStatus(Map map);

    /**
     * 统计某标的出借人数
     * @param productInfoId
     * @return
     */
    public Integer selectCountByProductInfoId(Long productInfoId);

    /**
     * =====================资金账户管理
     * 查询用户保护期金额（标的状态未筹集中，出借状态为 未还款 ，的金额
     * @return
     */
    public Double selectAllDealMoney(Long userId);

    /**
     * 查询用户下的待回款的标(statud = 13 or 11)
     * @param userId
     * @return
     */
    public List<UserProductInfoVo> selectDHKProductInfo(Long userId);
}
