package com.guanyitong.service.impl;
import com.guanyitong.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guanyitong.service.UserService;
import util.MD5Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl {
    @Autowired
    private UserService userService;
    /**
     * 存放“用户名：token”的键值对，static修饰只new一次，共用一个map
     */
    public static Map<String,String> tokenMap= new HashMap<String, String>();
    /**
     * 存放“（密码进行加密）token：user”的键值对，static修饰只new一次，共用一个map
     */
    public static Map<String, User> loginUserMap= new HashMap<String, User>();
    /**
     * 客户登录（同步）
     * @param username
     * @param password
     * @return
     */
    public String login(String username,String password){
        /**
         * 先判断用户是否存在
         */
        User user= new User();
        if(username!=null && password!=null){
            user.setUsername(username);
            user.setPassword(MD5Util.MD5(password));
            System.out.println(MD5Util.MD5(password));
        }
        User u = null;
        if(user!=null){
             u = userService.selectUser(user);
            System.out.println(user.getPassword());
        }
        if(u==null){
            return null;
        }
        /**
         * 判断是否登录成功
         * 1.登录成功
         * 1.1生成对应的token并更新
         * 1.2失败就抛异常
         */
        String token=tokenMap.get("userName");
        if(token==null){
            user.setUsername(username);
            user.setPassword(password);
            System.out.println("新用户登录");
        }else{
            user = loginUserMap.get(token);
            loginUserMap.remove(token);//更新用户登录的token（时间变化）
            System.out.println("更新用户登录的token");
        }
        //更新token
        token = MD5Util.MD5("userName:"+username+",password:"+password+",time:"+new Date().getTime());
        loginUserMap.put(token,user);
        tokenMap.put(username,token);
        System.out.println("目前有"+tokenMap.size()+"个用户");
        return token;
    }
}
