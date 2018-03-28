package com.guanyitong.controller.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.Sha256CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Controller;

//shiro权限认证
@SuppressWarnings("deprecation")
@Controller
public class ShiroSecurityRealm extends AuthorizingRealm {


    public ShiroSecurityRealm() {
        setName("ShiroSecurityRealm"); // This name must match the name in the SysUser class's getPrincipals() method
        setCredentialsMatcher(new Sha256CredentialsMatcher());
    }
    /**
     * 权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 登陆认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
