package com.guanyitong.controller.moneyManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.MoneyManageVo;
import com.guanyitong.service.MoneyManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;
import java.util.*;

//资金账户管理模块
@Controller
@RequestMapping("/moneyManage")
public class MoneyManageController {
    @Autowired
    private MoneyManageService moneyManageService;
    /**
     *
     * @param username 用户名（手机号）
     * @param startTime 开通时间范围
     * @param endTime
     * @param minyuE 可用余额范围
     * @param maxyuE
     * @param minLJSY 累计收益范围
     * @param maxLJSY
     * @return
     */
    @RequestMapping("/selectUserMoney")
    @ResponseBody
    public JsonResult moneyManage(String username, String startTime,String endTime,Double minyuE,
                                  Double maxyuE,Double minLJSY,Double maxLJSY,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            if(username !=null && !("").equals(username)){
                map.put("username",username);
            }
            if(startTime !=null && !("").equals(startTime)){
                map.put("startTime", DateAndTimeUtil.convert(startTime));
            }
            if(endTime !=null && !("").equals(endTime)){
                map.put("endTime",DateAndTimeUtil.convert(endTime));
            }
            if(minyuE !=null){
                map.put("minyuE",minyuE);
            }
            if(maxyuE !=null){
                map.put("maxyuE",maxyuE);
            }
            PageInfo<MoneyManageVo> pageInfo = moneyManageService.moneyManage(map, pageNum, pageSize);
            Integer count = moneyManageService.selectAllAcountManagerCount(map);
            if(minLJSY !=null || maxLJSY !=null){
                List<MoneyManageVo> list = pageInfo.getList();
                List<MoneyManageVo> list1 = new ArrayList<MoneyManageVo>();
                if(list.size()>0 && list !=null){
                    for(MoneyManageVo moneyManageVo : list){//判断累计收益的范围
                        if(minLJSY !=null && maxLJSY ==null){
                            if(minLJSY<moneyManageVo.getLJSY()){
                                list1.add(moneyManageVo);
                            }
                        }
                        if(minLJSY==null && maxLJSY!=null){
                            if(maxLJSY>moneyManageVo.getLJSY()){
                                list1.add(moneyManageVo);
                            }
                        }
                        if(minLJSY!=null && maxLJSY !=null){
                            if(minLJSY<moneyManageVo.getLJSY() && maxLJSY>moneyManageVo.getLJSY()){
                                list1.add(moneyManageVo);
                            }
                        }
                    }
                }
                pageInfo.setList(list1);//把集合重新set进去
            }
            Map map1= new HashMap();
            map1.put("count",count);
            map1.put("pageInfo",pageInfo);
            result.setState(JsonResult.SUCCESS);
            result.setData(map1);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return  result;
    }
}
