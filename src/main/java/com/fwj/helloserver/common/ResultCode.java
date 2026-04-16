package com.fwj.helloserver.common;

import lombok.Getter;

@Getter
public enum ResultCode {
    USER_NOT_EXIST(401, "用户不存在"),
    PASSWORD_ERROR(402, "密码错误"),
    USER_HAS_EXISTED(403, "用户名已存在"),
    TOKEN_INVALID(401, "Token无效或已过期"); // 加上这一行！

    private final int code;
    private final String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}