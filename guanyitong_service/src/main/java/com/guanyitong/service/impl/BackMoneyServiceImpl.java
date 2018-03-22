package com.guanyitong.service.impl;
import com.guanyitong.mapper.BackMoneyDao;
import com.guanyitong.model.BackMoney;
import com.guanyitong.service.BackMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BackMoneyServiceImpl implements BackMoneyService {
    @Autowired
    private BackMoneyDao backMoneyDao;
    /**
     * 批量插入数据（还款计划）
     * @param list
     * @return
     */
    @Transactional
    @Override
    public Integer insertBatchBackMoney(List<BackMoney> list) {
        return backMoneyDao.insertBatchBackMoney(list);
    }
}
