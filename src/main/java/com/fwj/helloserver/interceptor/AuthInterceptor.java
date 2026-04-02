package com.fwj.helloserver.interceptor;

import com.fwj.helloserver.common.Result;
import com.fwj.helloserver.common.ResultCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 获取 token (假设从 header 或 parameter 取)
        String token = request.getHeader("token");
        // 或者 String token = request.getParameter("token");

        // 2. 校验 token
        if (token == null || !token.equals("valid_token")) { // 这里替换为真实的 JWT/Token 校验逻辑
            // 👇 使用刚刚添加的状态码
            Result<String> result = Result.error(ResultCode.TOKEN_INVALID);

            // 设置响应格式
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // 写入错误信息 (简单序列化，无需引入 Jackson 依赖)
            response.getWriter().write(String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":null}", result.getCode(), result.getMsg()));
            return false; // 拦截请求
        }

        return true; // 放行
    }
}