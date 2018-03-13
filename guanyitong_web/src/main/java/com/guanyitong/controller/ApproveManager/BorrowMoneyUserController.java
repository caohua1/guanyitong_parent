package com.guanyitong.controller.ApproveManager;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.service.BorrowMoneyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BorrowMoneyUser")
public class BorrowMoneyUserController {
    @Autowired
    private BorrowMoneyUserService borrowMoneyUserService;

    /**
     * 借款主体的新用户添加
     * @param borrowMoneyUser
     * @return
     */
    @RequestMapping("/addBorrowMoneyUser")
    @ResponseBody
    public JsonResult addBorrowMoneyUser(BorrowMoneyUser borrowMoneyUser){
        JsonResult result = new JsonResult();
        try{
            Integer i = borrowMoneyUserService.insertUser(borrowMoneyUser);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("返回数据成功");
            }else {
                result.setState(JsonResult.ERROR);
                result.setMessage("返回数据失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 借款人认证资料审核列表，（分页）0待审核 (1 3 审核未通过) 4审核通过，条件查询
     * @param borrowMoneyUser
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllBorrowUser")
    @ResponseBody
    public JsonResult selectAllBorrowUser(BorrowMoneyUser borrowMoneyUser,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<BorrowMoneyUser> pageInfo = borrowMoneyUserService.selectAllBorrowUser(borrowMoneyUser, pageNum, pageSize);
            result.setData(pageInfo);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     *  审核借款人的基本信息(合同管理)
     * 0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款待审核
     * 3 借款审核失败  4 借款审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核
     * @param id
     * @param status
     * @param causeBy
     * @return
     */
    @RequestMapping("/updateStatus")
    @ResponseBody
    public JsonResult updateStatus(Long id, Integer status, @RequestParam(required=false)String causeBy, @RequestParam(required=false)String contractImage){
         JsonResult result = new JsonResult();
         try{
             Map map = new HashMap();
             map.put("id",id);
             map.put("status",status);
             if(status == 1 || status == 3){
                 map.put("causeBy",causeBy);
             }
             if(contractImage !=null && ("").equals(contractImage)){
                 map.put("contractImage",contractImage);
             }
             Integer i = borrowMoneyUserService.updateStatus(map);
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
             result.setMessage("返回数据失败");
         }
         return result;
    }
    /**
     * 展示需要认证审核资料
     */
    @RequestMapping("/selectBorrowMoneyUser")
    @ResponseBody
    public JsonResult selectBorrowMoneyUser(Long id){
        JsonResult result = new JsonResult();
        try{
            BorrowMoneyUser borrowMoneyUser = borrowMoneyUserService.selectBorrowMoneyUser(id);
            result.setData(borrowMoneyUser);
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
