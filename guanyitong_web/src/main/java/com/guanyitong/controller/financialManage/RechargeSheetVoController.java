package com.guanyitong.controller.financialManage;



import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.RechargeSheetVo;
import com.guanyitong.service.RechargeSheetVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.DateAndTimeUtil;
import util.JsonResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//充值记录
@Controller
@RequestMapping("/RechargeSheetVo")
public class RechargeSheetVoController {

    @Autowired
    private RechargeSheetVoService rechargeSheetVoService;

    /**
     * 查询充值记录
     * @param pageNum
     * @param pageSize
     * @param rechargeSheetVo 条件查询
     * @param firstMoney
     * @param lastMoney
     * @param firstDate
     * @param lastDate
     * @return
     */
    @RequestMapping("/selectRechargeSheetVo")
    @ResponseBody
    public JsonResult selectRechargeSheetVo(Integer pageNum, Integer pageSize,
                                           RechargeSheetVo rechargeSheetVo,
                                            @RequestParam(required=false)String firstMoney, @RequestParam(required=false)String lastMoney,
                                            @RequestParam(required=false)String firstDate, @RequestParam(required=false)String lastDate){
        JsonResult result = new JsonResult();
        Map<Object, Object> demandMap = new HashMap<Object, Object>();
        DateAndTimeUtil dateAndTimeUtil = new DateAndTimeUtil();
        Date firDate=null;
        Date lasDate =null;
        if(firstDate!=null&&firstDate!=""){
            firDate = dateAndTimeUtil.convert(firstDate);
        }
        if(lastDate!=null&&lastDate!=""){
            lasDate = dateAndTimeUtil.convert(lastDate);
        }
        try{
            if(rechargeSheetVo!=null){
                if(rechargeSheetVo.getUserId()!=null && ("").equals(rechargeSheetVo.getUserId())){
                    demandMap.put("userId",rechargeSheetVo.getUserId());
                }
                if(rechargeSheetVo.getUsername()!=null && !("").equals(rechargeSheetVo.getUsername())){
                    demandMap.put("phone",rechargeSheetVo.getUsername());
                }
                if(rechargeSheetVo.getSerial()!=null && !("").equals(rechargeSheetVo.getSerial())){
                    demandMap.put("serial",rechargeSheetVo.getSerial());
                }
                if(rechargeSheetVo.getRealName()!=null && !("").equals(rechargeSheetVo.getRealName())){
                    demandMap.put("realName",rechargeSheetVo.getRealName());
                }
                if(rechargeSheetVo.getUserId()!=null && !("").equals(rechargeSheetVo.getUserId())){
                    demandMap.put("userId",rechargeSheetVo.getUserId());
                }
                if(rechargeSheetVo.getStatus()!=null && !("").equals(rechargeSheetVo.getStatus())){
                    demandMap.put("status",rechargeSheetVo.getStatus());
                }
            }
            if(firstMoney!=null && firstMoney!=""){
                demandMap.put("firstMoney",firstMoney);
            }
            if(lastMoney!=null && lastMoney!=""){
                demandMap.put("lastMoney",lastMoney);
            }
            if(firDate!=null){
                demandMap.put("firstDate",firDate);
            }
            if(lasDate!=null){
                demandMap.put("lastDate",lasDate);
            }
            PageInfo<RechargeSheetVo> rechargeSheetVoPageInfo = rechargeSheetVoService.listRechargeSheetVo(demandMap,pageNum, pageSize);
            Integer rechargeCount = rechargeSheetVoService.RechargeSheetCount(demandMap);
            HashMap rechargeMap = new HashMap();
            rechargeMap.put("PageInfo",rechargeSheetVoPageInfo);
            rechargeMap.put("count",rechargeCount);
            result.setData(rechargeMap);
            result.setState(JsonResult.SUCCESS);
            result.setMessage("返回数据成功");
        }catch (Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
@RequestMapping("/selectByrid")
    public String selectByUserId(String id, Model model) throws ParseException {
    Long rid= Long.valueOf(id);
    List<RechargeSheetVo> rechargeSheetVos = rechargeSheetVoService.selectByrid(rid);
    for (RechargeSheetVo recharge:rechargeSheetVos
         ) {
        recharge.getRechargeTime();
        System.out.println("时间----"+recharge.getRechargeTime());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formatDate = df.format(recharge.getRechargeTime());
        recharge.setRechargeTime(df.parse(formatDate));
        System.out.println(formatDate);
    }
    model.addAttribute("rechargeSheet",rechargeSheetVos);
    return "moneyManager/rechargeManagement_select";
    }
}
