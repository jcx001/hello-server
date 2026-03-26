package com.fwj.helloserver.interceptor;

import com.fwj.helloserver.common.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录鉴权拦截器
 */
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 获取请求头中的Token
        String token = request.getHeader("Authorization");

        // 校验Token是否存在
        if (token == null || token.isEmpty()) {
            // 设置响应格式和编码
            response.setContentType("application/json;charset=UTF-8");
            // 构造错误响应数据
            Map<String, Object> errorResult = new HashMap<>();
            errorResult.put("code", ResultCode.TOKEN_INVALID.getCode());
            errorResult.put("msg", ResultCode.TOKEN_INVALID.getMsg());
            // 写入响应
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResult));
            return false; // 拦截请求
        }

        return true; // 放行请求
    }
}