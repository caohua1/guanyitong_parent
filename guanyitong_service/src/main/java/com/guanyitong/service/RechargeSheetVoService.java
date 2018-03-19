package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.RechargeSheetVo;


import java.util.List;
import java.util.Map;

public interface RechargeSheetVoService {

    public PageInfo<RechargeSheetVo> listRechargeSheetVo(Integer pageNum, Integer pageSize, Map demandMap);


}
