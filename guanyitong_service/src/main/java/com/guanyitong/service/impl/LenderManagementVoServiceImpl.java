package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.LenderManagementVoDao;
import com.guanyitong.model.vo.LenderManagementVo;
import com.guanyitong.service.LenderManagementVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LenderManagementVoServiceImpl implements LenderManagementVoService {

    @Autowired
    private LenderManagementVoDao lenderManagementVoDao;

    /**
     * 分页查询出借人银行卡信息（业务层）
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<LenderManagementVo> listLenderManagementVo(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<LenderManagementVo> lenderManagementVos = lenderManagementVoDao.listLenderManagementVo();
        PageInfo<LenderManagementVo> lenderManagementVoPageInfo = new PageInfo<LenderManagementVo>(lenderManagementVos);
        return lenderManagementVoPageInfo;
    }

    /**
     * 模糊查询（条件查询）出借人银行卡信息
     * @param needsMap
     * @return
     */
    @Override
    public LenderManagementVo selectLenderManagementVo(Map needsMap) {
        return lenderManagementVoDao.selectLenderManagementVo(needsMap);
    }
}
