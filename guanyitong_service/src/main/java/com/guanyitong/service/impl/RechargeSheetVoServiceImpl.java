package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.AccountManagerDao;
import com.guanyitong.mapper.RechargeMoneyDao;

import com.guanyitong.model.RechargeMoney;
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

    @Autowired
    private AccountManagerDao accountManagerDao;

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

    /**
     * 添加充值记录并修改当前用户余额
     * @param rechargeMoney
     * @return
     */
    @Override
    public boolean insertRechargeMoney(RechargeMoney rechargeMoney) {
        Long id = rechargeMoneyDao.insertRechargeMoney(rechargeMoney);
        HashMap<Object, Object> balanceMap = new HashMap<Object, Object>();
        balanceMap.put("userId",rechargeMoney.getUserId());
        balanceMap.put("yuE",rechargeMoney.getRechargeMoney());
        Integer integer = accountManagerDao.updateBalance(balanceMap);
        return false;
    }

    /**
     * 修改充值记录状态
     * @param id
     * @return
     */
    @Override
    public Integer updateRechargeMoney(Long id) {
        return rechargeMoneyDao.updateRechargeMoney(id);
    }


    /**
     * 根据userId查询
     * @param
     * @return
     */
    @Override
    public List<RechargeSheetVo> selectByrid(Long rid) {
        Map<Object, Object> demandMap = new HashMap<Object, Object>();
        demandMap.put("rid",rid);
        return rechargeMoneyDao.listRechargeSheetVo(demandMap);
    }
}
