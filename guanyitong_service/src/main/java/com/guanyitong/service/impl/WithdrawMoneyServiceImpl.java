package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.AccountManagerDao;
import com.guanyitong.mapper.ProductDao;
import com.guanyitong.mapper.WithdrawMoneyDao;
import com.guanyitong.model.BackMoney;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.model.vo.WithdrawalMoneyVo;
import com.guanyitong.service.BackMoneyService;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.DateChangeUtil;

import java.math.BigDecimal;
import java.util.*;

@Service
public class WithdrawMoneyServiceImpl implements WithdrawMoneyService{

    @Autowired
    private WithdrawMoneyDao withdrawMoneyDao;
    @Autowired
    private AccountManagerDao accountManagerDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private BackMoneyService backMoneyService;

    /**
     * 提现（修改体现表的状态，同时修改标（productinfo）的状态），添加数据
     * @param withdrawalMoney
     * @return
     */
    @Transactional
    @Override
    public Boolean insertWithdrawMoney(WithdrawalMoney withdrawalMoney) {
        if(withdrawalMoney.getBorrowMoneyUserId()!=null && !("").equals(withdrawalMoney.getBorrowMoneyUserId())){
            Integer i = withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);//借款人申请提现
            Map map = new HashMap();
            map.put("borrowMoneyUserId",withdrawalMoney.getBorrowMoneyUserId());
            map.put("status1",5);//筹集完待提现
            map.put("status",10);//提现中
            Integer k = productDao.updateStatus(map);//把标的状态改为提现中
            return i>0 && k>0;
        }
        if(withdrawalMoney.getUserId()!=null){
            Integer i = withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);//出借人申请提现，余额减少
            Map map = new HashMap();
            map.put("txMoney",0-Integer.parseInt(withdrawalMoney.getTxMoney()));
            map.put("userId",withdrawalMoney.getUserId());
            Integer j = accountManagerDao.updateYuE(map);
            return i>0 && j>0;
        }
        return null;
    }

    /**
     * 确认提现（修改体现表的状态，同时修改标（productinfo）的状态）成功、失败（修改状态）,添加审核时间
     * @param map
     * @return
     */
    @Transactional
    @Override
    public Boolean updateStatus(Map map) {
        Integer i = withdrawMoneyDao.updateStatus(map);
        Integer j = 0;
        Integer n = 0;
        if(map.get("borrowMoneyUserId")!=null && !("").equals(map.get("borrowMoneyUserId")) ){
            Map map1 = new HashMap();
            map1.put("borrowMoneyUserId",map.get("borrowMoneyUserId"));
            map1.put("status1",10);
            if((Integer)map.get("status")==1){//提现成功
                map1.put("status",11);
            }
            if((Integer)map.get("status")==2){//提现失败
                map1.put("status",12);
            }
            j = productDao.updateStatus(map1);//修改标（productinfo的状态）
            //提现成功后，批量插入回款计划表数据（根据borrowMoneyUserId和status==11查询表标的id）
            if((Integer)map.get("status")==1 && j>0){//提现成功
               Map map2 = new HashMap();
               map2.put("borrowMoneyUserId",map.get("borrowMoneyUserId"));
               map2.put("status",11);
                ProductInfo productInfo = productDao.selectProductInfoByStAndBUId(map2);
                if(productInfo!=null ){
                    List<BackMoney> backMoneyList = new ArrayList<BackMoney>();
                    int count = 1;
                    int monthNum = productInfo.getMonthNum();//期限（几个月）
                    double bj= productInfo.getZMoney()/monthNum;//本金
                    double lx =bj*productInfo.getYield()/100/12;
                    BigDecimal b = new BigDecimal(lx);
                    double lx1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位有效数字
                    Date date = new Date();
                    for(int m=0;m<monthNum;m++){
                        BackMoney backMoney = new BackMoney();
                        if(m==0){
                            backMoney.setBackTime(date);
                        }else{
                            date = DateChangeUtil.dateAddMonths(date,1);
                            backMoney.setBackTime(date);
                        }
                        backMoney.setBorrowMoneyUserId(String.valueOf(map.get("borrowMoneyUserId")));
                        backMoney.setProductInfoId(productInfo.getId());
                        backMoney.setLx(String.valueOf(lx1));
                        backMoney.setBj(String.valueOf(bj));
                        backMoney.setBackMoney(String.valueOf(bj+lx1));
                        backMoney.setCount(count);
                        count++;
                        backMoneyList.add(backMoney);
                    }
                     n = backMoneyService.insertBatchBackMoney(backMoneyList);
                }
                return i>0 && j>0 && n>0;
            }else{
                return i>0 && j>0;
            }
        }
      return false;
    }

    /**
     * 分页/条件查询提现数据
     * @param withdrawalMoneyVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<WithdrawalMoneyVo> selectWithdrawal(WithdrawalMoneyVo withdrawalMoneyVo, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<WithdrawalMoneyVo> withdrawalMoneyVos = withdrawMoneyDao.selectWithdrawal(withdrawalMoneyVo);
        PageInfo<WithdrawalMoneyVo> pageInfo = new PageInfo<WithdrawalMoneyVo>(withdrawalMoneyVos);
        return pageInfo;
    }
}
