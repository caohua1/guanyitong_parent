package com.guanyitong.filter;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义用户过滤器
 * @author
 *
 */
public class SysUserFilter extends PathMatchingFilter {
   /* @Resource
    private ISysUserService sysUserService;
*/

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        //可以参考http://jinnianshilongnian.iteye.com/blog/2025656
        return true;
    }
}
