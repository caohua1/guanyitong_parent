package com.guanyitong.service;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.LenderManagementVo;

import java.util.List;
import java.util.Map;

public interface LenderManagementVoService {
    public PageInfo<LenderManagementVo> listLenderManagementVo(Integer pageNum, Integer pageSize);

    public LenderManagementVo selectLenderManagementVo(Map needsMap);

    public  LenderManagementVo selectByIDCard(Map IDCardMap);
}
