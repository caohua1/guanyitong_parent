package com.guanyitong.mapper;

import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.UserDealMoneyVo;

import java.util.List;
import java.util.Map;

public interface UserDealDao {

    /**
     * (分页)查询所有用户的出借情况
     * @param map
     * @return
     */
    public List<UserDealMoneyVo> selectAllUserDeal(Map map);

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
}
