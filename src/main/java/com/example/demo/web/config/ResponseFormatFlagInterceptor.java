package com.example.demo.web.config;

import com.example.demo.common.RequestContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseFormatFlagInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //set request format flag
        String formatFlag = request.getParameter(RequestContextHolder.RESPONSE_FORMAT_FLAG);
        if (formatFlag == null) formatFlag = RequestContextHolder.TRUE_FLAG;
        RequestContextHolder.setResponseFormatFlag(formatFlag);

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContextHolder.restResponseFormatFlag();
    }
}
