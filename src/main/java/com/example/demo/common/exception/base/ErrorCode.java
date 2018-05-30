package com.example.demo.common.exception.base;


public interface ErrorCode {
    /**
     * 错误码
     */
    Integer getCode();

    /**
     * 错误提示信息
     */
    String getMessage();
}
