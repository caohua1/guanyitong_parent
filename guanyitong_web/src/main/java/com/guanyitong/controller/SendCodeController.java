package com.guanyitong.controller;
import com.guanyitong.model.User;
import com.guanyitong.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import util.JsonResult;
import util.SendCodeUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@RequestMapping("/api")
@Controller
public class SendCodeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    /**
     * @author hang
     * @Decription 注册，发送短信验证码,保存到Session中
     * @return 返回状态参数
     * @throws Exception
     */
    @RequestMapping(value = "/sendCode", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult SendCheckMessage(HttpServletRequest request,User u){
        JsonResult result = new JsonResult();
        try{
            String message = "发送成功";
            String phone=u.getUserName(); //获取到客户端发来的手机号,用户名就是手机号
            User user = userService.selectUserName(u);
            if (user == null) {
                message = "该手机号未被注册";
                result.setState(JsonResult.ERROR);
                result.setMessage(message);
            } else {
                HashMap<String, String> m = SendCodeUtil.getMessageStatus(phone); //应用发送短信接口
                String res = m.get("result"); //获取到result值
                if (res.trim().equals("1")) { //如果为1，表示成功发送
                    String code = m.get("code"); //获取发送的验证码内容
                    logger.info("发送的验证码:"+code); //打印日志
                    HttpSession session = request.getSession(); //设置session
                    session.setAttribute("code", code); //将短信验证码放到session中保存
                    session.setMaxInactiveInterval(60 * 5);//保存时间 暂时设定为5分钟
                    result.setState(JsonResult.SUCCESS);
                    result.setData(code);//将验证码转到app手机端
                    result.setMessage(message);
                } else {
                    message = "短信发送失败";
                    result.setState(JsonResult.ERROR);
                    result.setMessage(message);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
        }
        return result;
    }


    /**
     * @author hang
     * @Decription 注册，发送短信验证码,保存到Session中
     * @return 返回状态参数
     * @throws Exception
     */
    @RequestMapping(value = "/sendCode1", method = RequestMethod.GET)
    @ResponseBody
    public JsonResult SendCheckMessage1(HttpServletRequest request,User u){
        JsonResult result = new JsonResult();
        try{
            String message = "发送成功";
            String phone=u.getUserName(); //获取到客户端发来的手机号,用户名就是手机号
            User user = userService.selectUserName(u);
            if (user == null) {
                message = "该手机号未被注册";
                result.setState(JsonResult.ERROR);
                result.setMessage(message);
            } else {
                HashMap<String, String> messageStatus1 = SendCodeUtil.getMessageStatus1(phone);//应用发送短信接口
                if(messageStatus1.get("code").equals("2")){
                    String mobile_code = messageStatus1.get("mobile_code");
                    HttpSession session = request.getSession(); //设置session
                    session.setAttribute("mobile_code", mobile_code); //将短信验证码放到session中保存
                    session.setMaxInactiveInterval(60 * 5);//保存时间 暂时设定为5分钟
                    result.setState(JsonResult.SUCCESS);
                    result.setData(messageStatus1);
                    result.setMessage(message);
                }else{
                    result.setState(JsonResult.ERROR);
                    result.setMessage("发送失败");
                }

            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
        }
        return result;
    }
}
