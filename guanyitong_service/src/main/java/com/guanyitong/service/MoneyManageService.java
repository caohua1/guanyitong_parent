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
}
