package com.guanyitong.controller.moneyManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.AccountDetails;
import com.guanyitong.model.RechargeMoney;
import com.guanyitong.model.UserDealBackMoneyRecord;
import com.guanyitong.model.UserDealMoney;
import com.guanyitong.model.vo.MoneyManageVo;
import com.guanyitong.service.AccountDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/AccountDetails")
//出借人账户明细
public class AccountDetailsController {
    @Autowired
    private AccountDetailsService accountDetailsService;


    /**
     * 账户明细页面跳转
     * @param model
     * @param moneyManageVo
     * @return
     */
    @RequestMapping("/skipDetails")
    public String skipDetails(Model model, MoneyManageVo moneyManageVo){
        model.addAttribute("moneyManage",moneyManageVo);
        return "capitalAccount/capitalAccount_select";
    }


    /**
     * 查询出借人账户明细（ajxa请求接口）
     * @param userId(出借人id)
     * @param //startTime（查询起始时间）
     * @param //endTime（查询结束时间）
     * @param //type1（收支类型 0代表收入，1代表支出）
     * @param //type2（类型 2代表充值类型，3 代表回款类型，4 代表出借类型）
     * @return
     */
    @RequestMapping("/checkDetails")
    @ResponseBody
    public JsonResult checkDetails(Long userId,String startTime,String endTime,Integer type1,Integer type2,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        Map map = new HashMap();
        Map map1 = new HashMap();
        try{
            //判断是否是条件查询
               if(userId!=null){
                   map.put("userId",userId);
               }
               if(startTime!=null && !("").equals(startTime)){
                   map.put("startTime", DateAndTimeUtil.convert(startTime));
               }
               if(endTime!=null && !("").equals(endTime)){
                   map.put("startTime", DateAndTimeUtil.convert(endTime));
               }
               //收入：回款，充值------ 支出：出借
                 //查询出借人充值记录
                 PageInfo<RechargeMoney> rechargeMoneyPageInfo = accountDetailsService.selectRechargeList(map, pageNum, pageSize);
                 Integer CZ_count = accountDetailsService.selectRechargeCount(map);
                 //查询出借人回款记录
                 PageInfo<UserDealBackMoneyRecord> userDealBackMoneyRecordPageInfo = accountDetailsService.selectReturnedEarningsMoney(map,pageNum,pageSize);
                 Integer HK_count = accountDetailsService.selectReturnedEarningsMoneyCount(map);
                //查询出借人出借记录
                PageInfo<UserDealMoney> userDealMoneyPageInfo = accountDetailsService.selectUserDealMoneyList(map, pageNum, pageSize);
                Integer CJ_count = accountDetailsService.selectUserDealMoneyCount(map);
                 Map map2 = new HashMap();

                 if(type1!=null && type1==0){
                     if(type2 !=-1 && type2 ==2 && type2!=null){
                         map1.put("pageInfo",rechargeMoneyPageInfo);
                         map1.put("count",CZ_count);
                     }else if(type2 !=-1 && type2 ==3  && type2!=null){
                         map1.put("pageInfo",userDealBackMoneyRecordPageInfo);
                         map1.put("count",HK_count);
                     }else if(type2 !=-1 && type2 ==4  && type2!=null){
                         map1.put("pageInfo",null);
                         map1.put("count",0);
                     }else {
                         map2.put("rechargeMoneyPageInfo",rechargeMoneyPageInfo);
                         map2.put("userDealBackMoneyRecordPageInfo",userDealBackMoneyRecordPageInfo);
                         map1.put("pageInfo",map2);
                         map1.put("count",CZ_count + HK_count);
                     }
                 }else if( type1!=null && type1==1){
                     if(type2!=null && type2 !=-1 && type2 ==2){
                         map1.put("pageInfo",null);
                         map1.put("count",0);
                     }else if(type2!=null && type2 !=-1 && type2 ==3){
                         map1.put("pageInfo",null);
                         map1.put("count",0);
                     }else if(type2!=null && type2 !=-1 && type2 ==4){
                         map1.put("pageInfo",userDealMoneyPageInfo);
                         map1.put("count",CJ_count);
                     }else{
                         map1.put("pageInfo",userDealMoneyPageInfo);
                         map1.put("count",CJ_count);
                     }
                 }else{
                     if(type2!=null && type2 !=-1 && type2 ==2){
                         map1.put("pageInfo",rechargeMoneyPageInfo);
                         map1.put("count",CZ_count);
                     }else if(type2!=null &&  type2 !=-1 && type2 ==3){
                         map1.put("pageInfo",userDealBackMoneyRecordPageInfo);
                         map1.put("count",HK_count);
                     }else if(type2!=null && type2 !=-1 && type2 ==4){
                         map1.put("pageInfo",userDealMoneyPageInfo);
                         map1.put("count",CJ_count);
                     }else {
                         map2.put("rechargeMoneyPageInfo",rechargeMoneyPageInfo);
                         map2.put("userDealBackMoneyRecordPageInfo",userDealBackMoneyRecordPageInfo);
                         map2.put("userDealMoneyPageInfo",userDealMoneyPageInfo);
                         map1.put("pageInfo",map2);
                         map1.put("count",CZ_count + CJ_count + HK_count);
                     }
                 }
            result.setData(map1);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
