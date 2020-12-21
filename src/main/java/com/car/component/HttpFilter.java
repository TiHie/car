package com.car.component;

import com.car.util.RuntimeDataUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HttpFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String) request.getAttribute("username");
        String role = RuntimeDataUtil.roleMap.get(username);
        if (null == role){
            return false;
        }
        String servletPath = request.getServletPath();
        if (role.equals("user") && servletPath.contains("/api/admin")) {
            return false;
        }
        return false;
    }
}
