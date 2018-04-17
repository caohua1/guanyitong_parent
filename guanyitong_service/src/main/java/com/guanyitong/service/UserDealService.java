package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.UserDealMoneyVo;

import java.util.Map;

public interface UserDealService {

    /**
     * 用户出借（添加）
     * @param userDealMoney
     * @return
     */
    public Boolean insertUserDealMoney(UserDealMoney userDealMoney,Integer SYMoney);
    /**
     * (分页)查询所有用户的出借情况
     * @param map
     * @return
     */
    public PageInfo<UserDealMoneyVo> selectAllUserDeal(Map map,Integer pageNum,Integer pageSize);

    /**
     * (分页)查询所有出借用户的数量
     * @param map
     * @return
     */
    public Integer selectAllUserDealCount(Map map);

    /**
     * 统计某标的出借人数
     * @param productInfoId
     * @return
     */
    public Integer selectCountByProductInfoId(Long productInfoId);

}
