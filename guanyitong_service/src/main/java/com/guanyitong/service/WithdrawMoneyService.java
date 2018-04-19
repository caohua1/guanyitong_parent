package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.model.vo.WithdrawalMoneyVo;
import java.util.Map;

public interface WithdrawMoneyService {
    /**
     * 提现，添加数据
     * @param withdrawalMoney
     * @return
     */
    public Boolean insertWithdrawMoney(WithdrawalMoney withdrawalMoney);

    /**
     * 确认提现成功、失败（修改状态）,添加审核时间
     * @param map
     * @return
     */
    public Boolean updateStatus(Map map);
    /**
     * （分页，条件查询）查询所有提现数据
     * @param withdrawalMoneyVo
     * @return
     */
    public PageInfo<WithdrawalMoneyVo> selectWithdrawal(WithdrawalMoneyVo withdrawalMoneyVo,Integer pageNum,Integer pageSize);

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
