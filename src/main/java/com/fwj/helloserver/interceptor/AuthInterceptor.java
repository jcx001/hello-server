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
        // 从请求头获取 token
        String token = request.getHeader("token");

        // ========================
        // 【真实可用】这里写你测试用的 token
        // 只要请求头带上这个 token，就放行！
        // ========================
        String REAL_TOKEN = "test123456";

        if (token == null || !token.equals(REAL_TOKEN)) {
            // 验证失败
            Result<String> result = Result.error(ResultCode.TOKEN_INVALID);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(
                    String.format("{\"code\":%d,\"msg\":\"%s\",\"data\":null}",
                            result.getCode(), result.getMsg())
            );
            return false;
        }

        // 验证成功 → 放行
        return true;
    }
}