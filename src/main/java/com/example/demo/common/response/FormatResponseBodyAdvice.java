package com.example.demo.common.response;

import com.example.demo.common.RequestContextHolder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;


@Order(1)
@ControllerAdvice
public class FormatResponseBodyAdvice implements ResponseBodyAdvice {
    private static Logger logger = LoggerFactory.getLogger(FormatResponseBodyAdvice.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        if (returnType.getMethod().getReturnType().isAssignableFrom(Response.class)) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Object wrapperBody = body;
        try {
            if (!RequestContextHolder.FALSE_FLAG.equals(RequestContextHolder.getResponseFormatFlag())) {
                if (!(body instanceof Response)) {
                    if (body instanceof String) {
                        wrapperBody = objectMapper.writeValueAsString(new Response<>(body));
                    } else {
                        wrapperBody = new Response<>(body);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("request uri path: {}, format response body error", request.getURI().getPath(), e);
        }
        return wrapperBody;
    }

}
