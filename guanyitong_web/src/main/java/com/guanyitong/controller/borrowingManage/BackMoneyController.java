package com.guanyitong.controller.borrowingManage;
import com.guanyitong.model.BackMoney;
import com.guanyitong.service.BackMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

@RequestMapping("/backMoney")
@Controller
public class BackMoneyController {
    @Autowired
    private BackMoneyService backMoneyService;

    /**
     * 借款人回款（status=1）
     * @param backMoney
     * @return
     */
    @RequestMapping("/backMoney")
    @ResponseBody
    public JsonResult backMoney(BackMoney backMoney){
        JsonResult result = new JsonResult();
        try{
            Boolean b = backMoneyService.updateStatus(backMoney);
            if(b==true){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("回款成功");
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("回款失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("操作失败");
        }
        return result;
    }
}
