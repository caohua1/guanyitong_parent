package com.guanyitong.controller.financialManagement;

import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/withdrawMoney")
//提现管理
public class WithdrawMoneyController {
    @Autowired
    private WithdrawMoneyService withdrawMoneyService;

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
            Integer i = withdrawMoneyService.insertWithdrawMoney(withdrawalMoney);
            if(i>0){
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
     * 确认提现1成功、2失败（修改状态）,添加审核时间
     * @param status
     * @param id
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(@RequestParam(required = false)Integer status,  Long id){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(id !=null){
                map.put("id",id);
            }
            if(status !=null){
                map.put("status",status);
            }
            Integer i = withdrawMoneyService.updateStatus(map);
            if(i>0){
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
