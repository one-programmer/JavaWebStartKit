package com.example.demo.common.exception;

import com.example.demo.common.exception.base.BaseException;

public class DemoException extends BaseException {

    public DemoException(DemoErrorEnum errorCode) {
        super(errorCode);
    }

    public DemoException(String message, DemoErrorEnum errorCode) {
        super(message, errorCode);
    }

    public DemoException(String message, Throwable cause, DemoErrorEnum errorCode) {
        super(message, cause, errorCode);
    }
}
