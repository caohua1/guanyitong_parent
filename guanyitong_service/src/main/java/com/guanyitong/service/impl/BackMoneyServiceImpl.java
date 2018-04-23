package com.guanyitong.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guanyitong.mapper.*;
import com.guanyitong.model.BackMoney;
import com.guanyitong.model.ProductInfo;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.BackMoneyManageListVo;
import com.guanyitong.model.vo.BackMoneyVo;
import com.guanyitong.service.BackMoneyService;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.FinalData;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BackMoneyServiceImpl implements BackMoneyService {
    @Autowired
    private BackMoneyDao backMoneyDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private UserDealDao userDealDao;
    @Autowired
    private AccountManagerDao accountManagerDao;
    @Autowired
    private UserBackMoneyRecordDAO userBackMoneyRecordDAO;
    @Autowired
    private BorrowMoneyUserDao borrowMoneyUserDao;
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

    /**
     * 根据borrowMoneyUserId和productInfoId查询用户的还款计划
     * @param map
     * @return
     */
    @Override
    public BackMoneyVo selectBackMoney(Map map) {
        List<BackMoney> backMoneyList = backMoneyDao.selectBackMoney(map);
        BackMoneyVo backMoneyVo = new BackMoneyVo();
        if(backMoneyList!=null && backMoneyList.size()>0){
            double allBj = 0;//所有的本金
            double allLx = 0;//所有的利息
            for(BackMoney backMoney : backMoneyList){
              allBj += Double.valueOf(backMoney.getBj());
              allLx += Double.valueOf(backMoney.getLx());
            }
            backMoneyVo.setNO((String) map.get("NO"));
            backMoneyVo.setAllBj(String.valueOf(allBj));
            backMoneyVo.setAllLx(String.valueOf(allLx));
            backMoneyVo.setAllBjAndLx(String.valueOf(allBj+allLx));
            backMoneyVo.setBackMoneyList(backMoneyList);
        }
        return backMoneyVo;
    }

    /**
     * 还款人还款（还款方式不同，出借人收益不同）
     * 1 修改状态为已还款
     * 2.用户的余额增加
     * 3.添加出借用户的回款记录
     * @param backMoney
     * @return
     */
    @Transactional
    @Override
    public Boolean updateStatus(BackMoney backMoney) {
        if(backMoney.getStatus()==2){
            Integer o = backMoneyDao.updateStatus(backMoney);//2 修改状态为还款失败
            return o>0;
        }
        Integer i = backMoneyDao.updateStatus(backMoney);//1 修改状态为已还款
        Map map = new HashMap();
        map.put("borrowMoneyUserId",backMoney.getBorrowMoneyUserId());
        map.put("productInfoId",backMoney.getProductInfoId());
        List<ProductInfo> productInfos = productDao.selectProductInfo(map);//查询还款方式
        List<UserDealMoney> userDealMonies = userDealDao.selectUserDealByProductInfoId(backMoney.getProductInfoId());//查询某标下的所有用户投标信息
        if(productInfos!=null&&productInfos.size()>0){
            String backMoneyType = productInfos.get(0).getBackMoneyType();
            if((FinalData.ByMonth).equals(backMoneyType)){//按月还本还息，给用户反钱（反一个月的本息）
                Boolean b = ByMonth(userDealMonies, productInfos, backMoney);
                return b==true && i>0;
            }else if((FinalData.BlxAfterbj).equals(backMoneyType)){//先息后本
                Boolean b = BelxAfterBj(userDealMonies, productInfos, backMoney);
                return b==true && i>0;
            }else if((FinalData.AllBack).equals(backMoneyType)){//一次性还本付息
                Boolean b = AllBack(userDealMonies, productInfos, backMoney);
                return b==true && i>0;
            }
        }
        return false;
    }

    /**
     * 财务管理模块（还款管理列表，分页）
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<BackMoneyManageListVo> backMoneyList(Map map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<BackMoneyManageListVo> backMoneyManageListVos = backMoneyDao.backMoneyList(map);
        PageInfo<BackMoneyManageListVo> pageInfo = new PageInfo<BackMoneyManageListVo>(backMoneyManageListVos);
        return pageInfo;
    }

    /**
     * 财务管理模块（还款管理列表，分页）总数量
     * @param map
     * @return
     */
    @Override
    public Integer backMoneyListCount(Map map) {
        return backMoneyDao.backMoneyListCount(map);
    }


    //===========================如果是按月还本还息，用户的回款记录
    private Boolean ByMonth(List<UserDealMoney> userDealMonies,List<ProductInfo> productInfos,BackMoney backMoney){
        Integer j = 0;
        Integer k = 0;
        Integer m = 0;
        Integer n = 0;
        Integer p = 0;
        Integer count =  backMoney.getCount();
        if(userDealMonies!=null&&userDealMonies.size()>0){
            for(UserDealMoney userDealMoney : userDealMonies){
                double bj = userDealMoney.getDealMoney();//用户投标的本金
                double  yield = productInfos.get(0).getYield();
                double lx =(bj-bj/productInfos.get(0).getMonthNum()*(count-1)) * yield/100/12;//每个月的利息不一样
                double lx1 = doubleTwo(lx);
                double Zmoney = bj/productInfos.get(0).getMonthNum() + lx1;//用户返回的钱，添加到余额中
                double Zmoney1 = doubleTwo(Zmoney);
                Map map = new HashMap();
                map.put("txMoney",Zmoney1);
                map.put("userId",userDealMoney.getUserId());
                j += accountManagerDao.updateYuE(map);//2.用户的余额增加
                //=============3.添加出借用户的回款记录
                UserDealBackMoneyRecord userDealBackMoneyRecord = new UserDealBackMoneyRecord();
                userDealBackMoneyRecord.setUserId(userDealMoney.getUserId());
                userDealBackMoneyRecord.setProductInfoId(backMoney.getProductInfoId());
                userDealBackMoneyRecord.setBj(String.valueOf(doubleTwo(bj/(productInfos.get(0).getMonthNum()))));
                userDealBackMoneyRecord.setLx(String.valueOf(lx1));
                userDealBackMoneyRecord.setTime(new Date());
                k += userBackMoneyRecordDAO.insertUserBackMoneyRecord(userDealBackMoneyRecord);
            }
            //============4用户出借表状态改为还款中status=3或者还款完成status=4
            //============修改标（productinfo）的状态 第一次还款status=13 最后一次还款status=14
            Map map1= new HashMap();
            Map map2 = new HashMap();
            Map map3 = new HashMap();
            if(count == 1){//如果是第一个月还款就是改为还款中
                map1.put("status",3);
                map2.put("status",13);
                map2.put("updateTime",new Date());
                map1.put("productInfoId",backMoney.getProductInfoId());
                map2.put("id",backMoney.getProductInfoId());
                map3.put("id",backMoney.getBorrowMoneyUserId());
                map3.put("status",8);//把borrowmoney_user表的状态改为还款中
                m = userDealDao.updateUserDealMoneyStatus(map1);
                n = productDao.updateStatus(map2);
                p = borrowMoneyUserDao.updateStatus(map3);
                return j==userDealMonies.size() && k==userDealMonies.size()  && m>0 && n>0 && p>0 ;
            }
            if(count == productInfos.get(0).getMonthNum()){//如果是最后一次，就是还款完成
                map1.put("status",4);
                map2.put("status",14);
                map2.put("updateTime",new Date());
                map1.put("productInfoId",backMoney.getProductInfoId());
                map2.put("id",backMoney.getProductInfoId());
                map3.put("id",backMoney.getBorrowMoneyUserId());
                map3.put("status",9);//把borrowmoney_user表的状态改为已还款完成
                m = userDealDao.updateUserDealMoneyStatus(map1);
                n = productDao.updateStatus(map2);
                p = borrowMoneyUserDao.updateStatus(map3);
                return j==userDealMonies.size() && k==userDealMonies.size()  && m>0 && n>0 && p>0;
            }

        }
        return j==userDealMonies.size() && k==userDealMonies.size();
    }



    //==========================先息后本，用户的回款记录
    private Boolean BelxAfterBj(List<UserDealMoney> userDealMonies,List<ProductInfo> productInfos,BackMoney backMoney){
        Integer j = 0;
        Integer k = 0;
        Integer m = 0;
        Integer n = 0;
        Integer p = 0;
        Integer count =  backMoney.getCount();
        if(userDealMonies!=null&&userDealMonies.size()>0){
            for(UserDealMoney userDealMoney : userDealMonies){
                double bj = userDealMoney.getDealMoney();//用户投标的本金
                double  yield = productInfos.get(0).getYield();
                double lx =(bj*(count-1)) * yield/100/12;//每个月的利息一样
                double lx1 = doubleTwo(lx);
                double Zmoney = 0;
                if(count == productInfos.get(0).getMonthNum() ){
                    Zmoney = bj + lx1;//用户返回的钱，添加到余额中
                }else{
                    Zmoney = lx1;//如果不是最后一次还款就只有利息
                }
                double Zmoney1 = doubleTwo(Zmoney);
                Map map = new HashMap();
                map.put("txMoney",Zmoney1);
                map.put("userId",userDealMoney.getUserId());
                j += accountManagerDao.updateYuE(map);//2.用户的余额增加
                //=============3.添加出借用户的回款记录
                UserDealBackMoneyRecord userDealBackMoneyRecord = new UserDealBackMoneyRecord();
                userDealBackMoneyRecord.setUserId(userDealMoney.getUserId());
                userDealBackMoneyRecord.setProductInfoId(backMoney.getProductInfoId());
                if(count == productInfos.get(0).getMonthNum() ){
                    userDealBackMoneyRecord.setBj(String.valueOf(bj));
                }else{
                    userDealBackMoneyRecord.setBj(String.valueOf(0));//如果 不是最后一次还款bj就是0因为先息后本
                }
                userDealBackMoneyRecord.setLx(String.valueOf(lx1));
                userDealBackMoneyRecord.setTime(new Date());
                k += userBackMoneyRecordDAO.insertUserBackMoneyRecord(userDealBackMoneyRecord);
            }
            //============4用户出借表状态改为还款中status=3或者还款完成status=4
            //============修改标（productinfo）的状态 第一次还款status=13 最后一次还款status=14
            Map map1= new HashMap();
            Map map2 = new HashMap();
            Map map3 = new HashMap();
            if(count == 1){//如果是第一个月还款就是改为还款中
                map1.put("status",3);
                map2.put("status",13);
                map2.put("updateTime",new Date());
                map1.put("productInfoId",backMoney.getProductInfoId());
                map2.put("id",backMoney.getProductInfoId());
                map3.put("id",backMoney.getBorrowMoneyUserId());
                map3.put("status",8);//把borrowmoney_user表的状态改为还款中
                p = borrowMoneyUserDao.updateStatus(map3);
                m = userDealDao.updateUserDealMoneyStatus(map1);
                n = productDao.updateStatus(map2);
                return j==userDealMonies.size() && k==userDealMonies.size()  && m>0 && n>0 && p>0;
            }
            if(count == productInfos.get(0).getMonthNum()){//如果是最后一次，就是还款完成
                map1.put("status",4);
                map2.put("status",14);
                map2.put("updateTime",new Date());
                map1.put("productInfoId",backMoney.getProductInfoId());
                map2.put("id",backMoney.getProductInfoId());
                map3.put("id",backMoney.getBorrowMoneyUserId());
                map3.put("status",9);//把borrowmoney_user表的状态改为还款完成
                p = borrowMoneyUserDao.updateStatus(map3);
                m = userDealDao.updateUserDealMoneyStatus(map1);
                n = productDao.updateStatus(map2);
                return j==userDealMonies.size() && k==userDealMonies.size()  && m>0 && n>0 && p>0;
            }


        }
        return j==userDealMonies.size() && k==userDealMonies.size()  ;
    }



    //==========================一次性还本付息
     private Boolean AllBack(List<UserDealMoney> userDealMonies,List<ProductInfo> productInfos,BackMoney backMoney){
         Integer j = 0;
         Integer k = 0;
         Integer m = 0;
         Integer n = 0;
         Integer p = 0;
         if(userDealMonies!=null&&userDealMonies.size()>0){
             for(UserDealMoney userDealMoney : userDealMonies){
                 double bj = userDealMoney.getDealMoney();//用户投标的本金
                 double  yield = productInfos.get(0).getYield();
                 double lx =(bj*(productInfos.get(0).getMonthNum() )) * yield/100/12;//所有的利息
                 double lx1 = doubleTwo(lx);
                 double Zmoney = bj + lx1;
                 double Zmoney1 = doubleTwo(Zmoney);
                 Map map = new HashMap();
                 map.put("txMoney",Zmoney1);
                 map.put("userId",userDealMoney.getUserId());
                 j += accountManagerDao.updateYuE(map);//2.用户的余额增加
                 //=============3.添加出借用户的回款记录
                 UserDealBackMoneyRecord userDealBackMoneyRecord = new UserDealBackMoneyRecord();
                 userDealBackMoneyRecord.setUserId(userDealMoney.getUserId());
                 userDealBackMoneyRecord.setProductInfoId(backMoney.getProductInfoId());
                 userDealBackMoneyRecord.setBj(String.valueOf(bj));
                 userDealBackMoneyRecord.setLx(String.valueOf(lx1));
                 userDealBackMoneyRecord.setTime(new Date());
                 k += userBackMoneyRecordDAO.insertUserBackMoneyRecord(userDealBackMoneyRecord);
             }
             Map map1= new HashMap();
             Map map2 = new HashMap();
             Map map3 = new HashMap();
             map1.put("status",4);//一次性还款完成
             map2.put("status",14);
             map1.put("productInfoId",backMoney.getProductInfoId());
             map2.put("id",backMoney.getProductInfoId());
             map2.put("updateTime",new Date());
             map3.put("id",backMoney.getBorrowMoneyUserId());
             map3.put("status",9);//把borrowmoney_user表的状态改为还款完成
             p = borrowMoneyUserDao.updateStatus(map3);
             m = userDealDao.updateUserDealMoneyStatus(map1);
             n = productDao.updateStatus(map2);
         }
        return j==userDealMonies.size() && k==userDealMonies.size()  && m>0 && n>0 && p>0;
     }

    //保留两位有效数字
    private double doubleTwo(double a){
        BigDecimal b = new BigDecimal(a);
        double c = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留两位有效数字
        return c;
    }
}
