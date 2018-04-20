package com.guanyitong.mapper;

import com.guanyitong.model.BackMoney;
import com.guanyitong.model.vo.BackMoneyManageListVo;
import com.guanyitong.model.vo.BackMoneyVo;

import java.util.List;
import java.util.Map;

public interface BackMoneyDao {
    /**
     * 批量插入（还款计划）
     * @param list
     * @return
     */
    public Integer insertBatchBackMoney(List<BackMoney> list);

    /**
     * 根据borrowMoneyUserId和productInfoId查询用户的还款计划
     * @param map
     * @return
     */
    public List<BackMoney> selectBackMoney(Map map);

    /**
     * 借款人还款
     * @param backMoney
     * @return
     */
    public Integer updateStatus(BackMoney backMoney);

    /**
     * 财务管理模块（还款管理列表，分页）
     * @return
     */
    public List<BackMoneyManageListVo> backMoneyList(Map map);

    /**
     * 财务管理模块（还款管理列表，分页）总数量
     * @param map
     * @return
     */
    public Integer backMoneyListCount(Map map);
}
