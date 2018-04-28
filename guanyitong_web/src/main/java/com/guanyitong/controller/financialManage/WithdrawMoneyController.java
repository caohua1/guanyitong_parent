package com.guanyitong.controller.financialManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.model.vo.WithdrawalMoneyVo;
import com.guanyitong.service.BackMoneyService;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.*;

@Controller
@RequestMapping("/withdrawMoney")
//提现管理
public class WithdrawMoneyController {
    @Autowired
    private WithdrawMoneyService withdrawMoneyService;
    @Autowired
    private BackMoneyService backMoneyService;
    /**
     * 提现，添加数据（//pc端的是借款人,有申请人姓名）
     * @return
     */
    @RequestMapping("/insertWithdrawMoney")
    @ResponseBody
    public JsonResult insertWithdrawMoney(WithdrawalMoney withdrawalMoney){
        JsonResult result = new JsonResult();
        try{
            withdrawalMoney.setUserType(1);//借款人申请提现
            Boolean b = withdrawMoneyService.insertWithdrawMoney(withdrawalMoney);
            if(b==true){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("申请提现成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("申请提现失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("申请提现失败");
        }
        return result;
    }

    /**
     * 分页，条件查询，所有提现数据
     * @param
     * @param pageNum
     * @param pageSize
     * @retur
     *
     */
    @RequestMapping("/selectWithdrawal")
    @ResponseBody
    public JsonResult selectWithdrawal(String username,String borrowMoneyUserId,String realName,String idCard,String txNumber,
                                       Double minMoney,Double maxMoney,String startTime,String endTime,String sqUser,
                                       Integer userType,Integer status,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        WithdrawalMoneyVo withdrawalMoneyVo = new WithdrawalMoneyVo();
        try{
            if(username!=null && username!=""){
                withdrawalMoneyVo.setUsername(username);
            }
            if(borrowMoneyUserId!=null && borrowMoneyUserId!=""){
                withdrawalMoneyVo.setBorrowMoneyUserId(borrowMoneyUserId);
            }
            if(realName!=null && realName!=""){
                withdrawalMoneyVo.setRealName(realName);
            }
            if(idCard!=null && idCard!=""){
                withdrawalMoneyVo.setIdCard(idCard);
            }
            if(txNumber!=null && txNumber!=""){
                withdrawalMoneyVo.setTxNumber(txNumber);
            }
            if(!minMoney.isNaN()){
                withdrawalMoneyVo.setMinMoney(minMoney);
            }
            if(!maxMoney.isNaN()){
                withdrawalMoneyVo.setMaxMoney(maxMoney);
            }
            if(startTime!=null&&!("").equals(startTime)){
                withdrawalMoneyVo.setStartTime(DateAndTimeUtil.convert(startTime));
            }if(endTime!=null&&!("").equals(endTime)){
                withdrawalMoneyVo.setEndTime(DateAndTimeUtil.convert(endTime));
            }if(sqUser!=null&&sqUser!=""){
                withdrawalMoneyVo.setSqUser(sqUser);
            }if(userType!=null){
                withdrawalMoneyVo.setUserType(userType);
            }if(status!=null){
                withdrawalMoneyVo.setStatus(status);
            }
            HashMap rechargeMap = new HashMap();
            PageInfo<WithdrawalMoneyVo> pageInfo = withdrawMoneyService.selectWithdrawal(withdrawalMoneyVo, pageNum, pageSize);
            Integer totalMoney = withdrawMoneyService.totalJe();
            Integer allCount = withdrawMoneyService.selectWithdrawalCount(withdrawalMoneyVo);
            rechargeMap.put("pageInfo",pageInfo);
            rechargeMap.put("totalMoney",totalMoney);
            rechargeMap.put("allCount",allCount);
            result.setState(JsonResult.SUCCESS);
            result.setData(rechargeMap);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
    /**
     * 确认提现1成功、2失败（修改状态,如果是提现成功，那么会生成一个还款计划（批量插入backMoney））,添加审核时间
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(@RequestParam(required = false)String borrowMoneyUserId,@RequestParam(required = false)Integer status,  Long id,@RequestParam(required = false)String dzMoney){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(id !=null){
                map.put("id",id);
            }
            if(status !=null){
                map.put("status",status);
            }
            if(status ==1){
                map.put("txTime",new Date());
                map.put("dzMoney",dzMoney);
                map.put("borrowMoneyUserId",borrowMoneyUserId);
            }
            if(status ==2){
                map.put("borrowMoneyUserId",borrowMoneyUserId);
            }
            Boolean b = withdrawMoneyService.updateStatus(map);
            if(b==true){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("操作成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("操作失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("操作失败");
        }
        return result;
    }
}
