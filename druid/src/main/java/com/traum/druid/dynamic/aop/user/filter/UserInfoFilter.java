package com.traum.druid.dynamic.aop.user.filter;

import com.traum.druid.dynamic.aop.user.UserThreadLocal;
import com.traum.druid.model.UserInfo;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 名称：
 * 阐述：
 *
 * @author Administrator
 * @date 2022/7/11 19:21
 */
@Component
@WebFilter(filterName = "MyFilter", urlPatterns = "/*")
public class UserInfoFilter implements Filter {
    private static Map<String,UserInfo> USER_MAP = new HashMap<>();
    static {
        UserInfo userInfo1 = new UserInfo("1","traum","1");
        USER_MAP.put("1",userInfo1);
        UserInfo userInfo2 = new UserInfo("2","angel","2");
        USER_MAP.put("2",userInfo2);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        UserInfo userInfo = USER_MAP.getOrDefault(token,new UserInfo());
        UserThreadLocal.setUserInfo(userInfo);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
