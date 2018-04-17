package com.guanyitong.controller.UserApproveManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.model.User;
import com.guanyitong.service.BorrowMoneyUserService;
import com.guanyitong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BorrowMoneyUser")
public class BorrowMoneyUserController {
    @Autowired
    private BorrowMoneyUserService borrowMoneyUserService;
    @Autowired
    private UserService userService;

    /**
     * 借款主体的新用户添加
     * @param borrowMoneyUser
     * @return
     */
    @RequestMapping("/addBorrowMoneyUser")
    @ResponseBody
    public JsonResult addBorrowMoneyUser(BorrowMoneyUser borrowMoneyUser,String companytime){
        JsonResult result = new JsonResult();
        try{
            borrowMoneyUser.setCreateTime(new Date());
            if(companytime!=null && !("").equals(companytime)){
                borrowMoneyUser.setCompanyCreateTime(DateAndTimeUtil.convert(companytime));
            }
            Integer i = borrowMoneyUserService.insertUser(borrowMoneyUser);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("返回数据成功");
                result.setData(borrowMoneyUser);
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

    @RequestMapping(value="/selectAllBorrowUser",method= RequestMethod.POST)
    @ResponseBody
    public JsonResult selectAllBorrowUser( BorrowMoneyUser borrowMoneyUser, String startTime,  String endTime, Integer pageNum, Integer pageSize,Integer type,String Sta){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(borrowMoneyUser!=null){
                if(borrowMoneyUser.getId()!=null){
                    map.put("id",borrowMoneyUser.getId());
                }
                if(borrowMoneyUser.getApprroveName()!=null && !("").equals(borrowMoneyUser.getApprroveName())){
                    map.put("apprroveName",borrowMoneyUser.getApprroveName());
                }
                if(borrowMoneyUser.getLegalIDCard()!=null && !("").equals(borrowMoneyUser.getLegalIDCard())){
                    map.put("legalIDCard",borrowMoneyUser.getLegalIDCard());
                }
                if(borrowMoneyUser.getCompanyName()!=null && !("").equals(borrowMoneyUser.getCompanyName())){
                    map.put("companyName",borrowMoneyUser.getCompanyName());
                }
                if(borrowMoneyUser.getAuditUserName()!=null && !("").equals(borrowMoneyUser.getAuditUserName())){
                    map.put("auditUserName",borrowMoneyUser.getAuditUserName());
                }
                if(borrowMoneyUser.getStatus()!=null && borrowMoneyUser.getStatus()>=0){
                    map.put("status",borrowMoneyUser.getStatus());
                }
                if(type !=null){
                    map.put("type",type);
                }
            }
            if(Sta !=null && !("").equals(Sta)){
                map.put("Status",Sta);
            }
            if(startTime !=null && !("").equals(startTime)){
                map.put("startTime", DateAndTimeUtil.convert(startTime) );
            }
            if(endTime !=null && !("").equals(endTime)){
                map.put("endTime",DateAndTimeUtil.convert(endTime));
            }
            int count = borrowMoneyUserService.selectCount(map);
            PageInfo<BorrowMoneyUser> pageInfo = borrowMoneyUserService.selectAllBorrowUser(map, pageNum, pageSize);
            Map data = new HashMap();
            data.put("count",count);
            data.put("pageInfo",pageInfo);
            result.setData(data);
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
     *  审核 借款人的基本信息(合同管理)
     *  审核 借款额度
     *  合同的确认
     * 0 认证信息待审核  1 认证审核失败 2 认证信息审核成功，借款额度待审核
     * 3 借款额度审核失败  4 借款额度审核成功，合同待确认  5 合同确认失败  6 合同确认成功，产品待审核
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
     * 查看页面
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

    /**
     * 查询所有注册的出借用户（分页，条件，模糊）
     * @param user
     * @param startTime
     * @param endTime
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllUser")
    @ResponseBody
   public JsonResult selectAllUser(User user, String startTime,String endTime,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(user !=null){
                if(user.getUsername()!=null && !("").equals(user.getUsername())){
                    map.put("username",user.getUsername());
                }
                if(user.getStatus()!=null && user.getStatus()>0){
                    map.put("status",user.getStatus());
                }
            }
            if(startTime!=null && !("").equals(startTime)){
                map.put("startTime",DateAndTimeUtil.convert(startTime));
            }
            if(endTime!=null && !("").equals(endTime)){
                map.put("endTime",DateAndTimeUtil.convert(endTime));
            }
            PageInfo<User> pageInfo = userService.selectAllUser(map, pageNum, pageSize);
            Integer count = userService.selectCount(map);
            Map data = new HashMap();
            data.put("count",count);
            data.put("pageInfo",pageInfo);
            result.setState(JsonResult.SUCCESS);
            result.setData(data);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
   }
}
