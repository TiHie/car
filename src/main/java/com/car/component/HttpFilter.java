package com.car.component;

import com.car.exception.BizException;
import com.car.util.NetWorkUtil;
import com.car.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpFilter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        String ip = NetWorkUtil.getIpAddress(request);
        String role = TokenUtil.checkToken(token, userId, ip, "role");
        if (null != role) {
            log.info("ip:"+ip+"请求了"+request.getServletPath()+"接口，请求成功，角色："+role);
            return true;
        }
        log.error("ip:"+ip+"请求了"+request.getServletPath()+"接口，请求失败");
        throw new BizException(50001,"token失效，请重试");

    }
}
