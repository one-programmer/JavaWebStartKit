package com.example.demo.common.response;

import com.example.demo.common.exception.base.ErrorCode;

public class Response<T> {
    private int errno = 200;
    private String errmsg = "success";
    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(int errno, String errmsg) {
        this.errno = errno;
        this.errmsg = errmsg;
    }

    public Response(int errno, String errmsg, T data) {
        this.errno = errno;
        this.errmsg = errmsg;
        this.data = data;
    }

    public Response(ErrorCode errorCode) {
        this.errno = errorCode.getCode();
        this.errmsg = errorCode.getMessage();
    }

    public Response(ErrorCode errorCode, T data) {
        this.errno = errorCode.getCode();
        this.errmsg = errorCode.getMessage();
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }


    public String getErrmsg() {
        return errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
