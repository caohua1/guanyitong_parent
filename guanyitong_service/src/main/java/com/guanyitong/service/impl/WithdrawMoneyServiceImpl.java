package com.guanyitong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.*;
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
import util.FinalData;

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
    private BackMoneyDao backMoneyDao;
    @Autowired
    private BorrowMoneyUserDao borrowMoneyUserDao;

    /**
     * 提现（修改体现表的状态，同时修改标（productinfo）的状态），添加数据
     * @param withdrawalMoney
     * @return
     */
    @Transactional
    @Override
    public Boolean insertWithdrawMoney(WithdrawalMoney withdrawalMoney) {

        if(withdrawalMoney.getBorrowMoneyUserId()!=null && !("").equals(withdrawalMoney.getBorrowMoneyUserId())){
            if(withdrawalMoney.getId()!=null){//说明提现失败，再次申请提现
                //删除原来的数据
                withdrawMoneyDao.deleteWithdrawalById(withdrawalMoney.getId());
                //把id设为null
                withdrawalMoney.setId(null);
            }
            Integer i = withdrawMoneyDao.insertWithdrawMoney(withdrawalMoney);//借款人申请提现
            Map map = new HashMap();
            map.put("borrowMoneyUserId",withdrawalMoney.getBorrowMoneyUserId());
            map.put("status1",5);//筹集完待提现
            map.put("status",10);//提现中
            map.put("updateTime",new Date());
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
        Integer p = 0;
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
            map1.put("updateTime",new Date());
            j = productDao.updateStatus(map1);//修改标（productinfo的状态）
            //提现成功后，批量插入回款计划表数据（根据borrowMoneyUserId和status==11查询表标的id）
            if((Integer)map.get("status")==1 && j>0){//提现成功
               Map map2 = new HashMap();
               map2.put("borrowMoneyUserId",map.get("borrowMoneyUserId"));
               map2.put("status",11);
                ProductInfo productInfo = productDao.selectProductInfoByStAndBUId(map2);
                if(productInfo!=null ){
                    List<BackMoney> backMoneyList = new ArrayList<BackMoney>();
                    if((FinalData.ByMonth).equals(productInfo.getBackMoneyType())){//1.还款方式：按月还本还息
                        byMonth(map,productInfo,backMoneyList);
                    }else if((FinalData.BlxAfterbj).equals(productInfo.getBackMoneyType())){//2.先息后本
                        BlxAfterbj(map,productInfo,backMoneyList);
                    }else if((FinalData.AllBack).equals(productInfo.getBackMoneyType())){
                        AllBack(map,productInfo,backMoneyList);
                    }
                     n = backMoneyDao.insertBatchBackMoney(backMoneyList);
                }
                //提现成功后，修改表 borrowmoney_user 表的状态为 待还款 状态
                Map map3 = new HashMap();
                map3.put("id",map.get("borrowMoneyUserId"));
                map3.put("status",7);
                p = borrowMoneyUserDao.updateStatus(map3);
                return i>0 && j>0 && n>0 && p>0;
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
    /**
     * 提现总数量
     * @param withdrawalMoneyVo
     * @return
     */
    @Override
    public Integer selectWithdrawalCount(WithdrawalMoneyVo withdrawalMoneyVo) {
        return withdrawMoneyDao.selectWithdrawalCount(withdrawalMoneyVo);
    }

    /**
     * 查询出提现总金额
     * @return
     */
    @Override
    public Double totalJe() {
        return withdrawMoneyDao.totalJe();
    }

    //==================================1 按月还本还息
    private List<BackMoney> byMonth(Map map,ProductInfo productInfo,List<BackMoney> backMoneyList){
        int count = 1;
        int monthNum = productInfo.getMonthNum();//期限（几个月）
        int ZMoney = productInfo.getZMoney();
        double bj=doubleTwo(ZMoney /monthNum) ;//本金
        Date date = new Date();
        for(int m=0;m<monthNum;m++){
            double lx =ZMoney*productInfo.getYield()/100/12;//每个月的利息不一样，ZMoney（逐渐递减）不一样，
            double lx1 = doubleTwo(lx);//保留两位有效数字
            BackMoney backMoney = new BackMoney();
            date = DateChangeUtil.dateAddMonths(date,1);
            backMoney.setBackTime(date);
            backMoney.setBorrowMoneyUserId(String.valueOf(map.get("borrowMoneyUserId")));
            backMoney.setProductInfoId(productInfo.getId());
            backMoney.setLx(String.valueOf(lx1));
            backMoney.setBj(String.valueOf(bj));
            backMoney.setBackMoney(String.valueOf(doubleTwo(bj+lx1)));
            backMoney.setCount(count);
            count++;
            ZMoney -= bj; //借款金额不断减少，利息也要减少
            backMoneyList.add(backMoney);
        }
        return backMoneyList;
    }


    //===================================2 先息后本
    private List<BackMoney> BlxAfterbj(Map map,ProductInfo productInfo,List<BackMoney> backMoneyList){
        int count = 1;
        int monthNum = productInfo.getMonthNum();//期限（几个月）
        int ZMoney = productInfo.getZMoney();
        double bj= ZMoney ;//本金
        Date date = new Date();
        for(int m=0;m<monthNum;m++){
            double lx =ZMoney*productInfo.getYield()/100/12;//每个月的利息一样
            double lx1 = doubleTwo(lx);//保留两位有效数字
            BackMoney backMoney = new BackMoney();
            date = DateChangeUtil.dateAddMonths(date,1);
            backMoney.setBackTime(date);
            backMoney.setBorrowMoneyUserId(String.valueOf(map.get("borrowMoneyUserId")));
            backMoney.setProductInfoId(productInfo.getId());
            backMoney.setLx(String.valueOf(lx1));
            if(m==(monthNum-1)){
                backMoney.setBj(String.valueOf(bj));
                backMoney.setBackMoney(String.valueOf(doubleTwo(bj+lx1)));
            }else{
                backMoney.setBj(String.valueOf(0));
                backMoney.setBackMoney(String.valueOf(doubleTwo(0+lx1)));
            }
            backMoney.setCount(count);
            count++;
            backMoneyList.add(backMoney);
        }
        return backMoneyList;
    }

    //===================================3 一次性还本付息
    private List<BackMoney> AllBack(Map map,ProductInfo productInfo,List<BackMoney> backMoneyList){
        int count = 1;
        int monthNum = productInfo.getMonthNum();//期限（几个月）
        int ZMoney = productInfo.getZMoney();
        double bj= ZMoney ;//本金
        Date date = new Date();
        double lx =monthNum*ZMoney*productInfo.getYield()/100/12;//总利息
        double lx1 = doubleTwo(lx);//保留两位有效数字
        BackMoney backMoney = new BackMoney();
        date = DateChangeUtil.dateAddMonths(date,1);
        backMoney.setBackTime(date);
        backMoney.setBorrowMoneyUserId(String.valueOf(map.get("borrowMoneyUserId")));
        backMoney.setProductInfoId(productInfo.getId());
        backMoney.setLx(String.valueOf(lx1));
        backMoney.setBj(String.valueOf(bj));
        backMoney.setBackMoney(String.valueOf(doubleTwo(bj+lx1)));
        backMoney.setCount(count);
        count++;
        backMoneyList.add(backMoney);
        return backMoneyList;
    }

    //保留两位有效数字
    private double doubleTwo(double a){
        BigDecimal b = new BigDecimal(a);
        double c = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位有效数字
        return c;
    }
}
