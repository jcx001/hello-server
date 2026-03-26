package com.fwj.helloserver.config;

import com.fwj.helloserver.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC配置类，注册拦截器
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**") // 拦截所有/api开头的请求
                .excludePathPatterns(
                        "/api/users/login",    // 放行登录接口
                        "/api/users",          // 放行新增用户接口
                        "/api/users/*"         // 放行获取用户信息接口
                );
    }
}