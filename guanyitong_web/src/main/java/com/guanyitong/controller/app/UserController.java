package com.guanyitong.controller.app;
import com.guanyitong.model.User;
import com.guanyitong.model.UserPersonalData;
import com.guanyitong.model.UserQuestion;
import com.guanyitong.model.UserQuestionContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.guanyitong.service.UserService;
import com.guanyitong.service.impl.LoginServiceImpl;
import util.FinalData;
import util.JsonResult;
import util.MD5Util;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class UserController {
    @Autowired
    private LoginServiceImpl loginService;
    @Autowired
    private UserService userService;

    /**
     * 累计用户数，累计交易额
     * @return
     */
    @RequestMapping(value = "/selectUserNumsAndDeals",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectUserNumsAndDeals(){
        JsonResult result = new JsonResult();
        try{
            Map map = new HashMap();
            //累计用户数量
            int userNumbers = userService.selectUserNums();
            map.put(FinalData.USER_NUMBER,userNumbers);
            //累计交易额
            int dealMoney = userService.selectDealMoney();
            map.put(FinalData.USER_DEALS_MONEY,dealMoney);
            result.setState(JsonResult.SUCCESS);
            result.setData(map);
            result.setMessage("返回数据成功");
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value="/regist",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult regist(User user){
        JsonResult result = new JsonResult();
        try {
            //先查询一下此用户名是否已经存在，如果已经存在就不能注册
            User u = userService.selectUserName(user);
            if(u==null){
                user.setPassword(MD5Util.MD5(user.getPassword()));
                user.setRegistTime(new Date());
                user.setStatus(1);
                int i = userService.insertUser(user);
                if(i>0){
                    result.setState(JsonResult.SUCCESS);
                    result.setData(user);
                    result.setMessage("注册成功");
                }
            }else{
                result.setState(JsonResult.ERROR);
                result.setMessage("此用户名已经存在，请重新注册");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 登录，并返回token
     * @param user
     * @return
     */
    @RequestMapping(value="/loginin",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult login(User user){
        JsonResult result = new JsonResult();
        try{
            String userName = user.getUserName();
            String password = user.getPassword();
            String token = loginService.login(userName, password);
            if(token!=null) {//登陆成功
                User u = userService.selectUserName(user);
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("token",token);
                map.put("userId",u.getId());
                result.setState(JsonResult.SUCCESS);
                result.setData(map);//登录成功返回用户token的一些信息
                result.setMessage("登录成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("用户名或密码错误");
        }
          return result;
    }
    /**
     * 用户修改登录密码
     * @param u
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult updatePassword(User u){
        JsonResult result = new JsonResult();
        try{
            String password = MD5Util.MD5(u.getPassword());//MD5加密密码
            u.setPassword(password);
            u.setUpdateTime(new Date());
            int i = userService.updatePassword(u);
            if(i>0){
                result.setState(JsonResult.SUCCESS);
                result.setMessage("密码修改成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("修改失败");
        }
        return result;
    }
    /**
     * 添加或者修改用户个人资料
     * @param userPersonalData
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertOrUpdateUserPersonalData",method = RequestMethod.GET)
    @ResponseBody
    public  JsonResult insertOrUpdateUserPersonalData(UserPersonalData userPersonalData, User user){
        JsonResult result = new JsonResult();
        try{
            userPersonalData.setCreatTime(new Date());
            if(user.getId()!=null){
                userPersonalData.setUserId(user.getId());
            }
            UserPersonalData userPersona = userService.selectPersonalData(user.getId());
            if(userPersona==null){//如果没有此用户的资料，就添加
                int i = userService.insertUserPersonalData(userPersonalData);
                if(i>0){
                    result.setState(JsonResult.SUCCESS);
                    result.setMessage("成功添加个人资料");
                }
            }else{//如果有此用户的个人资料，就去修改
                userPersonalData.setUpdateTime(new Date());
                userService.updatePersonalData(userPersonalData);
                result.setState(JsonResult.SUCCESS);
                result.setMessage("成功修改个人资料");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("保存失败");
        }
        return result;
    }
    /**
     * 查询个人资料
     * @param userId
     * @return
     */
    @RequestMapping(value = "/selectPersonalData",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult selectPersonalData(Long userId){
        JsonResult result = new JsonResult();
        try{
            UserPersonalData userPersonalData = userService.selectPersonalData(userId);
            if(userPersonalData!=null){
                result.setState(JsonResult.SUCCESS);
                result.setData(userPersonalData);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }

    /**
     * 查询问卷调查的内容
     * @return
     */
    @RequestMapping(value = "/userQuestion",method = RequestMethod.GET)
    @ResponseBody
    public JsonResult userQuestion(){
        JsonResult result = new JsonResult();
        try{
            List<UserQuestion> userQuestions = userService.selectQuestion();
            if(userQuestions!=null){
                for(UserQuestion userQuestion :userQuestions){
                    //查询每个问题对应的选项
                    List<UserQuestionContent> userQuestionContents = userService.selectUserQuestionContent(userQuestion.getId());
                    userQuestion.setUserQuestionContentList(userQuestionContents);
                }
                result.setData(userQuestions);
                result.setState(JsonResult.SUCCESS);
                result.setMessage("返回数据成功");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("返回数据失败");
        }
        return result;
    }
}
