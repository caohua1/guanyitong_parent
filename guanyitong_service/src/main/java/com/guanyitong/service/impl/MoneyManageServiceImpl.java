package com.guanyitong.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.*;
import com.guanyitong.model.BackMoney;
import com.guanyitong.model.vo.MoneyManageVo;
import com.guanyitong.model.vo.UserProductInfoVo;
import com.guanyitong.service.MoneyManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.FinalData;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyManageServiceImpl implements MoneyManageService {

    @Autowired
    private AccountManagerDao accountManagerDao;
    @Autowired
    private UserDealDao userDealDao;
    @Autowired
    private WithdrawMoneyDao withdrawMoneyDao;
    @Autowired
    private UserBackMoneyRecordDAO userBackMoneyRecordDAO;
    @Autowired
    private BackMoneyDao backMoneyDao;

    /**
     * 资金账户管理
     * @return
     */
    @Transactional
    @Override
    public PageInfo<MoneyManageVo> moneyManage(Map map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        double dhmoney = 0;
        //1.查询所有绑定银行卡的用户
        List<MoneyManageVo> moneyManageVos = accountManagerDao.selectAllAcountManager(map);
        if(moneyManageVos !=null && moneyManageVos.size()>0){
            for(MoneyManageVo moneyManageVo : moneyManageVos){//循环遍历每个用户
                //2.查询此用户保护期金额（筹集中金额udm.status=2 productinfo.status =4 or 5）
                Double BHMoney = userDealDao.selectAllDealMoney(moneyManageVo.getUserId());
                if(BHMoney ==null){
                    BHMoney = 0.0;
                }
                moneyManageVo.setBHMoney(BHMoney);
                //3.查询此用户提取中的金额（提现withdrawal_money.status = 0）
                Double TQZMoney = withdrawMoneyDao.selectUserZTxMoney(moneyManageVo.getUserId());
                if(TQZMoney ==null){
                    TQZMoney = 0.0;
                }
                moneyManageVo.setTQZMoney(TQZMoney);
                //4.查询此用户待回款的金额(productInfo.status = (提现成功，代还款)11 or 13（还款中）)（还款方式不同，计算方式不同）
                List<UserProductInfoVo> userProductInfoVos = userDealDao.selectDHKProductInfo(moneyManageVo.getUserId());//查询用户下的待回款的标(statud = 13 or 11)
                if(userProductInfoVos!=null && userProductInfoVos.size()>0){
                    double DHKMoney = 0;
                    for(UserProductInfoVo userProductInfoVo : userProductInfoVos){
                        //根据productInfoId来查询还款计划，计算未还款金额（status=0）
                        Map map1 = new HashMap();
                        map1.put("productInfoId",userProductInfoVo.getId());
                        map1.put("status",0);
                        List<BackMoney> backMoneyList = backMoneyDao.selectBackMoney(map1);
                        //计算待还款金额
                        if((FinalData.ByMonth).equals(userProductInfoVo.getBackMoneyType())){//如果是按月还本还息
                            DHKMoney += ByMonth(userProductInfoVo, DHKMoney, backMoneyList);
                            moneyManageVo.setDHKMoney(DHKMoney);
                        }
                        if((FinalData.BlxAfterbj).equals(userProductInfoVo.getBackMoneyType())){//如果是先息后本
                            DHKMoney += BlxAfterbj(userProductInfoVo, DHKMoney, backMoneyList);
                            moneyManageVo.setDHKMoney(DHKMoney);
                        }
                        if((FinalData.AllBack).equals(userProductInfoVo.getBackMoneyType())){//如果是一次性还本还息
                            DHKMoney += AllBack(userProductInfoVo, DHKMoney);
                            moneyManageVo.setDHKMoney(DHKMoney);
                        }
                    }
                    dhmoney = DHKMoney;
                }

                //5.查询此用户的累计收益
                Double LJSY = userBackMoneyRecordDAO.selectUserBackMoney(moneyManageVo.getUserId());
                if(LJSY==null){
                    LJSY= 0.0;
                }
                moneyManageVo.setLJSY(LJSY);
                //6.计算账户总金额
                double ZMoney = moneyManageVo.getYuE() + BHMoney + TQZMoney + dhmoney +LJSY;
                moneyManageVo.setZMoney(doubleTwo(ZMoney));
            }
        }
        //把集合添加到分页
        PageInfo<MoneyManageVo> pageInfo = new PageInfo<MoneyManageVo>(moneyManageVos);
        return pageInfo;
    }


    //==========================================
    //如果是按月还本还息
    private double ByMonth(UserProductInfoVo userProductInfoVo,double DHKMoney,List<BackMoney> backMoneyList){
        //求剩余的钱
        double dealMoney = userProductInfoVo.getDealMoney();
        int monthNum = userProductInfoVo.getMonthNum();
        double yield = userProductInfoVo.getYield();
        double SYMoney = dealMoney-(dealMoney/monthNum*(backMoneyList.size()));//总的剩余的本金
        double Zlx = 0;//总的利息
        for(int i=0;i<monthNum-backMoneyList.size();i++){
            Zlx += (SYMoney-(dealMoney/monthNum*i))*yield/100/12;
        }
        DHKMoney = SYMoney + Zlx;
        return doubleTwo(DHKMoney);
    }

    //如果是先息后本
    private double BlxAfterbj(UserProductInfoVo userProductInfoVo,double DHKMoney,List<BackMoney> backMoneyList){
        double dealMoney = userProductInfoVo.getDealMoney();
        int monthNum = userProductInfoVo.getMonthNum();
        double yield = userProductInfoVo.getYield();
        double Zlx = dealMoney * yield/100/12 *(monthNum-backMoneyList.size());//总的剩余的利息
        DHKMoney = dealMoney + Zlx;
        return doubleTwo(DHKMoney);
    }

    //如果是一次性还本付息
    private double AllBack(UserProductInfoVo userProductInfoVo,double DHKMoney){
        double dealMoney = userProductInfoVo.getDealMoney();
        int monthNum = userProductInfoVo.getMonthNum();
        double yield = userProductInfoVo.getYield();
        DHKMoney = dealMoney + dealMoney * yield/100/12 * monthNum;
        return doubleTwo(DHKMoney);
    }
    //保留两位有效数字
    private double doubleTwo(double a){
        BigDecimal b = new BigDecimal(a);
        double c = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位有效数字
        return c;
    }
}
