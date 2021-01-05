package com.car.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.car.exception.BizException;
import com.car.util.NetWorkUtil;
import com.car.util.RStatic;
import com.car.util.TokenUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponseInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class HttpFilter implements HandlerInterceptor {

    @Override
    @SneakyThrows
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws BizException {
        String token = request.getHeader("token");
        String userId = request.getHeader("userId");
        String ip = NetWorkUtil.getIpAddress(request);
        String role = TokenUtil.checkToken(token, userId, ip, "role");
        if (null != role) {
            log.info("ip:"+ip+"请求了"+request.getServletPath()+"接口，请求成功，角色："+role);
            return true;
        }
        return true;
//        log.warn("ip:"+ip+"请求了"+request.getServletPath()+"接口，请求失败，token信息过期");
//        response.setHeader("content-type","application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.getWriter().print(JSONObject.toJSONString(RStatic.error(5001,"token失效,请重新登录")));
//        return false;

    }
}
