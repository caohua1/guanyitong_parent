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
     * 分页查询出借人银行卡信息（业务层,条件查询）
     * @param pageNum
     * @param pageSize
     * @return
     */

    @Override
    public PageInfo<LenderManagementVo> selectLenderManagementVo(Integer pageNum, Integer pageSize,Map lenderMap){
        PageHelper.startPage(pageNum,pageSize);//开始分页
        List<LenderManagementVo> lenderManagementVos = lenderManagementVoDao.selectLenderManagementVo(lenderMap);
        PageInfo<LenderManagementVo> lenderManagementVoPageInfo = new PageInfo<LenderManagementVo>(lenderManagementVos);
        return lenderManagementVoPageInfo;
    }
    /**
     * 条件查询出借人银行卡信息
     */
    @Override
    public LenderManagementVo selectByID(Long id) {
        return lenderManagementVoDao.selectByID(id);
    }
}
