package com.guanyitong.controller.shiro;

import com.guanyitong.model.Employee;
import com.guanyitong.service.EmployeeService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import util.MD5Util;

//shiro权限认证
@SuppressWarnings("deprecation")
@Controller
public class ShiroSecurityRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeService employeeService;

    public ShiroSecurityRealm() {
        setName("ShiroSecurityRealm"); // This name must match the name in the SysUser class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
    }
    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       /* //获取登录时输入的用户名
        String loginName=(String) principalCollection.fromRealm(getName()).iterator().next();
        //到数据库查是否有此对象
        User user=userService.findByName(loginName);
        if(user!=null){
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
            SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(user.getRolesName());
            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的四行可以不要
            List<Role> roleList=user.getRoleList();
            for (Role role : roleList) {
                info.addStringPermissions(role.getPermissionsName());
            }
            return info;
        }
        return null;*/
        return null;
    }

    /**
     * 登陆认证
     * @param Token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken Token) throws AuthenticationException {
        //UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token=(UsernamePasswordToken) Token;
        Employee employee = new Employee();
        employee.setEpassword(String.valueOf(token.getPassword()));
        employee.setEphone(token.getUsername());
        Employee employee1 = employeeService.login(employee);
        //查出是否有此用户
        if(employee1!=null){
            //若存在，将此用户存放到登录认证info中
            return new SimpleAuthenticationInfo(employee1.getEphone(),employee1.getEpassword(), getName());
        }
        return null;
    }
}
