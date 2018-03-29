package com.guanyitong.controller.financialManage;



import com.github.pagehelper.PageInfo;
import com.guanyitong.model.vo.RechargeSheetVo;
import com.guanyitong.service.RechargeSheetVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

import javax.xml.crypto.Data;
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
                                            @RequestParam(required=false)RechargeSheetVo rechargeSheetVo,
                                            @RequestParam(required=false)String firstMoney, @RequestParam(required=false)String lastMoney,
                                            @RequestParam(required=false)Data firstDate, @RequestParam(required=false)Data lastDate){
        JsonResult result = new JsonResult();
        Map<Object, Object> demandMap = new HashMap<Object, Object>();
        try{
            if(rechargeSheetVo!=null){
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
            if(firstDate!=null){
                demandMap.put("firstDate",firstDate);
            }
            if(lastDate!=null){
                demandMap.put("lastDate",lastDate);
            }
            PageInfo<RechargeSheetVo> rechargeSheetVoPageInfo = rechargeSheetVoService.listRechargeSheetVo(pageNum, pageSize, demandMap);
            result.setData(rechargeSheetVoPageInfo);
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
