package com.guanyitong.mapper;

import com.guanyitong.model.vo.LenderManagementVo;

import java.util.List;
import java.util.Map;

/**
 *出借人银行卡管理
 */
public interface LenderManagementVoDao  {
    /**
     * 条件查找出借人银行卡
     * @return
     */
    public List<LenderManagementVo> selectLenderManagementVo(Map lenderMap);

    /**
     * 条件查找（根据用户身份证查询）
     */
    public  LenderManagementVo selectByID(Long id);

    /**
     * 分页查询出借人银行卡总数量
     * @param needsMap
     * @return
     */
    public Integer selectAllLenderAcount(Map needsMap);
}
