package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.LenderManagementVo;

import java.util.List;
import java.util.Map;

public interface LenderManagementVoService {


    public PageInfo<LenderManagementVo> selectLenderManagementVo(Integer pageNum,Integer pageSize,Map lenderMap);

    public  LenderManagementVo selectByID(Map IDMap);
}
