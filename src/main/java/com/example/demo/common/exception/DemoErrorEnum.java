package com.example.demo.common.exception;

import com.example.demo.common.exception.base.ErrorCode;

/**
 * AppException异常类对应的错误码
 */
public enum DemoErrorEnum implements ErrorCode {
    // system
    UNKOWN(1000, "未知错误"),
    TARGET_NOT_EXISTS(1001, "不存在对应的对象"),

    // auth
    USERNAME_OR_PASSWORD_ERROR(2001, "用户名或密码错误");

    private int code;
    private String message;

    DemoErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
