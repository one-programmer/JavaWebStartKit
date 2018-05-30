package com.example.demo.common;

public class RequestContextHolder {

    /**
     * request format flag 用于控制请求返回的数据是否使用统一的json格式
     */
    public static final String RESPONSE_FORMAT_FLAG = "formatFlag";
    public static final String TRUE_FLAG = "true";
    public static final String FALSE_FLAG = "false";
    private static final ThreadLocal<String> FORMAT_FLAG = new ThreadLocal<>();

    public static void restResponseFormatFlag() {
        FORMAT_FLAG.remove();
    }

    public static void setResponseFormatFlag(String formatFlag) {
        if (formatFlag == null) {
            restResponseFormatFlag();
        } else {
            FORMAT_FLAG.set(formatFlag);
        }
    }

    public static String getResponseFormatFlag() {
        return FORMAT_FLAG.get();
    }

}
