package com.guanyitong.controller.app;

import com.guanyitong.model.RechargeMoney;
import com.guanyitong.service.RechargeSheetVoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.JsonResult;

@Controller
@RequestMapping("/")
public class RechargeController {

    @Autowired
    private RechargeSheetVoService rechargeSheetVoService;

    @RequestMapping("/recharge")
    public JsonResult recharge(RechargeMoney rechargeMoney){
        JsonResult result = new JsonResult();
        try{
            rechargeSheetVoService.insertRechargeMoney(rechargeMoney);
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
        }
        return result;
    }
}
