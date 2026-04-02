package com.fwj.helloserver.config;

import com.fwj.helloserver.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;

    // 构造注入拦截器
    public WebConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                // 拦截所有请求
                .addPathPatterns("/**")
                // 【关键】放行登录、注册、系统错误接口，不需要Token
                .excludePathPatterns(
                        "/api/users",       // 注册接口
                        "/api/users/login", // 登录接口
                        "/error"            // SpringBoot内置错误接口
                );
    }
}