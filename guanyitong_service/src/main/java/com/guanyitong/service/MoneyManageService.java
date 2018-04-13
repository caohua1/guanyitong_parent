package com.guanyitong.service;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.MoneyManageVo;
import java.util.Map;

public interface MoneyManageService {

    /**
     * 资金账户管理
     * @return
     */
    public PageInfo<MoneyManageVo> moneyManage(Map map, Integer pageNum, Integer pageSize);

    /**
     * 查询所有绑定银行卡的用户总数量
     * @param map
     * @return
     */
    public Integer selectAllAcountManagerCount(Map map);
}
