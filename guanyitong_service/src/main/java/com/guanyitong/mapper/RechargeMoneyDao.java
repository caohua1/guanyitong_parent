package com.guanyitong.mapper;

import com.guanyitong.model.vo.RechargeSheetVo;

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


}
