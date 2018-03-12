package com.guanyitong.controller.ApproveManager;
import com.guanyitong.model.BorrowMoneyUser;
import com.guanyitong.service.BorrowMoneyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;

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
