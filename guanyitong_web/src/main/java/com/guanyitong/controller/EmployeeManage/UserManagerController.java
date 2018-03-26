package com.guanyitong.controller.EmployeeManage;
import com.github.pagehelper.PageInfo;
import com.guanyitong.model.AccountManager;
import com.guanyitong.model.User;
import com.guanyitong.model.UserPersonalData;
import com.guanyitong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
@Controller
@RequestMapping("/user")
public class UserManagerController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    /**
     * 分页查询所有出借注册的用户的信息
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping("/selectAllRegistUsers")
    @ResponseBody
    public JsonResult selectAllRegistUsers(User user,Integer pageNum,Integer pageSize){
        JsonResult result = new JsonResult();
        try{
            PageInfo<User> pageInfo = userService.selectAllRegistUsers(user, pageNum, pageSize);
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
     * 查询某个用户的个人资料
     * @param id
     * @return
     */
    @RequestMapping("/selectUserPersonalDataById")
    @ResponseBody
    public JsonResult selectUserPersonalDataById(Long id){
        JsonResult result = new JsonResult();
        try{
            if(id !=null){
                UserPersonalData userPersonalData = userService.selectUserPersonalDataById(id);
                if(userPersonalData!=null){
                    result.setState(JsonResult.SUCCESS);
                    result.setData(userPersonalData);
                    result.setMessage("返回数据成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("此用户还没有填写个人资料信息");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询某个用户绑定银行卡信息
     * @param id
     * @return
     */
    @RequestMapping("/selectAccountManagerById")
    @ResponseBody
    public JsonResult selectAccountManagerById(Long id){
        JsonResult result = new JsonResult();
        try{
            if(id !=null) {
                AccountManager accountManager = userService.selectAccountManagerById(id);
                if(accountManager!=null){
                    result.setState(JsonResult.SUCCESS);
                    result.setData(accountManager);
                    result.setMessage("返回数据成功");
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("此用户还没有绑定银行卡");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
