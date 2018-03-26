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

@Controller
@RequestMapping("/RechargeSheetVo")
public class RechargeSheetVoController {

    @Autowired
    private RechargeSheetVoService rechargeSheetVoService;

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
            List<RechargeSheetVo> listRechargeSheetVos = rechargeSheetVoPageInfo.getList();
            int numBer=0;
            int amount=0;
            for (RechargeSheetVo ignored :listRechargeSheetVos) {
                if(ignored.getStatus()==1){
                    int rechargeMoney = Integer.parseInt(ignored.getRechargeMoney());
                    amount+=rechargeMoney;
                }
                numBer++;
            }
            System.out.println("充值成功的总金额是"+amount+"$");
            System.out.println("数据库查询"+numBer+"条数据");
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
