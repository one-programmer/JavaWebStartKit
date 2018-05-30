package com.example.demo.common.exception.base;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public abstract class BaseException extends RuntimeException implements ErrorCode {

    private final ErrorCode errorCode;
    private HttpStatus status;

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, ErrorCode errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public BaseException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    @Override
    public Integer getCode() {
        return errorCode != null ? errorCode.getCode() : null;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

}
