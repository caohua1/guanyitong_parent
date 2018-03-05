package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.UserDealMoneyVo;

import java.util.Map;

public interface UserDealService {
    /**
     * (分页)查询所有用户的出借情况
     * @param map
     * @return
     */
    public PageInfo<UserDealMoneyVo> selectAllUserDeal(Map map,Integer pageNum,Integer pageSize);
}
