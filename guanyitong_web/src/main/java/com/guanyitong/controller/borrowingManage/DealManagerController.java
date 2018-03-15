package com.guanyitong.controller.borrowingManage;

import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.UserDealMoneyVo;
import com.guanyitong.service.UserDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/deal")
public class DealManagerController {
    @Autowired
    private UserDealService userDealService;
    @Autowired
    private BackMoneyService backMoneyService;
    /**
     * (分页)查询所有用户的出借情况
     * @param productInfoId
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllUserDeal")
    @ResponseBody
    public JsonResult selectAllUserDeal(@RequestParam(required=false) Long productInfoId, @RequestParam(required=false)Integer status, Integer pageNum, Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(productInfoId !=null){
                map.put("productInfoId",productInfoId);
            }
            if(status !=null){
                map.put("status",status);
            }
            PageInfo<UserDealMoneyVo> pageInfo = userDealService.selectAllUserDeal(map, pageNum, pageSize);
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
     * 分页)查询所有某个用户的某个出借的--回款--详情
     * @param dealMoneyId(某用户的某项出借的回款情况)
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectBackMoneyByDealId")
    @ResponseBody
    public JsonResult selectBackMoneyByDealId(Long dealMoneyId,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(dealMoneyId != null){
              map.put("dealMoneyId",dealMoneyId);
            }
            PageInfo<BackMoney> pageInfo = backMoneyService.selectBackMoneyByDealId(map, pageNum, pageSize);
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
}
