package com.car.config;

import com.car.component.HttpFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HttpFilterConfig implements WebMvcConfigurer {

    //注册拦截器

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HttpFilter())
                .addPathPatterns("/**")
                .excludePathPatterns("/favicon.ico")
                .excludePathPatterns("/uploadimg/**")
                .excludePathPatterns("/api/v1/user/login")
                .excludePathPatterns("/api/admin/login")
                .excludePathPatterns("/api/user/login")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/api/user/checkToken")
                .excludePathPatterns("/swagger-ui.html/swagger-resources/configuration/ui");
    }
}
