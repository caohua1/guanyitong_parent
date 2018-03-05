package com.guanyitong.mapper;

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
}
