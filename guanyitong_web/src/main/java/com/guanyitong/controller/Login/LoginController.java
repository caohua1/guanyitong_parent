package com.guanyitong.controller.Login;

import com.guanyitong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import util.JsonResult;

@Controller
@RequestMapping("/LoginController")
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     */
    @RequestMapping("/managementLogin")
    public JsonResult login(Long edeptno,String epassword){

        JsonResult result = new JsonResult();
        try{
            boolean register = employeeService.register(edeptno, epassword);
            if(register){
                System.out.println("登录成功"+register);
                result.setState(JsonResult.SUCCESS);
                result.setMessage("登录成功");
            }else {
                System.out.println("登录失败"+register);
                result.setState(JsonResult.ERROR);
                result.setMessage("密码不对，登录失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            result.setState(JsonResult.ERROR);
            result.setMessage("登录失败");
        }
        return result;
    }
}
