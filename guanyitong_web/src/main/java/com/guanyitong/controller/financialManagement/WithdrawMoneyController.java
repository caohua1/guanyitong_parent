package com.guanyitong.controller.financialManagement;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.WithdrawalMoney;
import com.guanyitong.model.vo.WithdrawalMoneyVo;
import com.guanyitong.service.WithdrawMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.Date;
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
     * @param withdrawalMoneyVo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectWithdrawal")
    @ResponseBody
    public JsonResult selectWithdrawal(WithdrawalMoneyVo withdrawalMoneyVo,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<WithdrawalMoneyVo> pageInfo = withdrawMoneyService.selectWithdrawal(withdrawalMoneyVo, pageNum, pageSize);
            result.setState(JsonResult.SUCCESS);
            result.setData(pageInfo);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
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
