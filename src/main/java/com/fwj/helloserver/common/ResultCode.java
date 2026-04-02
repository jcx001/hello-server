package com.fwj.helloserver.common;

public enum ResultCode {
    // 原有用户相关枚举
    USER_HAS_EXISTED(601, "用户名已存在"),
    USER_NOT_EXIST(602, "用户不存在"),
    PASSWORD_ERROR(603, "密码错误"),

    // 新增Token相关枚举（解决报错）
    TOKEN_INVALID(401, "Token无效或已过期"),
    TOKEN_MISSING(402, "Token缺失");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}