package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.RechargeMoneyDao;

import com.guanyitong.model.vo.RechargeSheetVo;
import com.guanyitong.service.RechargeSheetVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class RechargeSheetVoServiceImpl implements RechargeSheetVoService {

    @Autowired
    private RechargeMoneyDao rechargeMoneyDao;

    /**
     * 分页查询
     * 条件查询（）
     * @param demandMap
     * @return
     */
    @Override
    public PageInfo<RechargeSheetVo> listRechargeSheetVo(Integer pageNum, Integer pageSize, Map demandMap) {
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<RechargeSheetVo> listRechargeSheetVos = rechargeMoneyDao.listRechargeSheetVo(demandMap);
        PageInfo<RechargeSheetVo> rechargeSheetVoPageInfo = new PageInfo<RechargeSheetVo>(listRechargeSheetVos);
        return rechargeSheetVoPageInfo;
    }


}
