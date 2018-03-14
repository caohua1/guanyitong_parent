package com.guanyitong.mapper;

import com.guanyitong.model.vo.LenderManagementVo;

import java.util.List;
import java.util.Map;

/**
 *出借人银行卡管理
 */
public interface LenderManagementVoDao  {

    /**
     * 查询出借人银行
     * @return
     */
    public List<LenderManagementVo> listLenderManagementVo();

    /**
     * 按条件查找出借人银行卡
     * @return
     */
    public LenderManagementVo selectLenderManagementVo(Map needsMap);
}
