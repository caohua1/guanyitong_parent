package com.guanyitong.controller.Login;


import com.guanyitong.model.Employee;
import com.guanyitong.model.vo.PermissionVo;
import com.guanyitong.service.EmployeeService;
import com.guanyitong.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PermissionService permissionService;

   /* @RequestMapping(value="/login",method=RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("employee", new Employee());
        return "login/login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(@Valid Employee employee,BindingResult bindingResult,Model model){
        try {
            if(bindingResult.hasErrors()){
                return "login/login";
            }
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
            SecurityUtils.getSubject().login(new UsernamePasswordToken(employee.getEphone(), employee.getEpassword()));
            model.addAttribute("employee",employee);
            return "borrowUserManager/index";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("message","用户名或密码错误");
            return "login/login";
        }
    }
*/

    /**
     * 登录验证
     * @param employee
     * @param model
     * @return
     */
   @RequestMapping(value="/login",method=RequestMethod.POST)
   public String login(@Valid Employee employee , Model model){
       try {
           Employee employee1 = employeeService.login(employee);
           //查出是否有此用户
           if(employee1!=null){
               model.addAttribute("employee",employee1);
               List<PermissionVo> list = new ArrayList<PermissionVo>();
               //查询此用户的权限
               List<PermissionVo> permissions = permissionService.permissions(employee1.getId());
               if(permissions!=null && permissions.size()>0){
                   for(PermissionVo permissionVo : permissions){
                       if(permissionVo!=null){
                           if(("").equals(permissionVo.getParentId()) || (null == permissionVo.getParentId())){
                               List<PermissionVo>  permissionVos1 = permissionService.child_permissions(permissionVo.getId());
                               if(permissionVos1!=null && permissionVos1.size()>0){
                                   for(PermissionVo permissionVo1 : permissionVos1){
                                       list.add(permissionVo1);
                                   }
                               }else{
                                   list.add(permissionVo);
                               }
                           }else{
                               list.add(permissionVo);
                           }
                       }
                   }
               }

               //去除集合的重复数据
               if(list!=null && list.size()>0){
                   for (int i = 0; i < list.size()-1; i++) {
                       for (int j = list.size()-1; j > i; j--) {
                           if (list.get(j).getId() == list.get(i).getId()) {
                               list.remove(j);
                           }
                       }
                   }
               }

               //父级菜单的名称
               List<String> parentNameList = new ArrayList();
               if(list!=null && list.size()>0){
                   for(int i=0;i<list.size();i++){
                       if(i==0){
                           parentNameList.add(list.get(0).getParentName());
                       }else {
                           if(list.get(i)!=null){
                               if((null!=list.get(i).getParentName())&&(!list.get(i).getParentName().equals(list.get(i-1).getParentName()))){
                                   parentNameList.add(list.get(i).getParentName());
                               }else if((null==list.get(i).getParentName()) && (!list.get(i).getPermissionName().equals(list.get(i-1).getPermissionName()))){
                                   parentNameList.add(list.get(i).getPermissionName());
                               }
                           }
                       }
                   }

               }

               //展示到页面
               model.addAttribute("permissionList",list);
               model.addAttribute("parentNameList",parentNameList);

               return "userManager/index";
           }else{
               model.addAttribute("message","用户名或密码错误");
               return "login/login";
           }
       } catch (Exception e) {
           e.printStackTrace();
           model.addAttribute("message","用户名或密码错误");
           return "login/login";
       }
   }

    /**
     * 退出进入登录页面
     * @param model
     * @return
     */
    @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(Model model ) {
        model.addAttribute("message","您已安全退出");
        return "login/login";
    }

   /* @RequestMapping(value="/logout",method=RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes ){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        SecurityUtils.getSubject().logout();
        redirectAttributes.addFlashAttribute("message", "您已安全退出");
        return "login/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        return "/403";
    }*/
}