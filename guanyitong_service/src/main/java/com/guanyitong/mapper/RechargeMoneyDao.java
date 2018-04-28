package com.guanyitong.mapper;

import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.vo.RechargeSheetVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 充值记录Dao层
 */
public interface RechargeMoneyDao {

    /**
     * 条件查询（）
     * @return
     */
    public List<RechargeSheetVo>  listRechargeSheetVo(Map demandMap);

    /**
     * 添加
     */
    public Long insertRechargeMoney(RechargeMoney rechargeMoney);

    /**
     * 修改
     */
    public Integer updateRechargeMoney(Long id);

    /**
     * 账户明细查询（账户充值记录)
     * @return
     */
    public List<RechargeMoney> selectRechargeMoneyList(Map rechargeRecordMap);

    public Integer RechargeSheetCount(Map rechargeRecordMap);


}
