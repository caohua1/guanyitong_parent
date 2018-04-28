package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.vo.RechargeSheetVo;


import java.util.List;
import java.util.Map;

public interface RechargeSheetVoService {

    public PageInfo<RechargeSheetVo> listRechargeSheetVo(Map demandMap,Integer pageNum, Integer pageSize);

    public boolean insertRechargeMoney(RechargeMoney rechargeMoney);

    public Integer updateRechargeMoney(Long id);

    public List<RechargeSheetVo> selectByrid(Long rid);

    public Integer RechargeSheetCount(Map demandMap);

}
