package com.guanyitong.mapper;

import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.model.vo.WithdrawalMoneyVo;

import java.util.List;
import java.util.Map;

public interface WithdrawMoneyDao {
    /**
     * 提现，添加数据
     * @param withdrawalMoney
     * @return
     */
    public Integer insertWithdrawMoney(WithdrawalMoney withdrawalMoney);

    /**
     * 确认提现成功、失败（修改状态）,添加审核时间
     * @param map
     * @return
     */
    public Integer updateStatus(Map map);

    /**
     * （分页，条件查询）查询所有提现数据
     * @param withdrawalMoneyVo
     * @return
     */
    public List<WithdrawalMoneyVo> selectWithdrawal(WithdrawalMoneyVo withdrawalMoneyVo);

    /**
     * 资金账户管理
     * 查询某用户提现中的金额
     * @param userId
     * @return
     */
    public Double selectUserZTxMoney(Long userId);

    /**
     * 提现总数量
     * @param withdrawalMoneyVo
     * @return
     */
    public Integer selectWithdrawalCount(WithdrawalMoneyVo withdrawalMoneyVo);

    /**
     * 查询出提现总金额
     * @return
     */
    public Integer totalJe();
}
